import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Scanner;

class ClassAddPost {

    void MenuAddPost() {
        System.out.println("COMPUTER: [Main menu -> Add post -> ..] 1 == Add question.");
        System.out.println("COMPUTER: [Main menu -> Add post  -> ..] 2 == Add loss.");
        System.out.println("COMPUTER: [Main menu -> Add post  -> ..] 0 == Step back.");

        Scanner objScanner =
                new Scanner(System.in);

        System.out.print("USER: [.. -> Add post -> ] ");
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

        ModelPost objModelPost =
                new ModelPost();

        objModelPost = setBody(objModelPost);
        objModelPost = setPhoto(objModelPost);
        objModelPost = setAuthor(objModelPost);
    }

    private ModelPost setBody(ModelPost objModelPost) {
        System.out.println("COMPUTER: [.. -> Add post -> Add question] Copy content for body and press Enter. " +
                "Enter '0' for cancel.");
        System.out.print("USER: [.. -> Add question -> Body] ");
        Scanner objScanner =
                new Scanner(System.in);
        String varStringUserAnswer = objScanner.nextLine();

        if (varStringUserAnswer.equals("0")) {
            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
            MenuAddPost();
        } else {
            try {
                MainClass.TextTransfer objTextTransfer =
                        new MainClass.TextTransfer();
                objModelPost.setBody(objTextTransfer.getData());

                System.out.println("COMPUTER: [.. Add question -> Body] "
                        + objModelPost.getNumber() + objModelPost.getBody());

                System.out.println("COMPUTER: [.. -> Add question -> Body] " +
                        "Press Enter for continue. Enter \"00\" for cancel.");

                varStringUserAnswer = objScanner.nextLine();

                if (varStringUserAnswer.equals("00")) {
                    System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                    MenuAddPost();
                }
            } catch (IOException | UnsupportedFlavorException e) {
                e.printStackTrace();
            }
        }

        return objModelPost;
    }

    private ModelPost setPhoto(ModelPost objModelPost) {
        System.out.println("COMPUTER: [.. -> Add post -> Add question] Enter count of photos and press Enter. " +
                "Enter \"00\" for cancel.");
        System.out.print("USER: [.. -> Add question -> Photo] ");
        Scanner objScanner =
                new Scanner(System.in);
        String varStringUserAnswer = objScanner.nextLine();

        String[] arrayStringPhoto;

        if (varStringUserAnswer.equals("00")) {
            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
            MenuAddPost();
        } else {
            try {
                int varIntUserAnswer = Integer.parseInt(varStringUserAnswer);
                if (varIntUserAnswer > 0 &&
                        varIntUserAnswer <= 10) {
                    try {

                        arrayStringPhoto =
                                new String[varIntUserAnswer];

                        MainClass.TextTransfer objTextTransfer =
                                new MainClass.TextTransfer();

                        System.out.println("COMPUTER: [.. -> Add question -> Photo] " +
                                "Copy link to photo and press Enter. Enter \"00\" for cancel.");

                        for (int i = 0; i < arrayStringPhoto.length; i++) {

                            System.out.print("USER: [.. -> Add question -> Photo -> №" + (i + 1) + "] ");
                            varStringUserAnswer = objScanner.nextLine();

                            if (varStringUserAnswer.equals("0")) {
                                System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                                MenuAddPost();
                            }

                            arrayStringPhoto[i] = "- " + objTextTransfer.getData();
                        }

                        objModelPost.setPhoto(arrayStringPhoto);

                        for (String varString : objModelPost.getPhoto()) {
                            System.out.println("COMPUTER: [.. Add question -> Photo] "
                                    + varString);
                        }

                        System.out.println("COMPUTER: [.. -> Add question -> Photo] " +
                                "Press Enter for continue. Enter \"00\" for cancel.");

                        varStringUserAnswer = objScanner.nextLine();

                        if (varStringUserAnswer.equals("00")) {
                            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                            MenuAddPost();
                        }

                    } catch (IOException | UnsupportedFlavorException e) {
                        e.printStackTrace();
                        System.out.println("COMPUTER: Error. Retry query...");
                        return setPhoto(objModelPost);
                    }
                } else {
                    if (varIntUserAnswer == 0) {
                        objModelPost.setPhoto(null);
                        return objModelPost;
                    } else {
                        System.out.println("COMPUTER: Invalid value. Retry query...");
                        return setPhoto(objModelPost);
                    }
                }
            } catch (ClassCastException e) {
                e.printStackTrace();
                System.out.println("COMPUTER: Error. Retry query...");
                return setPhoto(objModelPost);
            }
        }
        return objModelPost;
    }

    private ModelPost setAuthor(ModelPost objModelPost) {

        System.out.println("COMPUTER: [.. -> Add post -> Add question] Enter '1', if need signature, or '0', if not. " +
                "Enter \"00\" for cancel.");
        System.out.print("USER: [.. -> Add question -> Author] ");
        Scanner objScanner =
                new Scanner(System.in);
        String varStringUserAnswer = objScanner.nextLine();

        if (varStringUserAnswer.equals("00")) {
            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
            MenuAddPost();
        } else {
            if (varStringUserAnswer.equals("0")) {
                objModelPost.setLinkAuthor("");
                objModelPost.setNameAuthor("");
                return objModelPost;
            } else {
                if (varStringUserAnswer.equals("1")) {
                    try {
                        System.out.println("COMPUTER: [.. -> Add question -> Author -> Link] " +
                                "Copy link to author and press Enter. Enter \"00\" for cancel.");
                        System.out.print("USER: [.. -> Add question -> Author -> Link] ");
                        varStringUserAnswer = objScanner.nextLine();

                        if (varStringUserAnswer.equals("00")) {
                            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                            MenuAddPost();
                        }

                        MainClass.TextTransfer objTextTransfer =
                                new MainClass.TextTransfer();
                        objModelPost.setLinkAuthor(objTextTransfer.getData());

                        System.out.println("COMPUTER: [.. -> Add question -> Author -> Name] " +
                                "Copy name of author and press Enter. Enter \"00\" for cancel.");
                        System.out.print("USER: [.. -> Add question -> Author -> Name] ");
                        varStringUserAnswer = objScanner.nextLine();

                        if (varStringUserAnswer.equals("00")) {
                            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                            MenuAddPost();
                        }
                        objModelPost.setNameAuthor(objTextTransfer.getData());

                        System.out.println("COMPUTER: [.. Add question -> Author] " + objModelPost.getAuthor());

                        System.out.println("COMPUTER: [.. -> Add question -> Author] " +
                                "Press Enter for continue. Enter \"00\" for cancel.");

                        varStringUserAnswer = objScanner.nextLine();

                        if (varStringUserAnswer.equals("00")) {
                            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                            MenuAddPost();
                        }
                    }
                    catch (IOException | UnsupportedFlavorException e) {
                        e.printStackTrace();
                        System.out.println("COMPUTER: Error. Retry query...");
                        return setPhoto(objModelPost);
                    }
                }
            }
        }
        return objModelPost;
    }

    void operationAddLoss() {
        System.out.println("COMPUTER: Here is empty. Return to Menu Add Post...");
        MenuAddPost();
    }

}
