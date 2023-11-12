import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.text.DecimalFormat;

public class User {

    private static final DecimalFormat df = new DecimalFormat("0.00");

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

    //setShoppingList() method sets the user's empty shopping list
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
        while (true){
        try{
        Scanner sc = new Scanner(System.in);
        System.out.println("How many items do you want to add to your list?");
        return sc.nextInt();
        }
        catch (Exception e){
            System.out.println("Invalid integer.");
        }
    }}

    //addToShoppingList() method asks the user for the description, priority,
    // and cost of the item they want to add to their list and adds it to the
    // list if it is not already present
    public boolean addItemToShoppingList() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter item category(food, clothing, or home): ");
        String category = sc.nextLine();

        System.out.println("Please enter item " + (counter) + " description: ");
        String description = sc.nextLine();

        int priority;
        while (true) {
            System.out.println("Please enter item " + (counter) + " priority: ");
            String input = sc.nextLine();
            if (input.matches("^[0-9].*")){
                priority = Integer.parseInt(input);
                break;
            }
            else{
                System.out.println("Invalid priority!");
            }
        }

        double cost;
        while (true) {
            System.out.println("Please enter item " + (counter) + " cost: ");
            String input = sc.nextLine();
            if (input.matches("(\\-?\\d*\\.?\\d+)")) {
                cost = Double.parseDouble(input);
                break;
            }
            else{
                System.out.println("Invalid price!");
            }
        }






        if (item.notPresent(shoppingList)) {
            for (int j = 0; j < shoppingList.length; j++) {
                if (shoppingList[j] == null) {
                    shoppingList[j] = item;
                    System.out.println(item.getDescription() + " added to list!");
                    counter++;
                    return true;
                }
            }
        }
        System.out.println("You have entered a non unique item. Please try again.");
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

                String descColumn = blank +i.getDescription() + blank.repeat(12- i.getDescription().length());
                String priorColumn = blank.repeat(5)+ i.getPriority() + blank.repeat(7-Double.toString(i.getPriority()).length());
                String costColumn = blank.repeat(1)+ df.format(i.getCost()) + blank.repeat(5-Double.toString(i.getCost()).length());

                System.out.println("|" + descColumn+ "|" + priorColumn + "|" + costColumn + "|");
            }
        }
    }

    public void sortShoppingList(){
    Arrays.sort(shoppingList, Comparator.comparingInt(i -> i.getPriority()));
    }


    public void makePurchases(double budget){

        sortShoppingList();
        Item[] purchases = new Item[10];

        int ctr = 0;

        System.out.println("Your initial budget is: " + budget);

        for (Item i: shoppingList){

            if (i.getCost() <= budget){
                purchases[ctr] = i;
                budget -= i.getCost();
                System.out.println("Purchased " + i.getDescription() +"(Priority "+ i.getPriority() + ") for " + df.format(i.getCost()) + " -> " + df.format(budget) + " remaining");
                ctr++;

            }
            else {
                System.out.println("Can't afford " + i.getDescription() + " for " + df.format(i.getCost()) + " -> "+ df.format(budget) + " remaining");
            }
        }

        System.out.println("Leftover budget: " + df.format(budget));

        purchases = removeNull(purchases);
        purchasedList = purchases;

        for(int i=0; i<shoppingList.length; i++ ){
            if (shoppingList[i].notPresent(purchases)){
                notPurchasedList[i] = shoppingList[i];
            }
        }
        notPurchasedList = removeNull(notPurchasedList);
    }

//    Removes null objects from an array
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
