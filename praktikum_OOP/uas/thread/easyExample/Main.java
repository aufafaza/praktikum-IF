
public class Main {
    public static void main(String[] args) {
        Pizza p = new Pizza("myPizzarn");
        PizzaBox pb = new PizzaBox();

        pb.putPizza(p);

        Pizza getP = pb.getPizza();
        System.out.println("Got pizza" + getP.getName());
    }
}
