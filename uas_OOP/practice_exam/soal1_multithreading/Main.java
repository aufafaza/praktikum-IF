import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Broker broker = new Broker(2);
        Map<String, Dispatcher> dispatchers = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.next();
            if (cmd.equals("SUBSCRIBE")) {
                String topicName = sc.next();
                String spec = sc.next();
                broker.subscribe(topicName, createSubscriber(spec));
                dispatchers.computeIfAbsent(topicName, name -> {
                    Dispatcher d = new Dispatcher(broker.getOrCreateTopic(name));
                    d.start();
                    return d;
                });
            } else if (cmd.equals("PUBLISH")) {
                String topicName = sc.next();
                double value = sc.nextDouble();
                broker.publish(topicName, new SensorData(topicName, value));
            } else if (cmd.equals("END")) {
                break;
            }
        }
        sc.close();

        // kirim poison pill ke setiap topic yang punya dispatcher,
        // lalu tunggu semua dispatcher selesai
        for (String topicName : dispatchers.keySet()) {
            broker.publish(topicName, new SensorData("END", 0));
        }
        for (Dispatcher d : dispatchers.values()) {
            d.join();
        }
    }

    private static Subscriber createSubscriber(String spec) {
        String[] parts = spec.split(":");
        switch (parts[0]) {
            case "LOG":
                return new LoggerSubscriber();
            case "ALERT":
                return new AlertSubscriber(Double.parseDouble(parts[1]));
            case "COUNT":
                return new CountingSubscriber(Integer.parseInt(parts[1]));
            default:
                throw new IllegalArgumentException("Unknown subscriber spec: " + spec);
        }
    }
}
