public class Main {
    public static void main(String[] args) {
        Monster mon = new Monster("faza", 10, 20);
        SummonedMonster m = new SummonedMonster(mon, false, false);
        m.render();

        m.flip();
        m.render();

        m.getPositionValue();
        m.render();

        m.rotate();
        m.render();
    }
}
