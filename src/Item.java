public class Item {
    String description;
    int priority;
    double cost;


    public Item(String des, int pri, double cos){
        description = des;
        priority = pri;
        cost = cos;
    }

    public boolean Equals(Item j){
        return (description.equalsIgnoreCase(j.description));
//                && (priority == j.priority)
//                && (cost == j.cost));
    }

    public boolean Present(Item[] shoppingList){
        for (Item i : shoppingList){
            if (i != null){
                if (this.Equals(i)){
                    return true;
                }
            }
        }
        System.out.println("Item not present in current list!");
        return false;
    }
}
