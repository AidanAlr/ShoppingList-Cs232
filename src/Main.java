import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        User u = new User();
        u.createShopList();
        Item apple = new Item("apple", 1, 2 );
        u.addToShopList(apple);
        System.out.println(Arrays.toString(u.shoppingList));
        }
    }