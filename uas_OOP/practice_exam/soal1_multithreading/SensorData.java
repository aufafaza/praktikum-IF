public class SensorData {
    public final String type;
    public final double value;

    public SensorData(String type, double value) {
        this.type = type;
        this.value = value;
    }

    public boolean isPoisonPill() {
        return "END".equals(type);
    }
}
