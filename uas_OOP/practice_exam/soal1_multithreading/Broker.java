import java.util.HashMap;
import java.util.Map;

/**
 * "ROS-like" registry of named topics. Setiap topic punya buffer terbatas
 * (bounded) berukuran sama (`topicCapacity`).
 */
public class Broker {
    private final int topicCapacity;
    private final Map<String, Topic> topics = new HashMap<>();

    public Broker(int topicCapacity) {
        this.topicCapacity = topicCapacity;
    }

    /**
     * TODO: kembalikan Topic dengan nama `name`.
     * - jika belum ada di `topics`, buat `new Topic(name, topicCapacity)`,
     *   simpan ke `topics`, lalu kembalikan.
     * - jika sudah ada, kembalikan yang sudah ada.
     */
    public synchronized Topic getOrCreateTopic(String name) {
        
        return topics.computeIfAbsent(name, n-> new Topic(n, topicCapacity));
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        getOrCreateTopic(topicName).subscribe(subscriber);
    }

    public void publish(String topicName, SensorData data) {
        getOrCreateTopic(topicName).publish(data);
    }
}
