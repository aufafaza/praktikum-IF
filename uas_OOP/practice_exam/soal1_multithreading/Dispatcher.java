public class Dispatcher extends Thread {
    private final Topic topic;

    public Dispatcher(Topic topic) {
        this.topic = topic;
    }

    @Override
    public void run() {
        // TODO:
        // loop terus-menerus:
        //   1. ambil data berikutnya dengan topic.take()
        //   2. jika data.isPoisonPill() -> hentikan loop (thread selesai)
        //   3. selain itu, panggil onData(data) untuk SETIAP subscriber
        //      yang terdaftar di topic (Observer notify)
    }
}
