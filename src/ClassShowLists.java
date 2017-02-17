import java.util.Scanner;

class ClassShowLists {

    void MenuShowLists() {
        System.out.println("COMPUTER: [Main menu -> Show lists -> ..] 1 == Show list of questions.");
        System.out.println("COMPUTER: [Main menu -> Show lists  -> ..] 2 == Show list of loss.");
        System.out.println("COMPUTER: [Main menu -> Show lists  -> ..] 0 == Step back.");

        Scanner objScanner =
                new Scanner(System.in);

        System.out.println("USER: [.. -> Show lists -> ] ");
        String varStringUserAnswer = objScanner.nextLine();

        if (varStringUserAnswer.equals("0")) {
            ClassMainMenu objClassMainMenu =
                    new ClassMainMenu();
            objClassMainMenu.MainMenu();
        } else {
            if (varStringUserAnswer.equals("1")) {
                operationShowListOfQuestions();
            } else {
                if (varStringUserAnswer.equals("2")) {
                    operationShowListOfLoss();
                } else {
                    System.out.println("COMPUTER: Unknown operation. Retry query...");
                    MenuShowLists();
                }
            }
        }
    }

    void operationShowListOfQuestions() {
        System.out.println("COMPUTER: Here is empty. Return to Menu Add Post...");
        MenuShowLists();
    }

    void operationShowListOfLoss() {
        System.out.println("COMPUTER: Here is empty. Return to Menu Add Post...");
        MenuShowLists();
    }

}
