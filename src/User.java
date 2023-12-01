import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.List;

// The User class implements the UserInterface interface
public class User implements UserInterface {

    // DecimalFormat to format double values with two decimal places
    public static final DecimalFormat df = new DecimalFormat("0.00");

    // Attributes for shopping lists and a counter to track the number of items created
    private ArrayList<Item> shoppingList = new ArrayList<Item>();

    // Counter to keep track of the number of items created
    int counter = 1;

    // Method to get the user's shopping list
    public ArrayList<Item> getShoppingList(){
        return shoppingList;
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
    public Item createItem() {
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
            System.out.println("Please enter the cost of " + description);
            String input = scanner.nextLine();
            if (input.matches("(-?\\d*\\.?\\d+)")) {
                cost = Double.parseDouble(input);
                break;
            } else {
                System.out.println("Invalid price!");
            }
        }
        
        int quantity;
        while (true) {
            System.out.println("Please enter the quantity of  " + description);
            String input = scanner.nextLine();
            if (input.matches("(-?\\d*\\.?\\d+)")) {
                quantity = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Invalid quantity!");
            }
        }

        String calories = null;
        boolean calories_bool = false;
        if (categoryEntry.equalsIgnoreCase("food")){
            System.out.println("would you like to enter calories for " + description + "(Y/N)");
            if (scanner.nextLine().equalsIgnoreCase("Y")){
                calories_bool = true;
                System.out.println("Please enter the calories for your item:");
                calories = scanner.nextLine();
            }

        }

        // Create the appropriate item based on the category and add it to the shopping list
        switch (categoryEntry) {
            case "home":
                return new HomeItem(description, priority, cost, quantity);
            case "food":
                if (calories_bool) {
                    return new FoodItem(description, priority, cost, calories, quantity);
                }
                else {
                    return new FoodItem(description, priority, cost, quantity);
                }
            case "clothing":
                return new ClothingItem(description, priority, cost, quantity);
        }
        return null;
    }

    // Method to add an item to the shopping list
    public void addItemToSL(Item item) throws NonUniqueException {
        if (item.notPresent(shoppingList)) {
            shoppingList.add(item);
            System.out.println(item.getDescription() + " added to list!");
        } else {
            throw new NonUniqueException();
        }
    }


    // Method to sort the shopping list using bubble sort
    public void sortShoppingList(){
        Bubble b = new Bubble();
        shoppingList = b.bubbleSort(shoppingList);
    }

    private int calculateQuantityCanBuy(double budget, double itemPrice) {
        if (budget <= 0 || itemPrice <= 0) {
            return 0; // Cannot buy any if budget or item price is non-positive
        }

        return (int) (budget / itemPrice);
    }

    // Method to make purchases based on a given budget
    public void makePurchases(double budget){
        double b = budget;
        sortShoppingList();
        System.out.println("Your initial budget is: " + budget);

        // Iterate through the shopping list and make purchases
        for (Item i: shoppingList){
            int quantityPurchased = Math.min(calculateQuantityCanBuy(b, i.getCost()), i.getQuantity());
            i.setQuantityPurchased(quantityPurchased);

            int quantityNotPurchased = i.getQuantity()-quantityPurchased;
            i.setQuantityNotPurchased(quantityNotPurchased);

            b -= (i.getCost() * quantityPurchased);
            System.out.println("Purchased " + quantityPurchased + " of " + i.getDescription() + "(Priority " + i.getPriority() + ") for " + df.format(i.getCost() * quantityPurchased) + " -> " + df.format(b) + " remaining");
        }
        System.out.println("Leftover budget: " + df.format(b));
    }
}
