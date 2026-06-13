public class LoggerSubscriber implements Subscriber {
    @Override
    public void onData(SensorData data) {
        System.out.println("[LOG] " + data.type + " = " + data.value);
    }
}
