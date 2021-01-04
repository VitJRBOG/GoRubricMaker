import java.util.Scanner;

class ClassMainMenu {

    void MainMenu() {
        System.out.println("COMPUTER: [Main menu -> ..] 1 == Add post.");
        System.out.println("COMPUTER: [Main menu -> ..] 2 == Show lists.");
        System.out.println("COMPUTER: [Main menu -> ..] 3 == File manager.");
        System.out.println("COMPUTER: [Main menu -> ..] 0 == Close program.");

        Scanner objScanner =
                new Scanner(System.in);

        System.out.print("USER: [Main menu -> ] ");
        String varStringUserAnswer = objScanner.nextLine();

        if (varStringUserAnswer.equals("0")) {
            MainClass objMainClass =
                    new MainClass();
            objMainClass.Exit();
        } else {
            if (varStringUserAnswer.equals("1")) {
                ClassAddPost objClassAddPost =
                        new ClassAddPost();
                objClassAddPost.MenuAddPost();
            } else {
                if (varStringUserAnswer.equals("2")) {
                    ClassShowLists objClassShowLists =
                            new ClassShowLists();
                    objClassShowLists.MenuShowLists();
                } else {
                    if (varStringUserAnswer.equals("3")) {
                        ClassMenuFileManager objClassMenuFileManager =
                                new ClassMenuFileManager();
                        objClassMenuFileManager.MenuFileManager();
                    } else {
                        System.out.println("COMPUTER: Unknown operation. Retry query...");
                        MainMenu();
                    }
                }
            }
        }
    }

}
