public class Item {
    String description;
    int priority;
    double cost;


    public Item(String des, int pri, double cos){
        description = des;
        priority = pri;
        cost = cos;
    }

    public boolean equals(Item j){
        return (description.equalsIgnoreCase(j.description));
//                && (priority == j.priority)
//                && (cost == j.cost));
    }

    public boolean present(Item[] shoppingList){
        for (Item i : shoppingList){
            if (i != null){
                if (this.equals(i)){
                    return true;
                }
            }
        }
        return false;
    }
}
