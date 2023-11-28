import java.util.ArrayList;

public interface UserInterface {
    Item createItem();
    int askListSize();
    void addItemToSL(Item item) throws NonUniqueException;
    void sortShoppingList();
    void makePurchases(double budget);

}
