import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Topic {
    private final Queue<SensorData> buffer = new ArrayDeque<>();
    private final List<Subscriber> subscribers = new ArrayList<>();

    public synchronized void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    /**
     * Dipanggil oleh publisher (main thread) untuk mengirim data baru.
     * TODO: tambahkan `data` ke buffer, lalu bangunkan thread yang menunggu
     * di take().
     */
    public synchronized void publish(SensorData data) {
        // TODO
    }

    /**
     * Dipanggil oleh Dispatcher untuk mengambil data berikutnya.
     * TODO: tunggu (wait) selama buffer kosong, lalu ambil & kembalikan
     * elemen paling depan.
     */
    public synchronized SensorData take() {
        // TODO
        return null;
    }
}
