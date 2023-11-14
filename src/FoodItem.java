public class FoodItem extends Item {

    private final String calories;
    public FoodItem(String description, int priority, double cost) {
        super(description, priority, cost);
        calories = "";
    }

    public FoodItem(String description, int priority, double cost, String calories){
        super(description, priority, cost);
        this.calories = calories;
    }

    public String getCalories(){return calories;}
}
