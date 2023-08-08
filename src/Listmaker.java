import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileListmaker {
    private static ArrayList<String> itemList = new ArrayList<>();
    private static boolean needToBeSaved = false;
    private static String currentFileName ="";


    public static void main(String[] args) {
        Scanner in = new Scanner( System.in );
        boolean actionChoose= true;
        while (actionChoose){
            menu();
            String userInput = SafeInput.getRegExString (in,"Enter your choice: ","[AaDdVvOoSsCcQq]" );
            String choice = in.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                   addItem ();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "V":
                    viewList();
                    break;
                case"O":
                   openList ();
                    break;

                case"S":
                    saveList (in);
                    break;
                case"C":
                    clearList ();
                    break;
                case "Q": SafeInput.getYNConfirm (in,"wanna quit! Data gonna lost");
                   actionChoose = false;
                    break;
            }
        }
    }
    private static void menu() {
        System.out.println("Menu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("V - View the list");
        System.out.println("O - Open the list from disk");
        System.out.println("S - Save the current list to disk");
        System.out.println("C - clear the list");
        System.out.println("Q - Quit the program");
    }

    private static void addItem() {
        Scanner in = new Scanner( System.in );
        System.out.print("Enter the item to add: ");
        String item = in.nextLine();
        itemList.add(item);
        needToBeSaved = true;
        System.out.println("Item '" + item + "' added successfully.");
    }

    private static void deleteItem() {
        Scanner in = new Scanner( System.in );
        System.out.print("Enter the number of the item to delete: ");
        int index = in.nextInt();
        in.nextLine();
        if (index >= 1 && index <= itemList.size()) {
            String deletedItem = itemList.remove(index - 1);
            System.out.println("Item " + deletedItem + "deleted successfully");
            needToBeSaved =true;
        } else {
            System.out.println("Invalid input. Try again!");
        }
    }

    private static void viewList() {
        if (itemList.isEmpty ()){
            System.out.println ("Empty List! Please add items to view");
        }else {
            System.out.println ("Current List  ");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
        }
    }
    private static void clearList() {
        itemList.clear();
        needToBeSaved = true;
        System.out.println("List cleared.");
    }
    private static void saveList(Scanner scanner) {
        if (currentFileName.isEmpty()) {
            System.out.print("Enter the filename to save: ");
            currentFileName = scanner.nextLine();
        }
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");
        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));
            for(String rec :itemList)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            needToBeSaved = false;
            System.out.println("List saved successfully.");
        } catch (IOException ex) {
            System.out.println("Error in saving file: " + ex.getMessage());
        }
    }
    public static void openList()
    {
        Scanner in = new Scanner ( System.in );
        System.out.print("Enter the filename to open: ");
        String fileName= in.nextLine ();

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            itemList.clear();
            String line;
            while ((line = reader.readLine()) != null) {
               itemList.add(line);
            }
            reader.close();
            needToBeSaved = false;
            currentFileName = fileName;
            System.out.println("List opened successfully.");
        } catch (IOException ex) {
            System.out.println("Error opening file: " + ex.getMessage());
        }
    }
}


       
