public class FoodItem extends Item {
    private final String calories;
    public FoodItem(String description, int priority, double cost, int quantity) {
        super(description, priority, cost, quantity);
        calories = "";
    }

    public FoodItem(String description, int priority, double cost, String calories, int quantity){
        super(description, priority, cost, quantity);
        this.calories = calories;
    }

    public String getCalories(){return calories;}
}
