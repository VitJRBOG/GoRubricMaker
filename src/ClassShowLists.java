import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner;

class ClassShowLists {

    void MenuShowLists() {
        System.out.println("COMPUTER: [Main menu -> Show lists -> ..] 1 == Show list of questions.");
        System.out.println("COMPUTER: [Main menu -> Show lists -> ..] 2 == Show list of loss.");
        System.out.println("COMPUTER: [Main menu -> Show lists -> ..] 0 == Step back.");

        Scanner objScanner =
                new Scanner(System.in);

        System.out.print("USER: [.. -> Show lists -> ] ");
        String varStringUserAnswer = objScanner.nextLine();

        if (varStringUserAnswer.equals("0")) {
            ClassMainMenu objClassMainMenu =
                    new ClassMainMenu();
            objClassMainMenu.MainMenu();
        } else {
            if (varStringUserAnswer.equals("1")) {
                JSONArray objJSONArray = readJSONFile("questions");
                operationShowList("questions", objJSONArray);
            } else {
                if (varStringUserAnswer.equals("2")) {
                    JSONArray objJSONArray = readJSONFile("loss");
                    operationShowList("loss", objJSONArray);
                } else {
                    System.out.println("COMPUTER: Unknown operation. Retry query...");
                    MenuShowLists();
                }
            }
        }
    }

    private void operationShowList(String varStringTypeOfPost, JSONArray objJSONArray) {

        System.out.println("COMPUTER: [Main menu -> Show lists -> List of " + varStringTypeOfPost +
                "] Begin of list.");

        System.out.println("");

        for (int i = 0; i < objJSONArray.size(); i++) {
            JSONObject objJSONObject = (JSONObject) objJSONArray.get(i);

            System.out.println(objJSONObject.get("body"));

            if (!(objJSONObject.get("photo").equals(""))) {

                JSONArray objJSONArrayPhoto = (JSONArray) objJSONObject.get("photo");

                for (int j = 0; j < objJSONArrayPhoto.size(); j++ ) {
                    System.out.println(objJSONArrayPhoto.get(j));
                }
            }

            if (!(objJSONObject.get("author").equals(""))) {
                System.out.println(objJSONObject.get("author"));
            }

            System.out.println("");
        }

        System.out.println("COMPUTER: [Main menu -> Show lists -> List of " + varStringTypeOfPost +
                "] End of list.");

        System.out.println("COMPUTER: Return to Menu Show List...");

        MenuShowLists();
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
                    "Return to Menu Show Lists...");
            MenuShowLists();
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
                        "Return to Menu Show Lists...");
                MenuShowLists();
            }
        }

        return objJSONArray;
    }

}
