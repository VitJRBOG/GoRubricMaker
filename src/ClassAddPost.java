import java.util.Scanner;

class ClassAddPost {

    void MenuAddPost() {
        System.out.println("COMPUTER: [Main menu -> Add post -> ..] 1 == Add question.");
        System.out.println("COMPUTER: [Main menu -> Add post  -> ..] 2 == Add loss.");
        System.out.println("COMPUTER: [Main menu -> Add post  -> ..] 0 == Step back.");

        Scanner objScanner =
                new Scanner(System.in);

        System.out.println("USER: [.. -> Add post -> ] ");
        String varStringUserAnswer = objScanner.nextLine();

        if (varStringUserAnswer.equals("0")) {
            ClassMainMenu objClassMainMenu =
                    new ClassMainMenu();
            objClassMainMenu.MainMenu();
        } else {
            if (varStringUserAnswer.equals("1")) {
                operationAddQuestion();
            } else {
                if (varStringUserAnswer.equals("2")) {
                    operationAddLoss();
                } else {
                    System.out.println("COMPUTER: Unknown operation. Retry query...");
                    MenuAddPost();
                }
            }
        }

    }

    void operationAddQuestion() {
        System.out.println("COMPUTER: Here is empty. Return to Menu Add Post...");
        MenuAddPost();
    }

    void operationAddLoss() {
        System.out.println("COMPUTER: Here is empty. Return to Menu Add Post...");
        MenuAddPost();
    }

}
