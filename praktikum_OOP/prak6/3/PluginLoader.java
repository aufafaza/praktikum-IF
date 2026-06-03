public class PluginLoader {

    /**
     * Me-load kelas plugin secara dinamis berdasarkan nama kelas yang diberikan.
     * Kelas yang di-load harus merupakan implementasi dari interface Plugin.
     * Jika kelas tersebut tidak mengimplementasikan interface Plugin, method ini
     * harus melempar IllegalArgumentException dengan pesan:
     * "Kelas <className> tidak mengimplementasikan interface Plugin"
     * 
     * @param className Nama lengkap kelas yang akan di-load.
     * @return Instance dari kelas plugin yang berhasil di-load.
     * @throws Exception Jika terjadi error saat proses pemuatan kelas atau
     *                   instansiasi.
     */
    public static Plugin loadPlugin(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        if (!Plugin.class.isAssignableFrom(clazz)) {
            String err = new String("Kelas " + className + " tidak mengimplementasikan Plugin");
            throw new IllegalArgumentException(err);
        }
        return (Plugin) clazz.getDeclaredConstructor().newInstance();
    }
}
