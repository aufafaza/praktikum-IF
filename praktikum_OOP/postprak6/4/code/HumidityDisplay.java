public class HumidityDisplay implements WeatherObserver {
    // Deklarasikan field private String name.
    private String name; 

    public HumidityDisplay(String name) {
        this.name = name; 
    }

    @Override
    public void update(double temperature, double humidity) {
        // TODO:
        // Cetak baris berikut ke System.out:
        // "Display <name>: Kelembaban <humidity>%"
        //
        // Format humidity menggunakan satu angka desimal (%.1f).
        // Contoh: "Display HumidA: Kelembaban 60.0%"
        System.out.printf("Display %s: Kelembaban %.1f%%", this.name, humidity); 
        System.out.println();
    }

    @Override
    public String getName() {
        // TODO:
        // Kembalikan nama tampilan ini.

        return this.name;
    }
}
