interface DiscountStrategy { void apply(Order o); }
class MemberDiscount  implements DiscountStrategy { public void apply(Order o) { o.applyDiscount(0.10); } }
class FlashSaleDiscount implements DiscountStrategy { public void apply(Order o) { o.applyDiscount(0.25); } }
class VoucherDiscount implements DiscountStrategy { ... }

interface OrderValidator { void validate(Order o, OrderValidator next); }
class StockValidator  implements OrderValidator { ... }
class FraudValidator  implements OrderValidator { ... }

interface OrderListener { void onOrderConfirmed(Order o); }

class OrderProcessor {
    private final DiscountStrategy   discount;
    private final List<OrderValidator> validators;
    private final List<OrderListener>  listeners = new ArrayList<>();

    OrderProcessor(DiscountStrategy d, List<OrderValidator> v) {
        this.discount = d; this.validators = v;
    }
    public void addListener(OrderListener l) { listeners.add(l); }

    public void process(Order order) {
        validators.forEach(v -> v.validate(order, null)); // Chain
        discount.apply(order);                            // Strategy
        stripeClient.createCharge(order.getTotal());
        listeners.forEach(l -> l.onOrderConfirmed(order)); // Observer
    }
}

OrderProcessor op = new OrderProcessor(
    isFlashSale ? new FlashSaleDiscount() : new MemberDiscount(),
    List.of(new StockValidator(), new FraudValidator())
);
op.addListener(email::send);
op.addListener(sms::send);
op.addListener(whatsapp::send);  // new channel — one line, no edits inside
