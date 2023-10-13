import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user = new User();

        int itemsToAdd = user.getShoppingList().length;

        while (itemsToAdd > 0) {
            if (user.addToShoppingList()) {
                itemsToAdd--;
            }

        }
        user.printShoppingList();
    }
}
