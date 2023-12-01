import java.util.ArrayList;

public abstract class Item implements ItemInterface{
    private final String description;
    private final int priority;
    private final double cost;
    private int quantity;
    private int quantityNotPurchased;
    private int quantityPurchased;


    public Item(String des, int pri, double cos, int qua) {
        this.description = des;
        this.priority = pri;
        this.cost = cos;
        this.quantity = qua;

    }

    public Item(Item original, int quantity) {
        this.description = original.getDescription();
        this.priority = original.getPriority();
        this.cost = original.getCost();
        this.quantity = quantity;
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
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}

    public void setQuantityPurchased(int quantityPurchased){this.quantityPurchased = quantityPurchased;}
    public int getQuantityPurchased(){return quantityPurchased;}

    public void setQuantityNotPurchased(int quantityNotPurchased){this.quantityNotPurchased = quantityNotPurchased;}
    public int getQuantityNotPurchased(){return quantityNotPurchased;}
    public boolean equals(Item j){
        return (description.equalsIgnoreCase(j.description) || priority == j.priority);//                && (cost == j.cost));
    }

    public boolean notPresent(ArrayList<Item> shoppingList){
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
