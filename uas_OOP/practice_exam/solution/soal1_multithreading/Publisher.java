import java.util.List;

public class Publisher extends Thread {
    private final Broker broker;
    private final String topicName;
    private final List<Double> values;

    public Publisher(Broker broker, String topicName, List<Double> values) {
        this.broker = broker;
        this.topicName = topicName;
        this.values = values;
    }

    @Override
    public void run() {
        for (double value : values) {
            broker.publish(topicName, new SensorData(topicName, value));
        }
    }
}
