import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Topic topic = new Topic();
        topic.subscribe(new LoggerSubscriber());
        topic.subscribe(new AlertSubscriber(40.0));

        Dispatcher dispatcher = new Dispatcher(topic);
        dispatcher.start();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.next();
            if (cmd.equals("PUBLISH")) {
                String type = sc.next();
                double value = sc.nextDouble();
                topic.publish(new SensorData(type, value));
            } else if (cmd.equals("END")) {
                topic.publish(new SensorData("END", 0));
                break;
            }
        }
        sc.close();

        dispatcher.join();
    }
}
