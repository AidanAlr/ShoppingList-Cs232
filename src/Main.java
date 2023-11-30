import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // Creating the user
        User user = new User();
        MyPrinter myPrinter = new MyPrinter();
        // Creating the shopping list
        user.setShoppingList();

        // Getting the number of items to add to the list
        int itemsToAdd = user.getShoppingList().length;

        // Adding items to the list
        while (itemsToAdd > 0) {
            // If the user successfully adds an item, decrement the itemsToAdd counter
            try {
                user.addItemToSL(user.createItem());
                itemsToAdd--;
                }
            catch (NonUniqueException e){
                System.out.println("Please enter a new one!");
            }
        }

        // Printing the sorted shopping list
        user.sortShoppingList();
        System.out.println("Shopping List: ");
        myPrinter.printList(user.getShoppingList());

        // Prompting the user to enter their budget (default is $59)
        System.out.println("Please enter your budget (leave blank for default $59) ->");
        Scanner sc = new Scanner(System.in);

        // Making Purchases, and giving a default budget if the user doesn't enter one
        String budgetEntry = sc.nextLine();
        double budget;
        if (budgetEntry.isEmpty()) {
            budget = 59.00;  // Default budget if the user doesn't enter one
        } else {
            budget = Double.parseDouble(budgetEntry);
        }
        user.makePurchases(budget);

        // Printing Purchases
        System.out.println("Purchased (" + user.getPurchasedList().size() + " items)");
        myPrinter.printList(user.getPurchasedList());

        // Printing not purchased items
        System.out.println("Not purchased items");
        myPrinter.printNonPurchasedList(user.getPurchasedList());


//      File manager logic
        FileManager fm = new FileManager();

        fm.createFile("PurchasedList.csv");
        fm.createFile("NotPurchasedList.csv");

        fm.WriteListToFile(user.getPurchasedList(), "PurchasedList.csv");
        fm.WriteNotPurchasedListToFile(user.getPurchasedList(), "NotPurchasedList.csv");
    }
}
