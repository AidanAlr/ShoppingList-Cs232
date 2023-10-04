public class Item {
    String description;
    int priority;
    int price = 0;

    public Item(String des, int prior, int cost){
        description = des;
        priority = prior;
        price = cost;
    }

    public boolean Equals(Item j){
        return ((description.equalsIgnoreCase(j.description))
                && (priority == j.priority)
                && (price == j.price));
    }

    public boolean Present(Item[] shoppingList){
        for (Item i : shoppingList){
            if (i != null){
                if (this.Equals(i)){
                    return true;
                };
            }
        }
        System.out.println("Item not present in current list!");
        return false;
    }
}
