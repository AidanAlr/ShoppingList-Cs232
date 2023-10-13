import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class User {

    //Creating the shopping list attribute
    private Item[] shoppingList;

    //Creating a counter to keep track of the number of items created
    int counter = 1;


    //getShoppingList() method returns the user's shopping list
    public Item[] getShoppingList() {
        return shoppingList;
    }

    //setShoppingList() method sets the user's shopping list length
    public void setShoppingList() {
        this.shoppingList = new Item[askListSize()];
    }
    //askListSize() method asks the user how many items they want to add to their list
    public int askListSize() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many items do you want to add to your list?");
        return sc.nextInt();
    }

    //addToShoppingList() method asks the user for the description, priority,
    // and cost of the item they want to add to their list and adds it to the
    // list if it is not already present
    public boolean addToShoppingList() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter item " + (counter) + " description: ");
        String description = sc.next();

        System.out.println("Please enter item " + (counter) + " priority: ");
        int priority = sc.nextInt();

        System.out.println("Please enter item " + (counter) + " cost: ");
        double cost = sc.nextDouble();

        Item item = new Item(description, priority, cost);

        if (!item.present(shoppingList)) {
            for (int j = 0; j < shoppingList.length; j++) {
                if (shoppingList[j] == null) {
                    shoppingList[j] = item;
                    System.out.println(item.description + " added to list!");
                    counter++;
                    return true;
                }
            }
        }
        System.out.println("Item already present in list! Please try again.");
            return false;
    }

    //printShoppingList() method prints the user's shopping list
    public void printShoppingList() {
        System.out.println("Purchase List: ");
        System.out.println("----------------------------------------    ");
        System.out.printf("%-10s %-10s %-10s%n", "| Description","| Priority", "| Price");
        System.out.println("----------------------------------------");

        String blank = " ";

        for (Item i : shoppingList) {
            if (i != null) {

                String descColumn = i.description + blank.repeat(13-i.description.length());
                String prioColumn = blank.repeat(5)+ i.priority + blank.repeat(6-Double.toString(i.priority).length());
                String costColumn = blank.repeat(5)+i.cost + blank.repeat(6-Double.toString(i.cost).length());

                System.out.println("|" + descColumn+ "|" + prioColumn + "|" + costColumn);
            }
        }
    }

    public void sortShoppingList(){
    Arrays.sort(shoppingList, Comparator.comparingInt(i -> i.priority));
    printShoppingList();
    }


    public Item[] makePurchases(double budget){
        Item[] purchases = new Item[10];

        int ctr = 0;

        System.out.println("Your Budget is: " + budget);

        for (Item i: shoppingList){

            if (i.cost <= budget){
                purchases[ctr] = i;
                budget -= i.cost;
                System.out.println("Priority "+i.priority+" Item Purchased " + i.description + " for " + i.cost);
                System.out.println("Budget:" + budget);
                ctr++;

            }
            else {
                System.out.println("Can't afford " + i.description + " Price: " + i.cost);
            }
        }

        purchases = removeNull(purchases);
        System.out.println(Arrays.toString(purchases));
        return purchases;
    }

    private Item[] removeNull(Item[] list){

        // Count the number of non-null items
        int ctr = 0;
        for (Item i: list){
            if (i != null){
                ctr++;
            }
        }
        // Create a new list of the same size as the number of non-null items
        Item[] newList = new Item[ctr];
        // Copy the non-null items to the new list
        ctr = 0;
        for (Item i: list){
            if (i != null){
                newList[ctr] = i;
                ctr++;
            }
        }
        return newList;
    }

}
