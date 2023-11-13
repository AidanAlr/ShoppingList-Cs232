import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Creating the user
        User user = new User();

        // Creating the shopping list
        user.setShoppingList();

        // Getting the number of items to add to the list
        int itemsToAdd = user.getShoppingList().length;

        // Adding items to the list
        while (itemsToAdd > 0) {
            // If the user successfully adds an item, decrement the itemsToAdd counter
            if (user.addItemToShoppingList()) {
                itemsToAdd--;
            }
        }

        // Printing the sorted shopping list
        user.sortShoppingList();
        System.out.println("Shopping List: ");
        user.printList(user.getShoppingList());

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
        System.out.println("Purchases (" + user.getPurchasedList().length + " items)");
        user.printList(user.getPurchasedList());

        // Printing not purchased items
        System.out.println("Not purchased (" + user.getNotPurchasedList().length + " items)");
        user.printList(user.getNotPurchasedList());
    }
}
