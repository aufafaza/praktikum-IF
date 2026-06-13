public class AlertSubscriber implements Subscriber {
    private final double threshold;

    public AlertSubscriber(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void onData(SensorData data) {
        // TODO: jika data.value > threshold, cetak:
        // "[ALERT] <type> = <value> exceeds threshold <threshold>"
    }
}
