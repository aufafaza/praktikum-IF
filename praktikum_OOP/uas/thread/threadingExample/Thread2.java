public class Thread2 extends Thread {
    String name;
    int total;
    boolean isCompleted = false;

    Thread2(String name) {
        this.name = name;
        this.total = 0;
    }

    @Override
    public synchronized void run() {

        for (int i = 0; i < 10; i++) {
            total += i;
            System.out.println(this.name + " Current total + " + this.total);
        }
        this.isCompleted = true;
        notifyAll();

    }
}
