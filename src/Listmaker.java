import java.util.ArrayList;
import java.util.Scanner;

public class Listmaker {
    private static ArrayList<String> itemList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner( System.in);
        String actionChoose;
        do {
            menu();
            String userInput = SafeInput.getRegExString (in,"Enter your choice: ","[AaDdPpQq]" );
            actionChoose = in.nextLine().toUpperCase();
            switch (actionChoose) {
                case "A":
                   addItem ();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    quit ();
                    break;
            }
        } while (true);
    }
    private static void menu() {
        System.out.println("\nMenu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
    }

    private static void addItem() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the item to add: ");
        String item = in.nextLine();
        itemList.add(item);
        System.out.println("Item '" + item + "' added successfully.");
    }

    private static void deleteItem() {
        Scanner in = new Scanner(System.in );
        System.out.print("Enter the number of the item to delete: ");
        int index = in.nextInt();
        in.nextLine();
        if (index >= 1 && index <= itemList.size()) {
            String deletedItem = itemList.remove(index - 1);
            System.out.println("Item " + deletedItem + "deleted successfully");
        } else {
            System.out.println("Invalid input. Try again!");
        }
    }

    private static void printList() {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }
    private static void quit () {
        Scanner in = new Scanner(System.in );
        Boolean quit = SafeInput.getYNConfirm ( in,"Do you want to quit ?" );
        in.nextLine ();
        return;
    }
}





