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

    public synchronized List<Subscriber> snapshotSubscribers() {
        return new ArrayList<>(subscribers);
    }

    public synchronized void publish(SensorData data) {
        while (buffer.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        buffer.add(data);
        notifyAll();
    }

    public synchronized SensorData take() {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        SensorData data = buffer.poll();
        notifyAll();
        return data;
    }
}
