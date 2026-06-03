
public class Checkout {
    private PaymentStrategy pStrat;

    public void setPaymentStrategy(PaymentStrategy p) {
        this.pStrat = p;
    }

    public void processPayment(int amount) {
        if (this.pStrat == null) {
            System.out.println("No payment method selected");
            return;
        }
        this.pStrat.pay(amount);
    }
}
