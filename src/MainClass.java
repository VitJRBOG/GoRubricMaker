public class MainClass {
    public static void main(String[] args) {
        MainClass objMainClass =
                new MainClass();
        objMainClass.Starter();
    }

    private void Starter() {
        ClassMainMenu objClassMainMenu =
                new ClassMainMenu();
        objClassMainMenu.MainMenu();
    }

    void Exit() {
        System.out.println("COMPUTER: Exit from program...");
        System.exit(0);
    }
}
