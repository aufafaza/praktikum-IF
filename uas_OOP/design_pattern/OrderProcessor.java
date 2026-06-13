class OrderProcessor {
 
    public void process(Order order) {
 
        if (order.getItems().isEmpty()) throw new Ex(...);
        if (order.getTotal() <= 0) throw new Ex(...);
 
        if (order.isMember()) {
            order.applyDiscount(0.10);
        } else {
            order.applyDiscount(0.0);
        }
 
        stripeClient.createCharge(order.getTotal());
 
        emailService.send(order.getUserEmail(), "confirmed");
        smsService.send(order.getPhone(), "confirmed");
    }
}
