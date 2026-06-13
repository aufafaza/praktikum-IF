import java.util.List;

public class Dispatcher extends Thread {
    private final Topic topic;

    public Dispatcher(Topic topic) {
        this.topic = topic;
    }

    @Override
    public void run() {
        while (true) {
            SensorData data = topic.take();
            if (data.isPoisonPill()) {
                break;
            }

            for (Subscriber subscriber : topic.snapshotSubscribers()) {
                boolean keep = subscriber.onData(data);
                if (!keep) {
                    topic.unsubscribe(subscriber);
                }
            }
        }
    }
}
