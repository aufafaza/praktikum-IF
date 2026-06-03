import java.util.List;

public class DapurJus {

    private DapurJus() {
    };

    public static void cekBahan(List<? extends Buah> bahan) {
        for (Buah b : bahan) {
            System.out.println(b.deskripsi());
        }
    }

    public static int hitungTotalManis(List<? extends Buah> bahan) {
        int manis = 0;
        for (int i = 0; i < bahan.size(); i++) {
            manis += bahan.get(i).getTingkatManis();
        }
        return manis;
    }

    public static void buatJusApelDefault(List<? super JusApel> daftarMinuman) {
        JusApel m1 = new JusApel("Jus Apel Original");
        JusApel m2 = new JusApel("Jus Apel Madu");

        daftarMinuman.add(m1);
        daftarMinuman.add(m2);
    }

    public static void cetakRakUmum(List<?> rak) {
        for (int i = 0; i < rak.size(); i++) {
            System.out.println(rak.get(i));
        }
    }

}
