public class CountingSubscriber implements Subscriber {
    private final int limit;
    private int count = 0;

    public CountingSubscriber(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean onData(SensorData data) {
        count++;
        System.out.println("[COUNT] " + data.type + " = " + data.value
                + " (" + count + "/" + limit + ")");

        if (count >= limit) {
            System.out.println("[UNSUB] CountingSubscriber done after " + limit + " messages");
            return false;
        }
        return true;
    }
}
