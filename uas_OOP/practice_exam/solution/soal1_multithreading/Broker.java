import java.util.HashMap;
import java.util.Map;

public class Broker {
    private final int topicCapacity;
    private final Map<String, Topic> topics = new HashMap<>();

    public Broker(int topicCapacity) {
        this.topicCapacity = topicCapacity;
    }

    public synchronized Topic getOrCreateTopic(String name) {
        return topics.computeIfAbsent(name, n -> new Topic(n, topicCapacity));
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        getOrCreateTopic(topicName).subscribe(subscriber);
    }

    public void publish(String topicName, SensorData data) {
        getOrCreateTopic(topicName).publish(data);
    }
}
