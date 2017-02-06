import jdk.nashorn.internal.ir.debug.JSONWriter;

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

    private void operationAddQuestion() {
        String varStringTypeOfPost = "question";

        ModelPost objModelPost =
                new ModelPost();

        objModelPost = setBody(objModelPost, varStringTypeOfPost);
        objModelPost = setPhoto(objModelPost, varStringTypeOfPost);
        objModelPost = setAuthor(objModelPost, varStringTypeOfPost);

        writingPostToJSON(objModelPost, varStringTypeOfPost);
    }

    private void operationAddLoss() {
        String varStringTypeOfPost = "loss";

        ModelPost objModelPost =
                new ModelPost();

        objModelPost = setBody(objModelPost, varStringTypeOfPost);
        objModelPost = setPhoto(objModelPost, varStringTypeOfPost);
        objModelPost = setAuthor(objModelPost, varStringTypeOfPost);

        writingPostToJSON(objModelPost, varStringTypeOfPost);
    }

    private void writingPostToJSON(ModelPost objModelPost, String varStringTypeOfPost) {



        MenuAddPost();
    }

    private ModelPost setBody(ModelPost objModelPost, String varStringTypeOfPost) {
        System.out.println("COMPUTER: [.. -> Add post -> Add " + varStringTypeOfPost + "] " +
                "Copy content for body and press Enter. " +
                "Enter '0' for cancel.");
        System.out.print("USER: [.. -> Add " + varStringTypeOfPost + " -> Body] ");
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

                System.out.println("COMPUTER: [.. Add " + varStringTypeOfPost + " -> Body] " + "\n" +
                        objModelPost.getNumber() + objModelPost.getBody());

            } catch (IOException | UnsupportedFlavorException e) {
                //e.printStackTrace();
                System.out.println("COMPUTER: Error. Retry query...");
                return setBody(objModelPost, varStringTypeOfPost);
            }
        }

        return objModelPost;
    }

    private ModelPost setPhoto(ModelPost objModelPost, String varStringTypeOfPost) {
        System.out.println("COMPUTER: [.. -> Add post -> Add " + varStringTypeOfPost + "] " +
                "Enter count of photos and press Enter. " +
                "Enter \"00\" for cancel.");
        System.out.print("USER: [.. -> Add " + varStringTypeOfPost + " -> Photo] ");
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

                        System.out.println("COMPUTER: [.. -> Add " + varStringTypeOfPost + " -> Photo] " +
                                "Copy link to photo and press Enter. Enter \"00\" for cancel.");

                        for (int i = 0; i < arrayStringPhoto.length; i++) {

                            System.out.print("USER: [.. -> Add " + varStringTypeOfPost +
                                    " -> Photo -> №" + (i + 1) + "] ");
                            varStringUserAnswer = objScanner.nextLine();

                            if (varStringUserAnswer.equals("00")) {
                                System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                                MenuAddPost();
                            }

                            arrayStringPhoto[i] = "- " + objTextTransfer.getData();
                        }

                        objModelPost.setPhoto(arrayStringPhoto);

                        System.out.println("COMPUTER: [.. Add " + varStringTypeOfPost + " -> Photo] ");
                        for (String varString : objModelPost.getPhoto()) {
                            System.out.println(varString);
                        }

                    } catch (IOException | UnsupportedFlavorException e) {
                        //e.printStackTrace();
                        System.out.println("COMPUTER: Error. Retry query...");
                        return setPhoto(objModelPost, varStringTypeOfPost);
                    }
                } else {
                    if (varIntUserAnswer == 0) {
                        objModelPost.setPhoto(null);
                        return objModelPost;
                    } else {
                        System.out.println("COMPUTER: Invalid value. Retry query...");
                        return setPhoto(objModelPost, varStringTypeOfPost);
                    }
                }
            } catch (ClassCastException | NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("COMPUTER: Error. Retry query...");
                return setPhoto(objModelPost, varStringTypeOfPost);
            }
        }
        return objModelPost;
    }

    private ModelPost setAuthor(ModelPost objModelPost, String varStringTypeOfPost) {

        System.out.println("COMPUTER: [.. -> Add post -> Add " + varStringTypeOfPost + "] " +
                "Enter '1', if need signature, or '0', if not. " +
                "Enter \"00\" for cancel.");
        System.out.print("USER: [.. -> Add " + varStringTypeOfPost + " -> Author] ");
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
                        System.out.println("COMPUTER: [.. -> Add " + varStringTypeOfPost + " -> Author -> Link] " +
                                "Copy link to author and press Enter. Enter \"00\" for cancel.");
                        System.out.print("USER: [.. -> Add " + varStringTypeOfPost + " -> Author -> Link] ");
                        varStringUserAnswer = objScanner.nextLine();

                        if (varStringUserAnswer.equals("00")) {
                            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                            MenuAddPost();
                        }

                        MainClass.TextTransfer objTextTransfer =
                                new MainClass.TextTransfer();
                        objModelPost.setLinkAuthor(objTextTransfer.getData());

                        System.out.println("COMPUTER: [.. -> Add " + varStringTypeOfPost + " -> Author -> Name] " +
                                "Copy name of author and press Enter. Enter \"00\" for cancel.");
                        System.out.print("USER: [.. -> Add " + varStringTypeOfPost + " -> Author -> Name] ");
                        varStringUserAnswer = objScanner.nextLine();

                        if (varStringUserAnswer.equals("00")) {
                            System.out.println("COMPUTER: Operation was canceled. Return to Menu Add Post...");
                            MenuAddPost();
                        }
                        objModelPost.setNameAuthor(objTextTransfer.getData());

                        System.out.println("COMPUTER: [.. Add " + varStringTypeOfPost + " -> Author] " +
                                "\n" + objModelPost.getAuthor());

                    }
                    catch (IOException | UnsupportedFlavorException e) {
                        //e.printStackTrace();
                        System.out.println("COMPUTER: Error. Retry query...");
                        return setAuthor(objModelPost, varStringTypeOfPost);
                    }
                } else {
                    System.out.println("COMPUTER: Error. Retry query...");
                    return setAuthor(objModelPost, varStringTypeOfPost);
                }
            }
        }
        return objModelPost;
    }
}
