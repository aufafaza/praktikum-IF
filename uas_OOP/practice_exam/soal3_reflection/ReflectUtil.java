import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectUtil {

    /**
     * TODO: lihat PROBLEM.md poin 1.
     */
    public static Object createInstance(Class<?> clazz, Map<String, String> data) throws Exception {
        return null;
    }

    /**
     * TODO: lihat PROBLEM.md poin 2.
     */
    public static Map<String, String> readProperties(Object obj) throws Exception {
        return new LinkedHashMap<>();
    }

    /**
     * TODO: lihat PROBLEM.md poin 3.
     */
    public static boolean updateField(Object obj, String propertyName, String newValue) throws Exception {
        return false;
    }

    /**
     * TODO: lihat PROBLEM.md poin 4.
     */
    public static void printSchema(Class<?> clazz) {
    }

    /**
     * Helper: konversi String ke tipe field (int, double, atau String).
     * Sudah lengkap, silakan dipakai.
     */
    private static Object convert(Class<?> type, String value) {
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        }
        if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        }
        return value;
    }
}
