public class LoggerSubscriber implements Subscriber {
    @Override
    public boolean onData(SensorData data) {
        System.out.println("[LOG] " + data.type + " = " + data.value);
        return true;
    }
}
