class BaseBot {
    int power = 10;

    String getTipe() {
        return "Greedy";
    }
}

class MainBot extends BaseBot {
    int power = 50;

    String getTipe() {
        return "Dynamic";
    }
}

public class Main {
    public static void main(String[] args) {
        BaseBot bot = new MainBot();
        System.out.println(bot.power + " & " + bot.getTipe());
    }
}
