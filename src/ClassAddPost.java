import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner;

class ClassAddPost {

    void MenuAddPost() {
        System.out.println("COMPUTER: [Main menu -> Add post -> ..] 1 == Add question.");
        System.out.println("COMPUTER: [Main menu -> Add post -> ..] 2 == Add loss.");
        System.out.println("COMPUTER: [Main menu -> Add post -> ..] 0 == Step back.");

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
                JSONArray objJSONArray = readJSONFile("questions");
                operationAddQuestion(objJSONArray);
            } else {
                if (varStringUserAnswer.equals("2")) {
                    JSONArray objJSONArray = readJSONFile("loss");
                    operationAddLoss(objJSONArray);
                } else {
                    System.out.println("COMPUTER: Unknown operation. Retry query...");
                    MenuAddPost();
                }
            }
        }

    }

    private void operationAddQuestion(JSONArray objJSONArray) {
        String varStringTypeOfPost = "questions";

        ModelPost objModelPost =
                new ModelPost();

        int varIntArraySize = objJSONArray.size();

        objModelPost.setCategoryNumber(1);
        objModelPost.setPostNumber(varIntArraySize + 1);

        objModelPost = setBody(objModelPost, varStringTypeOfPost);
        objModelPost = setPhoto(objModelPost, varStringTypeOfPost);
        objModelPost = setAuthor(objModelPost, varStringTypeOfPost);

        askForWriting(objModelPost, varStringTypeOfPost, objJSONArray);
    }

    private void operationAddLoss(JSONArray objJSONArray) {
        String varStringTypeOfPost = "loss";

        ModelPost objModelPost =
                new ModelPost();

        int varIntArraySize = objJSONArray.size();

        objModelPost.setCategoryNumber(2);
        objModelPost.setPostNumber(varIntArraySize + 1);

        objModelPost = setBody(objModelPost, varStringTypeOfPost);
        objModelPost = setPhoto(objModelPost, varStringTypeOfPost);
        objModelPost = setAuthor(objModelPost, varStringTypeOfPost);

        askForWriting(objModelPost, varStringTypeOfPost, objJSONArray);
    }

    private JSONArray readJSONFile(String varStringTypeOfPost) {
        JSONArray objJSONArray =
                new JSONArray();

        String varString = "";

        try {
            Scanner objScanner =
                    new Scanner(new File("json/" + varStringTypeOfPost + ".json"));
            varString = objScanner.next();
            objScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("COMPUTER: Error. File \"" + varStringTypeOfPost + ".json\" not found. " +
                    "Return to Menu Add Post...");
        }

        if (varString.length() > 5) {
            try {
                JSONParser objJSONParser =
                        new JSONParser();

                objJSONArray = (JSONArray) objJSONParser.parse(
                        new FileReader("json/" + varStringTypeOfPost + ".json"));

            } catch (IOException | ParseException | FileSystemNotFoundException | ClassCastException e) {
                e.printStackTrace();
                System.out.println("COMPUTER: Error of reading file \"" + varStringTypeOfPost + ".json\". " +
                        "Return to Menu Add Post...");
            }
        }

        return objJSONArray;
    }

    private void askForWriting(ModelPost objModelPost, String varStringTypeOfPost, JSONArray objJSONArray) {
        System.out.println("COMPUTER: [Main menu -> Add post -> Add questions]");

        System.out.println(objModelPost.getBody());

        try {
            String[] arrayStringPhoto = objModelPost.getPhoto();

            for (String varString : arrayStringPhoto) {
                System.out.println(varString);
            }
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }

        System.out.println(objModelPost.getAuthor());

        System.out.println("COMPUTER: [Main menu -> Add post -> Add " + varStringTypeOfPost +"] " +
                "Write this to file \"" + varStringTypeOfPost +".json\"? (1/0)");

        System.out.print("USER: [.. -> .. -> Add " + varStringTypeOfPost + "] ");

        Scanner objScanner =
                new Scanner(System.in);
        String varStringUsersAnswer = objScanner.nextLine();

        if (varStringUsersAnswer.equals("1")) {
            writingPostToJSON(objModelPost, varStringTypeOfPost, objJSONArray);
        } else {
            if (varStringUsersAnswer.equals("0")) {
                System.out.println("COMPUTER: Cancel of writing. Return to Menu Add Post...");
                MenuAddPost();
            } else {
                System.out.println("COMPUTER: Unknown operation. Retry query...");
                askForWriting(objModelPost, varStringTypeOfPost, objJSONArray);
            }
        }
    }

    private void writingPostToJSON(ModelPost objModelPost, String varStringTypeOfPost, JSONArray objJSONArray) {

        JSONObject objJSONObjectNew =
                new JSONObject();

        int varIntArraySize = objJSONArray.size();

        objJSONObjectNew.put("id", varIntArraySize + 1);
        objJSONObjectNew.put("body", objModelPost.getBody());

        try {
            String[] arrayStringContent = objModelPost.getPhoto();

            JSONArray objJSONArrayPhoto =
                    new JSONArray();

            for (String varString : arrayStringContent) {
                objJSONArrayPhoto.add(varString);
            }

            objJSONObjectNew.put("photo", objJSONArrayPhoto);
        } catch (NullPointerException e) {
            //e.printStackTrace();
            objJSONObjectNew.put("photo", "");
        }


        objJSONObjectNew.put("author", objModelPost.getAuthor());

        try {
            FileWriter objFileWriter =
                    new FileWriter("json/" + varStringTypeOfPost + ".json");
            objFileWriter.write("");
            if (objModelPost.getPostNumber() > 1) {
                String varStringJSONArray = objJSONArray.toJSONString();

                if (varStringJSONArray.charAt(0) == '[' &&
                        varStringJSONArray.charAt(varStringJSONArray.length() - 1) == ']') {
                    varStringJSONArray = varStringJSONArray.substring(1, varStringJSONArray.length() - 1);
                }

                objFileWriter.write("[" + varStringJSONArray + ", " +
                        objJSONObjectNew.toJSONString() + "]");
            } else {
                objFileWriter.write("[" + objJSONObjectNew.toJSONString() + "]");
            }
            objFileWriter.flush();
            objFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("COMPUTER: Error of writing file \"" + varStringTypeOfPost + ".json\". " +
                    "Return to Menu Add Post...");
        }

        System.out.println("COMPUTER: Post was successfully written. Return to Menu Add Post.");

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
                objModelPost.setBody(objModelPost.getNumber() + "" + objTextTransfer.getData());

                System.out.println("COMPUTER: [.. Add " + varStringTypeOfPost + " -> Body] " + "\n" +
                        objModelPost.getBody());

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
                                new String[varIntUserAnswer + 1];
                        arrayStringPhoto[0] = "Фото:";

                        MainClass.TextTransfer objTextTransfer =
                                new MainClass.TextTransfer();

                        System.out.println("COMPUTER: [.. -> Add " + varStringTypeOfPost + " -> Photo] " +
                                "Copy link to photo and press Enter. Enter \"00\" for cancel.");

                        for (int i = 1; i < arrayStringPhoto.length; i++) {

                            System.out.print("USER: [.. -> Add " + varStringTypeOfPost +
                                    " -> Photo -> №" + i + "] ");
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
