import java.util.ArrayList;

public class MyPrinter implements PrintInterface{
    public void printList(ArrayList<Item> list) {
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-13s %-10s %-10s %-7s %-10s%n","| Category", "| Description","| Priority", "| Price ", "| Quantity |" );
        System.out.println("------------------------------------------------------------");

        // Iterate through the list and print each item
        String descColumn;
        for (Item i : list) {
            if (i != null) {
                String blank = " ";
                // Format and print each column of the item
                String categoryColumn = blank + String.valueOf(i.getClass()).split(" ")[1] + blank.repeat(12 - String.valueOf(i.getClass()).split(" ")[1].length());
                if (i.getClass() == FoodItem.class){
                    String desc_cal = i.getDescription()+ "("+ ((FoodItem) i).getCalories() + ")";
                    descColumn = blank + desc_cal + blank.repeat(12 - desc_cal.length());

                }
                else {
                    descColumn = blank + i.getDescription() + blank.repeat(12 - i.getDescription().length());
                }
                String priorColumn = blank.repeat(5) + i.getPriority() + blank.repeat(7 - Double.toString(i.getPriority()).length());
                String costColumn = blank.repeat(1) + User.df.format(i.getCost()) + blank.repeat(6 - Double.toString(i.getCost()).length());
                String quantityColumn = blank.repeat(1) + i.getQuantity() + blank.repeat(9 - Integer.toString(i.getQuantity()).length());

                System.out.println("|" + categoryColumn + "|" + descColumn + "|" + priorColumn + "|" + costColumn + "|" + quantityColumn + "|");
            }
        }
    }

    public void printList(Item[] list) {
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-13s %-10s %-10s %-7s %-10s%n","| Category", "| Description","| Priority", "| Price", "| Quantity |" );
        System.out.println("------------------------------------------------------------");

        // Iterate through the list and print each item
        String descColumn;
        for (Item i : list) {
            if (i != null && i.getQuantity() > 0) {
                String blank = " ";
                // Format and print each column of the item
                String categoryColumn = blank + String.valueOf(i.getClass()).split(" ")[1] + blank.repeat(12 - String.valueOf(i.getClass()).split(" ")[1].length());
                if (i.getClass() == FoodItem.class){
                    String desc_cal = i.getDescription()+ "("+ ((FoodItem) i).getCalories() + ")";
                    descColumn = blank + desc_cal + blank.repeat(12 - desc_cal.length());

                }
                else {
                    descColumn = blank + i.getDescription() + blank.repeat(12 - i.getDescription().length());
                }
                String priorColumn = blank.repeat(5) + i.getPriority() + blank.repeat(7 - Double.toString(i.getPriority()).length());
                String costColumn = blank.repeat(1) + User.df.format(i.getCost()) + blank.repeat(6 - Double.toString(i.getCost()).length());
                String quantityColumn = blank.repeat(1) + i.getQuantity() + blank.repeat(9 - Integer.toString(i.getQuantity()).length());

                System.out.println("|" + categoryColumn + "|" + descColumn + "|" + priorColumn + "|" + costColumn + "|" + quantityColumn + "|" );
            }
        }
    }


    public void printNonPurchasedList(ArrayList<Item> list) {
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-13s %-10s %-10s %-7s %-10s%n","| Category", "| Description","| Priority", "| Price", "| Quantity |" );
        System.out.println("------------------------------------------------------------");

        // Iterate through the list and print each item
        String descColumn;
        for (Item i : list) {
            if (i != null && i.getQuantityNotPurchased() > 0) {
                String blank = " ";
                // Format and print each column of the item
                String categoryColumn = blank + String.valueOf(i.getClass()).split(" ")[1] + blank.repeat(12 - String.valueOf(i.getClass()).split(" ")[1].length());
                if (i.getClass() == FoodItem.class){
                    String desc_cal = i.getDescription()+ "("+ ((FoodItem) i).getCalories() + ")";
                    descColumn = blank + desc_cal + blank.repeat(12 - desc_cal.length());

                }
                else {
                    descColumn = blank + i.getDescription() + blank.repeat(12 - i.getDescription().length());
                }
                String priorColumn = blank.repeat(5) + i.getPriority() + blank.repeat(7 - Double.toString(i.getPriority()).length());
                String costColumn = blank.repeat(1) + User.df.format(i.getCost()) + blank.repeat(6 - Double.toString(i.getCost()).length());
                String quantityColumn = blank.repeat(1) + i.getQuantityNotPurchased() + blank.repeat(9 - Integer.toString(i.getQuantityNotPurchased()).length());

                System.out.println("|" + categoryColumn + "|" + descColumn + "|" + priorColumn + "|" + costColumn + "|" + quantityColumn + "|");
            }
        }
    }
}

