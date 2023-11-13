public interface UserInterface {
    boolean addItemToShoppingList();
    int askListSize();
    void addItemToSL(Item item);
    void printList(Item[] list);
    void sortShoppingList();
    void makePurchases(double budget);
}
