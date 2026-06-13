class PaymentService {
 
    public void charge(String provider, int amount) {
        if (provider.equals("stripe")) {
            StripeClient stripe = new StripeClient();
            stripe.createCharge(amount, "usd");
        } else if (provider.equals("gopay")) {
            GoPay gp = new GoPay("api-key-123");
            gp.debit(amount);
        } else if (provider.equals("ovo")) {
            OvoSDK ovo = OvoSDK.initialize();
            ovo.pay(amount, Currency.IDR);
        }
    }
}
