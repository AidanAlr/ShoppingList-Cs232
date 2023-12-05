import java.util.ArrayList;

// MyPrinter class implements PrintInterface
public class MyPrinter implements PrintInterface {

    // Method to print a list of items based on the specified quantity type
    public void printList(ArrayList<Item> list, String neededQuantity) {
        // Header for the printed table
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-13s %-10s %-10s %-7s %-10s%n", "| Category", "| Description", "| Priority", "| Price ", "| Quantity |");
        System.out.println("------------------------------------------------------------");

        // Iterate through the list and print each item
        for (Item i : list) {
            int q = 0;
            switch (neededQuantity) {
                case "quantity":
                    q = i.getQuantity();
                    break;
                case "quantityPurchased":
                    q = i.getQuantityPurchased();
                    break;
                case "quantityNotPurchased":
                    q = i.getQuantityNotPurchased();
                    break;
                default:
                    break;
            }

            if (i != null && q != 0) {
                // Initialize variables for formatting columns
                String blank = " ";
                String descColumn;

                // Format and print each column of the item
                String categoryColumn = blank + String.valueOf(i.getClass()).split(" ")[1] + blank.repeat(12 - String.valueOf(i.getClass()).split(" ")[1].length());

                if (i.getClass() == FoodItem.class) {
                    // For FoodItem, include calories in the description
                    String desc_cal = i.getDescription() + "(" + ((FoodItem) i).getCalories() + ")";
                    descColumn = blank + desc_cal + blank.repeat(12 - desc_cal.length());
                } else {
                    // For other items, use the regular description
                    descColumn = blank + i.getDescription() + blank.repeat(12 - i.getDescription().length());
                }

                String priorColumn = blank.repeat(5) + i.getPriority() + blank.repeat(7 - Double.toString(i.getPriority()).length());
                String costColumn = blank.repeat(1) + User.df.format(i.getCost()) + blank.repeat(6 - Double.toString(i.getCost()).length());
                String quantityColumn = blank.repeat(1) + q + blank.repeat(9 - Integer.toString(q).length());

                // Print the formatted item row
                System.out.println("|" + categoryColumn + "|" + descColumn + "|" + priorColumn + "|" + costColumn + "|" + quantityColumn + "|");
            }
        }
    }
}
