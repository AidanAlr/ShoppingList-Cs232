import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.List;

// The User class implements the UserInterface interface
public class User implements UserInterface {

    // DecimalFormat to format double values with two decimal places
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Attributes for shopping lists and a counter to track the number of items created
    private Item[] shoppingList;
    private Item[] purchasedList;
    private Item[] notPurchasedList = new Item[10];

    // Counter to keep track of the number of items created
    int counter = 1;

    // Method to get the user's shopping list
    public Item[] getShoppingList() {
        return shoppingList;
    }

    // Method to set the user's empty shopping list
    public void setShoppingList() {
        this.shoppingList = new Item[askListSize()];
    }

    // Method to get the user's purchased list
    public Item[] getPurchasedList(){
        return purchasedList;
    }

    // Method to get the user's not purchased list
    public Item[] getNotPurchasedList(){
        return notPurchasedList;
    }

    // Method to ask the user how many items they want to add to their list
    public int askListSize() {
        while (true){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println("How many items do you want to add to your list?");
                return scanner.nextInt();
            }
            catch (Exception e){
                System.out.println("Invalid integer.");
            }
        }
    }

    // Method to add an item to the shopping list
    public boolean addItemToShoppingList() {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get item category from the user
        boolean validCategory = false;
        String categoryEntry;
        do {
            System.out.println("Please enter item category(food, clothing, or home): ");
            categoryEntry = scanner.nextLine().toLowerCase();

            String[] categoryOptions = {"food", "home", "clothing"};
            List<String> categoriesList = Arrays.asList(categoryOptions);

            if (categoriesList.contains(categoryEntry)) {
                validCategory = true;
            }
            else{
                System.out.println("Invalid Category");
            }
        }
        while (!validCategory);


        // Get item description from the user
        System.out.println("Please enter a description of your " + categoryEntry + " item");
        String description = scanner.nextLine();

        // Get item priority from the user with input validation
        int priority;
        while (true) {
            System.out.println("Please enter the priority of " + (description));
            String input = scanner.nextLine();
            if (input.matches("^[0-9].*")) {
                priority = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Invalid priority!");
            }
        }

        // Get item cost from the user with input validation
        double cost;
        while (true) {
            System.out.println("Please enter the cost of  " + description);
            String input = scanner.nextLine();
            if (input.matches("(-?\\d*\\.?\\d+)")) {
                cost = Double.parseDouble(input);
                break;
            } else {
                System.out.println("Invalid price!");
            }
        }

        // Create the appropriate item based on the category and add it to the shopping list
        switch (categoryEntry) {
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

    // Method to add an item to the shopping list
    public void addItemToSL(Item item){
        if (item.notPresent(shoppingList)) {
            for (int j = 0; j < shoppingList.length; j++) {
                if (shoppingList[j] == null) {
                    shoppingList[j] = item;
                    System.out.println(item.getDescription() + " added to list!");
                    counter++;
                    break;
                }
            }
        }
        else{
            System.out.println("You have entered a non-unique item. Please try again.");
        }
    }

    // Method to print the shopping list
    public void printList(Item[] list) {
        System.out.println("------------------------------------------------");
        System.out.printf("%-13s %-10s %-10s %-10s%n","| Category", "| Description","| Priority", "| Price |");
        System.out.println("------------------------------------------------");

        // Iterate through the list and print each item
        for (Item i : list) {
            if (i != null) {
                String blank = " ";
                // Format and print each column of the item
                String categoryColumn = blank + String.valueOf(i.getClass()).split(" ")[1] + blank.repeat(12 - String.valueOf(i.getClass()).split(" ")[1].length());
                String descColumn = blank + i.getDescription() + blank.repeat(12 - i.getDescription().length());
                String priorColumn = blank.repeat(5) + i.getPriority() + blank.repeat(7 - Double.toString(i.getPriority()).length());
                String costColumn = blank.repeat(1) + df.format(i.getCost()) + blank.repeat(5 - Double.toString(i.getCost()).length());

                System.out.println("|" + categoryColumn + "|" + descColumn + "|" + priorColumn + "|" + costColumn + "|");
            }
        }
    }

    // Method to sort the shopping list using bubble sort
    public void sortShoppingList(){
        Bubble b = new Bubble();
        shoppingList = b.bubblesort(shoppingList);
    }

    // Method to make purchases based on a given budget
    public void makePurchases(double budget){
        sortShoppingList();
        Item[] purchases = new Item[10];

        int ctr = 0;

        System.out.println("Your initial budget is: " + budget);

        // Iterate through the shopping list and make purchases
        for (Item i: shoppingList){
            if (i.getCost() <= budget){
                purchases[ctr] = i;
                budget -= i.getCost();
                System.out.println("Purchased " + i.getDescription() + "(Priority " + i.getPriority() + ") for " + df.format(i.getCost()) + " -> " + df.format(budget) + " remaining");
                ctr++;
            }
            else {
                System.out.println("Can't afford " + i.getDescription() + " for " + df.format(i.getCost()) + " -> "+ df.format(budget) + " remaining");
            }
        }

        System.out.println("Leftover budget: " + df.format(budget));

        // Call the removeNull class to remove unwanted nulls
        removeNull removeNull = new removeNull();
        purchasedList = removeNull.execute(purchases);

        // Iterate through the shopping list to populate the not purchased list
        for(int i=0; i<shoppingList.length; i++ ){
            if (shoppingList[i].notPresent(purchasedList)){
                notPurchasedList[i] = shoppingList[i];
            }
        }
        notPurchasedList = removeNull.execute(notPurchasedList);
    }
}
