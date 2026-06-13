public class AlertSubscriber implements Subscriber {
    private final double threshold;

    public AlertSubscriber(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean onData(SensorData data) {
        // TODO: jika data.value > threshold, cetak:
        // "[ALERT] <type> = <value> exceeds threshold <threshold>"
        // subscriber ini tidak pernah berhenti -> selalu return true
        return true;
    }
}
