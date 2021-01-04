import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.nio.file.FileSystemNotFoundException;

public class MainClass {
    public static void main(String[] args) {
        MainClass objMainClass =
                new MainClass();
        objMainClass.Starter();
    }

    private void Starter() {
        FileCreator("questions.json");
        readJSONFile("questions");
        FileCreator("loss.json");
        readJSONFile("loss");

        ClassMainMenu objClassMainMenu =
                new ClassMainMenu();
        objClassMainMenu.MainMenu();
    }

    private static void FileCreator(String varStringFileName) {
        try {
            File objFile = new File("json/" + varStringFileName);

            if (!(objFile.exists())) {
                if (objFile.createNewFile()) {
                    OutputStreamWriter varObjWriter = new OutputStreamWriter(
                            new FileOutputStream("json/" + varStringFileName), "UTF-8");
                    varObjWriter.write("{}");
                    varObjWriter.close();

                    System.out.println("COMPUTER: Was created empty file \"" + varStringFileName + "\".");
                }
            }

        }
        catch (IOException e) {
            //e.printStackTrace();
            System.out.println("COMPUTER: Error. " + e.getMessage() + ". File \""
                    + varStringFileName + "\" was not created.");

            Exit();
        }
    }

    private void readJSONFile(String varStringTypeOfPost) {
        try {
            org.json.simple.parser.JSONParser objJSONParser =
                    new org.json.simple.parser.JSONParser();

            objJSONParser.parse(
                    new FileReader("json/" + varStringTypeOfPost + ".json"));

        } catch (IOException | ParseException | FileSystemNotFoundException e) {
            //e.printStackTrace();
            System.out.println("COMPUTER: Error of reading file \"" + varStringTypeOfPost + ".json\".");
            Exit();
        }
    }

    static void Exit() {
        System.out.println("COMPUTER: Exit from program...");
        System.exit(0);
    }

    static class TextTransfer implements ClipboardOwner {
        @Override
        public void lostOwnership(Clipboard clipboard, Transferable contents) {}
        void setData(String data){
            StringSelection stringSelection;
            stringSelection = new StringSelection(data);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, this);
        }
        String getData() throws IOException, UnsupportedFlavorException {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        }
    }
}
