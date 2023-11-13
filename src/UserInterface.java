public interface UserInterface {
    boolean addItemToShoppingList();
    int askListSize();
    void addItemToSL(ItemParent item);
    void printList(ItemParent[] list);
    void sortShoppingList();
    void makePurchases(double budget);
}
