import jdk.jshell.execution.Util;

import javax.swing.text.Utilities;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class User {

    //Creating the shopping list attribute
    private Item[] shoppingList;
    private Item[] purchasedList;
    private Item[] notPurchasedList = new Item[10];


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

    public Item[] getPurchasedList(){
        return purchasedList;
    }

    public Item[] getNotPurchasedList(){
        return notPurchasedList;
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
    public void printList(Item[] list) {
        System.out.println("----------------------------------");
        System.out.printf("%-10s %-10s %-10s%n", "| Description","| Priority", "| Price |");
        System.out.println("----------------------------------");

        String blank = " ";

        for (Item i : list) {
            if (i != null) {

                String descColumn = blank.repeat(5)+i.description + blank.repeat(8-i.description.length());
                String prioColumn = blank.repeat(5)+ i.priority + blank.repeat(7-Double.toString(i.priority).length());
                String costColumn = blank.repeat(2)+i.cost + blank.repeat(5-Double.toString(i.cost).length());

                System.out.println("|" + descColumn+ "|" + prioColumn + "|" + costColumn + "|");
            }
        }
    }

    private void sortShoppingList(){
    Arrays.sort(shoppingList, Comparator.comparingInt(i -> i.priority));
    }


    public void makePurchases(double budget){

        sortShoppingList();
        Item[] purchases = new Item[10];

        int ctr = 0;

        System.out.println("Your initial budget is: " + budget);

        for (Item i: shoppingList){

            if (i.cost <= budget){
                purchases[ctr] = i;
                budget -= i.cost;
                System.out.println("Purchased " + i.description +"(Priority "+i.priority+ ") for " + i.cost + " -> " + budget + " remaining");
                ctr++;

            }
            else {
                System.out.println("Can't afford " + i.description + " for " + i.cost + " -> "+ budget + " remaining");
            }
        }

        purchases = removeNull(purchases);
        purchasedList = purchases;

        for(int i=0; i<shoppingList.length; i++ ){
            if (!shoppingList[i].present(purchases)){
                notPurchasedList[i] = shoppingList[i];
            }
        }
        notPurchasedList = removeNull(notPurchasedList);
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
