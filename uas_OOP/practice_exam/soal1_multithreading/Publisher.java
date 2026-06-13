import java.util.List;

/**
 * Mensimulasikan satu node ROS yang mempublish serangkaian nilai sensor ke
 * satu topic, berjalan pada thread-nya sendiri.
 */
public class Publisher extends Thread {
    private final Broker broker;
    private final String topicName;
    private final List<Double> values;

    public Publisher(Broker broker, String topicName, List<Double> values) {
        this.broker = broker;
        this.topicName = topicName;
        this.values = values;
    }

    /**
     * TODO: untuk setiap nilai pada `values` (secara berurutan), panggil
     * broker.publish(topicName, new SensorData(topicName, value)).
     */
    @Override
    public void run() {
        // TODO
    }
}
