import java.util.List;

public class PublisherGroup {
    private final List<Publisher> publishers;

    public PublisherGroup(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public void runAll() throws InterruptedException {
        for (Publisher publisher : publishers) {
            publisher.start();
        }
        for (Publisher publisher : publishers) {
            publisher.join();
        }
    }
}
