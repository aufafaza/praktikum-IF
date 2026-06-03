import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class WeatherStation {
    // TODO:
    // Deklarasikan field berikut:
    // private List<WeatherObserver> observers
    // private double temperature
    // private double humidity
    private List<WeatherObserver> observers;
    private double temperature;
    private double humidity;

    public WeatherStation() {
        // TODO:
        // Inisialisasi observers sebagai ArrayList kosong.
        // Set temperature = 0.0 dan humidity = 0.0.
        this.temperature = 0.0;
        this.humidity = 0.0;
        this.observers = new ArrayList<>();
    }

    public void addObserver(WeatherObserver observer) {
        if (observer != null) {
            // TODO:
            // Tambahkan observer ke dalam daftar observers.
            this.observers.add(observer);
        }
    }

    public boolean removeObserver(String name) {
        if (this.observers.size() == 0) {
            throw new IllegalArgumentException();
        }
        // TODO:
        // Cari observer dengan nama yang sesuai di dalam daftar.
        // Jika ditemukan, hapus dan kembalikan true.
        // Jika tidak ditemukan, kembalikan false.
        Iterator<WeatherObserver> it = observers.iterator();
        while (it.hasNext()) {
            WeatherObserver observer = it.next();
            if (observer.getName().equals(name)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean hasObserver(String name) {
        // TODO:
        // Kembalikan true jika terdapat observer dengan nama tersebut
        // di dalam daftar, false jika tidak.
        Iterator<WeatherObserver> it = observers.iterator();
        while (it.hasNext()) {
            WeatherObserver observer = it.next();
            if (observer.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void setMeasurements(double temperature, double humidity) {
        // TODO:
        // Perbarui nilai temperature dan humidity.
        // Setelah diperbarui, panggil notifyObservers() untuk
        // memberitahu semua observer yang terdaftar.
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }

    private void notifyObservers() {
        // TODO:
        // Iterasi seluruh observer dalam daftar (sesuai urutan pendaftaran)
        // dan panggil update(temperature, humidity) pada masing-masing observer.
        Iterator<WeatherObserver> it = observers.iterator();
        while (it.hasNext()) {
            WeatherObserver observer = it.next();
            observer.update(this.temperature, this.humidity);
        }

    }

    public double getTemperature() {
        // TODO:
        // Kembalikan nilai temperature saat ini.

        return this.temperature;
    }

    public double getHumidity() {
        // TODO:
        // Kembalikan nilai humidity saat ini.

        return this.humidity;
    }

    public int getObserverCount() {
        return this.observers.size();
    }
}
