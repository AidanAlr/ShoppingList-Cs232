public abstract class Item implements ItemInterface{
    private final String description;
    private final int priority;
    private final double cost;

    public Item(String des, int pri, double cos) {
        this.description = des;
        this.priority = pri;
        this.cost = cos;

    }
    public int getPriority(){
        return priority;
    }
    public String getDescription(){
        return description;
    }
    public double getCost(){
        return cost;
    }
    public boolean equals(Item j){
        return (description.equalsIgnoreCase(j.description) || priority == j.priority);//                && (cost == j.cost));
    }

    public boolean notPresent(Item[] shoppingList){
        for (Item i : shoppingList){
            if (i != null){
                if (this.equals(i)){
                    return false;
                }
            }
        }
        return true;
    }


}
