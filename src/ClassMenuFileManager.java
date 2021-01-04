import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner;

class ClassMenuFileManager {

    void MenuFileManager() {
        System.out.println("COMPUTER: [Main menu -> File manager -> ..] 1 == Clear files.");
        System.out.println("COMPUTER: [Main menu -> File manager -> ..] 2 == Export files.");
        System.out.println("COMPUTER: [Main menu -> File manager -> ..] 0 == Step back.");

        Scanner objScanner =
                new Scanner(System.in);

        System.out.print("USER: [.. -> File manager -> ] ");
        String varStringUserAnswer = objScanner.nextLine();

        if (varStringUserAnswer.equals("0")) {
            ClassMainMenu objClassMainMenu =
                    new ClassMainMenu();
            objClassMainMenu.MainMenu();
        } else {
            if (varStringUserAnswer.equals("1")) {
                operationClearFiles();
            } else {
                if (varStringUserAnswer.equals("2")) {
                    operationExportFiles();
                } else {
                    System.out.println("COMPUTER: Unknown operation. Retry query...");
                    MenuFileManager();
                }
            }
        }
    }

    private void operationClearFiles() {

        System.out.println("COMPUTER: [Main menu -> File manager -> Clean files] Files \"questions.json\" and " +
                "\"loss.json\" will be clean. " +
                "Are you sure? (1/0)");

        System.out.print("USER: [.. -> .. -> Clean files] ");

        Scanner objScanner =
                new Scanner(System.in);
        String varStringUsersAnswer = objScanner.nextLine();

        if (varStringUsersAnswer.equals("0")) {
            System.out.println("COMPUTER: Cancel of cleaning. Return to Menu File Manager...");
            MenuFileManager();
        } else {
            if (varStringUsersAnswer.equals("1")) {
                String[] arrayStringFiles = {
                        "questions.json", "loss.json"
                };

                for (String varStringFileName : arrayStringFiles) {
                    try {
                        OutputStreamWriter varObjWriter = new OutputStreamWriter(
                                new FileOutputStream("json/" + varStringFileName), "UTF-8");
                        varObjWriter.write("{}");
                        varObjWriter.close();

                        System.out.println("COMPUTER: Was cleaned file \"" + varStringFileName + "\".");
                    } catch (IOException e) {
                        e.printStackTrace();

                        System.out.println("COMPUTER: Error of reading of files. Return to Menu File Manager...");
                    }
                }
            } else {
                System.out.println("COMPUTER: Unknown operation. Retry query...");
                operationClearFiles();
            }
        }

        System.out.println("COMPUTER: Return to Menu File Manager...");
        MenuFileManager();
    }

    private void operationExportFiles() {

        JSONArray objJSONArrayListOfQuestions = readJSONFile("questions");
        FileCreator("questions.txt", objJSONArrayListOfQuestions);

        JSONArray objJSONArrayListOfLoss = readJSONFile("loss");
        FileCreator("loss.txt", objJSONArrayListOfLoss);

        System.out.println("COMPUTER: Return to Menu File Manager...");
        MenuFileManager();
    }

    private void FileCreator(String varStringFileName, JSONArray objJSONArray) {

        String varStringListOfPosts = "";

        for (int i = 0; i < objJSONArray.size(); i++) {
            JSONObject objJSONObject = (JSONObject) objJSONArray.get(i);

            varStringListOfPosts += objJSONObject.get("body") + "\n";

            if (!(objJSONObject.get("photo").equals(""))) {

                JSONArray objJSONArrayPhoto = (JSONArray) objJSONObject.get("photo");

                for (int j = 0; j < objJSONArrayPhoto.size(); j++ ) {
                    varStringListOfPosts += objJSONArrayPhoto.get(j) + "\n";
                }
            }

            if (!(objJSONObject.get("author").equals(""))) {
                varStringListOfPosts += objJSONObject.get("author") + "\n";
            }

            varStringListOfPosts += "\n";
        }

        try {
            File objFile = new File("output/" + varStringFileName);

            if (!(objFile.exists())) {
                if (objFile.createNewFile()) {
                    OutputStreamWriter varObjWriter = new OutputStreamWriter(
                            new FileOutputStream("output/" + varStringFileName), "UTF-8");
                    varObjWriter.write(varStringListOfPosts);
                    varObjWriter.close();

                    System.out.println("COMPUTER: Was created file \"" + varStringFileName + "\".");
                }
            } else {
                OutputStreamWriter varObjWriter = new OutputStreamWriter(
                        new FileOutputStream("output/" + varStringFileName), "UTF-8");
                varObjWriter.write(varStringListOfPosts);
                varObjWriter.close();

                System.out.println("COMPUTER: File \"" + varStringFileName + "\" was rewritten.");
            }

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("COMPUTER: Error. " + e.getMessage() + ". File \""
                    + varStringFileName + "\" was not created.");
            MenuFileManager();
        }
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
                    "Return to Menu File Manager...");
            MenuFileManager();
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
                        "Return to Menu File Manager...");
                MenuFileManager();
            }
        }

        return objJSONArray;
    }

}
