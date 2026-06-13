import java.util.List;

/**
 * Satu Dispatcher berjalan per Topic, mengambil data dari topic tersebut dan
 * menyebarkannya (Observer notify) ke semua subscriber-nya.
 */
public class Dispatcher extends Thread {
    private final Topic topic;

    public Dispatcher(Topic topic) {
        this.topic = topic;
    }

    /**
     * TODO:
     * loop terus-menerus:
     *   1. ambil data berikutnya dengan topic.take()
     *   2. jika data.isPoisonPill() -> hentikan loop (thread selesai)
     *   3. selain itu, untuk setiap subscriber pada topic.snapshotSubscribers():
     *        - panggil onData(data)
     *        - jika onData mengembalikan false, panggil
     *          topic.unsubscribe(subscriber)
     */
    @Override
    public void run() {
        // TODO
    }
}
