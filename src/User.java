import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.text.DecimalFormat;

public class User {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    //Creating the shopping list attribute
    private ItemParent[] shoppingList;
    private ItemParent[] purchasedList;
    private ItemParent[] notPurchasedList = new ItemParent[10];


    //Creating a counter to keep track of the number of items created
    int counter = 1;


    //getShoppingList() method returns the user's shopping list
    public ItemParent[] getShoppingList() {
        return shoppingList;
    }

    //setShoppingList() method sets the user's empty shopping list
    public void setShoppingList() {
        this.shoppingList = new ItemParent[askListSize()];
    }

    public ItemParent[] getPurchasedList(){
        return purchasedList;
    }

    public ItemParent[] getNotPurchasedList(){
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
        String category = sc.nextLine().toLowerCase();


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

        switch(category){
            case "home":
                addItemToSL(new HomeItem(description, priority, cost));
                return true;
            case "food":
                addItemToSL(new FoodItem(description, priority, cost));
                return true;
            case "clothing":
                addItemToSL(new ClothingItem(description, priority, cost));
                return true;
            default:
                System.out.println("Incorrect Category");
                return false;

        }
    }
    public void addItemToSL(ItemParent item){
        if (item.notPresent(shoppingList)) {
            for (int j = 0; j < shoppingList.length; j++) {
                if (shoppingList[j] == null) {
                    shoppingList[j] = item;
                    System.out.println(item.getDescription() + " added to list!");
                    counter++;
                    return;
                }
            }
        }
        System.out.println("You have entered a non unique item. Please try again.");
    }

    //printShoppingList() method prints the user's shopping list
    public void printList(ItemParent[] list) {
        System.out.println("---------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s%n","| Category", "| Description","| Priority", "| Price |");
        System.out.println("---------------------------------------------");

        String blank = " ";

        for (ItemParent i : list) {
            if (i != null) {
                String categoryColumn = blank+ String.valueOf(i.getClass()).split(" ")[1] + blank.repeat(7- i.getDescription().length());
                String descColumn = blank +i.getDescription() + blank.repeat(12- i.getDescription().length());
                String priorColumn = blank.repeat(5)+ i.getPriority() + blank.repeat(7-Double.toString(i.getPriority()).length());
                String costColumn = blank.repeat(1)+ df.format(i.getCost()) + blank.repeat(5-Double.toString(i.getCost()).length());

                System.out.println(categoryColumn+ "|" + descColumn+ "|" + priorColumn + "|" + costColumn + "|");
            }
        }
    }

    public void sortShoppingList(){
    Arrays.sort(shoppingList, Comparator.comparingInt(i -> i.getPriority()));
    }


    public void makePurchases(double budget){

        sortShoppingList();
        ItemParent[] purchases = new ItemParent[10];

        int ctr = 0;

        System.out.println("Your initial budget is: " + budget);

        for (ItemParent i: shoppingList){

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
    private ItemParent[] removeNull(ItemParent[] list){

        // Count the number of non-null items
        int ctr = 0;
        for (ItemParent i: list){
            if (i != null){
                ctr++;
            }
        }
        // Create a new list of the same size as the number of non-null items
        ItemParent[] newList = new ItemParent[ctr];
        // Copy the non-null items to the new list
        ctr = 0;
        for (ItemParent i: list){
            if (i != null){
                newList[ctr] = i;
                ctr++;
            }
        }
        return newList;
    }

}
