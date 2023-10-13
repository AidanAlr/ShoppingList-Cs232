import java.util.Scanner;

public class User {

    private Item[] shoppingList = new Item[askListSize()];
    int counter = 1;

    public Item[] getShoppingList() {
        return shoppingList;
    }

    public int askListSize() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many items do you want to add to your list?");
        return sc.nextInt();
    }


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



    public void printShoppingList() {
        System.out.println("Your shopping list is: ");
        for (Item i : shoppingList) {
            if (i != null) {
                System.out.println(i.description + " " + i.priority + " " + i.cost);
            }
        }
    }
}
