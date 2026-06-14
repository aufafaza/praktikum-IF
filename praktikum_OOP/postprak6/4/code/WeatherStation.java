import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
    // TODO:
    // Deklarasikan field berikut:
    //   private List<WeatherObserver> observers
    //   private double temperature
    //   private double humidity
    private List<WeatherObserver> observers; 
    private double temperature; 
    private double humidity; 
    public WeatherStation() {
        // TODO:
        // Inisialisasi observers sebagai ArrayList kosong.
        // Set temperature = 0.0 dan humidity = 0.0.
        observers = new ArrayList<>(); 
        temperature = 0.0; 
        humidity = 0.0; 
    }

    public void addObserver(WeatherObserver observer) {
        // TODO:
        // Tambahkan observer ke dalam daftar observers.
        if (observer == null) {
            throw new IllegalArgumentException();
        } 
        observers.add(observer); 
    }

    public boolean removeObserver(String name) {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i).getName().equals(name)) {
                observers.remove(i);
                return true;
            }
        }
    return false;
}

    public boolean hasObserver(String name) {
        for (WeatherObserver obs : observers){ 
            if (obs.getName().equals(name)){ 
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
        for (WeatherObserver obs : observers){ 
            obs.update(this.temperature, this.humidity); 
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
        // TODO:
        // Kembalikan jumlah observer yang saat ini terdaftar.
        
        return observers.size();
    }
}
