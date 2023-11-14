public interface ItemInterface {
    int getPriority();
    String getDescription();
    double getCost();
    boolean equals(Item j);
    boolean notPresent(Item[] shoppingList);
}
