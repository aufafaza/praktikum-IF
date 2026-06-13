import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ORM {

    /**
     * Mencetak skema (nama tabel dan kolom-kolom) dari kelas yang diberikan.
     * Hanya field yang beranotasi @ColumnName yang ditampilkan.
     *
     * Format output:
     * Table: <nama_tabel>
     * Columns:
     * - <nama_kolom> [PRIMARY KEY] <- jika primaryKey = true
     * - <nama_kolom>
     */
    public static void schema(Class<?> clazz) {
        // getTable first
        TableName tableName = clazz.getAnnotation(TableName.class);
        System.out.println("Table Name: " + tableName);
        System.out.println("Columns: ");
        // getDecalredFields gets all visibility attributes in a given class
        for (Field f : clazz.getDeclaredFields()) {
            ColumnName col = f.getAnnotation(ColumnName.class);
            if (col != null) {
                System.out.print(" - " + col.value());
                if (col.primaryKey()) {
                    System.out.print(" [PRIMARY KEY]");
                }
                System.out.println();
            }
        }
    }

    /**
     * Membuat instance dari kelas yang diberikan menggunakan Reflection.
     * values[] berisi nilai-nilai untuk field beranotasi @ColumnName sesuai urutan
     * deklarasi.
     * Tipe yang perlu didukung: String, int, double.
     *
     * Setelah semua field diisi, panggil semua method yang beranotasi
     * 
     * @Hook(when = Hook.When.POST_LOAD) menggunakan method.invoke().
     */
    public static Object createInstance(Class<?> clazz, String[] values) throws Exception {
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Field[] fields = clazz.getDeclaredFields();

        // initialize fields
        int index = 0;
        for (Field f : fields) {
            f.setAccessible(true);
            // sets all attributes to accessible
            ColumnName col = f.getAnnotation(ColumnName.class);
            if (col == null)
                continue;
            String val = values[index++];
            // getes the values that we want to put inside the field
            if (f.getType() == String.class) {
                f.set(obj, val);
            } else if (f.getType() == Integer.class) {
                f.set(obj, Integer.parseInt(val));
            } else if (f.getType() == Double.class) {
                f.set(obj, Double.parseDouble(val));
            }
        }
        // then we invoke methods
        for (Method method : clazz.getDeclaredMethods()) {
            Hook hook = method.getAnnotation(Hook.class);
            if (hook != null && hook.when() == Hook.When.POST_LOAD) {
                method.setAccessible(true);
                method.invoke(obj);
            }
        }
        // this whole process reflects a given object from a given class owning a
        // certain method, and then instaniating them with its declared attributes and a
        // method hook POST_LOAD;
        return obj;

    }

    /**
     * Mencetak pernyataan INSERT SQL berdasarkan objek yang diberikan.
     *
     * Sebelum mencetak SQL, panggil semua method yang beranotasi
     * 
     * @Hook(when = Hook.When.PRE_INSERT) menggunakan method.invoke().
     *            Jika salah satu method tersebut melempar exception
     *            (InvocationTargetException),
     *            cetak "Gagal insert: <pesan exception>" dan JANGAN cetak SQL
     *            INSERT.
     *
     *            Format output (jika tidak ada exception):
     *            INSERT INTO <nama_tabel> (<col1>, <col2>, ...) VALUES (<val1>,
     *            <val2>, ...)
     *            Keterangan: nilai String diapit tanda kutip satu (' '),
     *            nilai numerik (int/double) ditulis apa adanya.
     */
    public static void insert(Object obj) throws Exception {
        // because we only want to USE The method instead of instaniating an object, we
        // don't need to get its fields, just its methods
        Class<?> clazz = obj.getClass();
        try {
            for (Method method : clazz.getDeclaredMethods()) {
                Hook hook = method.getAnnotation(Hook.class);
                if (hook != null && hook.when() == Hook.When.PRE_INSERT) {
                    method.setAccessible(true);
                    method.invoke(obj);
                }
            }
        } catch (InvocationTargetException e) {
            System.out.println("Gagal print");
            return;
        }
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        boolean first = true;
        for (Field f : clazz.getDeclaredFields()) {
            ColumnName column = f.getAnnotation(ColumnName.class);
            if (column != null) {
                if (!first) {
                    columns.append(", ");
                    values.append(", ");
                }
                first = false;
                columns.append(column.value());
                f.setAccessible(true);
                Object val = f.get(obj);
                if (val instanceof String) {
                    values.append("'").append(val).append("'");
                } else {
                    values.append(val);
                }
            }
        }
        TableName tableName = clazz.getAnnotation(TableName.class);
        System.out.println("INSERT INTO " + tableName.value() + " (" + columns +
                ") VALUES (" + values + ")");
    }

    /**
     * Mencetak pernyataan DELETE SQL berdasarkan objek yang diberikan.
     *
     * Sebelum mencetak SQL, panggil semua method yang beranotasi
     * 
     * @Hook(when = Hook.When.PRE_DELETE) menggunakan method.invoke().
     *            Jika salah satu method tersebut melempar exception
     *            (InvocationTargetException),
     *            cetak "Gagal delete: <pesan exception>" dan JANGAN cetak SQL
     *            DELETE.
     *
     *            Format output (jika tidak ada exception):
     *            DELETE FROM <nama_tabel> WHERE <pk_kolom> = <pk_nilai>
     *            Keterangan: pk_nilai bertipe String diapit tanda kutip satu (' '),
     *            pk_nilai bertipe numerik ditulis apa adanya.
     *            Gunakan field pertama yang memiliki @ColumnName(primaryKey = true)
     *            sebagai WHERE clause.
     */
    public static void delete(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        try {
            for (Method method : clazz.getDeclaredMethods()) {
                Hook hook = method.getAnnotation(Hook.class);
                if (hook != null && hook.when() == Hook.When.PRE_DELETE) {
                    method.setAccessible(true);
                    method.invoke(obj);
                }
            }
        } catch (InvocationTargetException e) {
            System.out.println("Gagal delete");
            return;
        }

        TableName tableName = clazz.getAnnotation(TableName.class);
        Field pkField = null;
        String pkColumnName = null;
        for (Field f : clazz.getDeclaredFields()) {
            ColumnName column = f.getAnnotation(ColumnName.class);
            if (column != null && column.primaryKey()) {
                pkField = f;
                pkColumnName = column.value();
                break;
            }
        }

        pkField.setAccessible(true);
        Object pkValue = pkField.get(obj);
        System.out.println("DELETE FROM " + tableName.value() + " WHERE " + pkColumnName + " = ");
        if (pkValue instanceof String) {
            System.out.println("`" + pkValue + "`");
        } else {
            System.out.println(pkValue);
        }

    }
}
