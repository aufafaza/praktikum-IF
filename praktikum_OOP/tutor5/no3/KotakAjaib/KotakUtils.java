public class KotakUtils {

    // Upper-bounded wildcard: membaca dari Kotak yang berisi subtype Barang apapun
    public static void tampilkanSemua(Kotak<? extends Barang> kotak) {
        // TODO: implementasi
        // Cetak info() setiap item, satu per baris
        // Jika kosong, cetak "Kotak kosong"
        if (kotak.kosong()) {
            System.out.println("Kotak kosong");
            return;
        }
        int n = kotak.jumlah();
        for (int i = 0; i < n; i++) {
            String printString = kotak.lihat(i).info();
            System.out.println(printString);
        }
    }

    // Upper-bounded wildcard: menghitung total harga
    public static int totalHarga(Kotak<? extends Barang> kotak) {
        int n = kotak.jumlah();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + kotak.lihat(i).harga;
        }
        return sum;
    }

    // Upper-bounded wildcard: mencari item termahal
    public static Barang termahal(Kotak<? extends Barang> kotak) {
        if (kotak.kosong()) {
            return null;
        }
        int max = kotak.lihat(0).harga;
        int index = 0;
        int n = kotak.jumlah();
        for (int i = 1; i < n; i++) {
            if (max < kotak.lihat(i).harga) {
                max = kotak.lihat(i).harga;
                index = i;
            }
        }
        return kotak.lihat(index);
    }

    // Bounded wildcard dengan type parameter (PECS: Producer Extends, Consumer
    // Super)
    // src menggunakan ? extends T (producer/pembaca), item diambil dari src
    // dst menggunakan ? super T (consumer/penulis), item ditambahkan ke dst
    public static <T extends Barang> int pindahkan(Kotak<? extends T> src, Kotak<? super T> dst) {
        // Pindahkan item dari src ke dst secara LIFO
        // Berhenti jika src kosong atau dst penuh
        // Kembalikan jumlah item yang dipindahkan
        if (src.kosong()) {
            return 0;
        }
        int amt = 0;
        while (!dst.penuh() && !src.kosong()) {
            T src_t = src.ambil();
            dst.tambah(src_t);
            amt++;
        }
        return amt;
    }

    // Unbounded wildcard: hanya perlu menghitung jumlah
    public static int hitungItem(Kotak<?> kotak) {
        return kotak.jumlah();
    }
}
