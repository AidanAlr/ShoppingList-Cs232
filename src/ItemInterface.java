import java.util.ArrayList;

public interface ItemInterface {
    int getPriority();
    String getDescription();
    double getCost();
    boolean equals(Item j);
    boolean notPresent(ArrayList<Item> shoppingList);
}
