public class LaciUtil {
    public static <T> void tukar(Laci<T> laci, int i, int j) {
        if (i < 0 || j < 0 || i > laci.ukuran() || j > laci.ukuran() || laci.ukuran() == 0) {
            return;
        }
        // T ti = laci.ambil(i);
        T tj = laci.ambil(j);
        T swap = laci.ambil(i);
        laci.set(i, tj);
        laci.set(j, swap);

    }

    public static <T extends Comparable<T>> T terbesar(Laci<T> laci) {
        if (laci.ukuran() == 0) {
            return null;
        }
        T max = laci.ambil(1);
        for (int i = 2; i <= laci.ukuran(); i++) {
            if (max.compareTo(laci.ambil(i)) < 0) {
                max = laci.ambil(i);
            }
        }
        return max;
    }

}
