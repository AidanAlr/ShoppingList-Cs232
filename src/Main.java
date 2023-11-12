import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Creating the user
        User user = new User();
        //Creating the shopping list
        user.setShoppingList();


        //Getting the number of items to add to the list
        int itemsToAdd = user.getShoppingList().length;
        //Adding items to the list
        while (itemsToAdd > 0) {
            if (user.addItemToShoppingList()) {
                itemsToAdd--;
            }

        }
        //Printing the list
        System.out.println("Shopping List: ");
        user.sortShoppingList();
        user.printList(user.getShoppingList());

        System.out.println("Please enter your budget(leave blank for default $59)->");
        Scanner sc = new Scanner(System.in);

        // Making Purchases, and giving default
        String s = sc.nextLine();
        double budget;
        if (s.isEmpty()) {
            budget = 59.00;
        }
        else {
            budget = Double.parseDouble(s);
        }
        user.makePurchases(budget);

        //Printing Purchases
        System.out.println("Purchases (" + user.getPurchasedList().length+" items)");
        user.printList(user.getPurchasedList());

//        Printing not purchased
        System.out.println("Not purchased ("+ user.getNotPurchasedList().length+" items)");
        user.printList(user.getNotPurchasedList());

    }
}
