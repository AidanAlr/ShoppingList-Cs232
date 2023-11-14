public interface UserInterface {
    Item createItem();
    int askListSize();
    void addItemToSL(Item item) throws NonUniqueException;
    void printList(Item[] list);
    void sortShoppingList();
    void makePurchases(double budget);

}
