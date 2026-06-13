public class AlertSubscriber implements Subscriber {
    private final double threshold;

    public AlertSubscriber(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean onData(SensorData data) {
        if (data.value > threshold) {
            System.out.println("[ALERT] " + data.type + " = " + data.value
                    + " exceeds threshold " + threshold);
        }
        return true;
    }
}
