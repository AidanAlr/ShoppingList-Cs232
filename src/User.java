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

        if (!item.Present(shoppingList)) {
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
        System.out.println("Your shopping list is: ");
        for (Item i : shoppingList) {
            if (i != null) {
                System.out.println(i.description + " " + i.priority + " " + i.cost);
            }
        }
    }
}
