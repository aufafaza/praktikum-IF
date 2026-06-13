public class Main {
    public static void main(String[] args) {
        Thread2 objB = new Thread2("objB");
        Thread2 objC = new Thread2("objC");
        objB.start();
        objC.start();

        try {
            System.out.println(
                    "Waiting for threads to complete...");
            synchronized (objB) {
                while (!objB.isCompleted) {
                    objB.wait();
                }
            }

            synchronized (objC) {

                while (!objC.isCompleted) {
                    objC.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Total is: " + objB.total);
        System.out.println("Total is: " + objC.total);

    }

}
