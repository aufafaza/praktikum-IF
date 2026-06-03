
class ValidPlugin implements Plugin {
    public ValidPlugin() {
    } // Pastikan ada constructor tanpa argumen

    @Override
    public void start() {
        System.out.println("Valid plugin executed!");
    }
}

class InvalidPlugin {
    public InvalidPlugin() {
    }
}
