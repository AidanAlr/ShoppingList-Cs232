
public class Main {
    public static void main(String[] args) {
        //Creating the user
        User user = new User();
        //Creating the shopping list attribute
        user.setShoppingList();
        //Getting the number of items to add to the list
        int itemsToAdd = user.getShoppingList().length;
        //Adding items to the list
        while (itemsToAdd > 0) {
            if (user.addToShoppingList()) {
                itemsToAdd--;
            }

        }
        //Printing the list
        user.printShoppingList();
    }
}
