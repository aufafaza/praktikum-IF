import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Topic {
    private final String name;
    private final int capacity;
    private final Queue<SensorData> buffer = new ArrayDeque<>();
    private final List<Subscriber> subscribers = new ArrayList<>();

    public Topic(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public synchronized void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public synchronized void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    /**
     * Kembalikan salinan (snapshot) daftar subscriber saat ini, supaya
     * Dispatcher bisa meng-iterasi tanpa kena ConcurrentModificationException
     * saat ada subscriber yang unsubscribe di tengah iterasi.
     */
    public synchronized List<Subscriber> snapshotSubscribers() {
        return new ArrayList<>(subscribers);
    }

    /**
     * Dipanggil oleh publisher (main thread) untuk mengirim data baru.
     * TODO:
     * - selama buffer.size() == capacity (buffer PENUH), publisher harus
     *   wait() (menunggu consumer mengambil data lewat take()).
     * - setelah ada slot kosong, tambahkan `data` ke buffer, lalu
     *   notifyAll() untuk membangunkan consumer yang menunggu di take().
     */
    public synchronized void publish(SensorData data) {
        // TODO
    }

    /**
     * Dipanggil oleh Dispatcher untuk mengambil data berikutnya.
     * TODO:
     * - selama buffer KOSONG, consumer harus wait() (menunggu publisher
     *   memanggil publish()).
     * - setelah ada data, ambil elemen paling depan, lalu notifyAll() untuk
     *   membangunkan publisher yang menunggu slot kosong di publish().
     */
    public synchronized SensorData take() {
        // TODO
        return null;
    }
}
