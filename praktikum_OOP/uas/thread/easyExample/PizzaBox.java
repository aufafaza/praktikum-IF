class PizzaBox {
    Pizza currentPizza;
    boolean available = false;

    synchronized Pizza getPizza() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        // here available is true, sendback to false
        available = false;
        notifyAll();
        return currentPizza;
    }

    synchronized void putPizza(Pizza p) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        currentPizza = p;
        available = true;
        notifyAll();
    }
}
