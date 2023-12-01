import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileManager {

    // Check if file exists
    public boolean fileExists(String pathname) {
        File f = new File(pathname);
        try {
            Scanner sc = new Scanner(f);
            sc.hasNextLine();
            return true;
        } catch (Exception e) {
            System.out.println("Could not find " + f.getPath());
            return false;
        }
    }

    // Create a file if it does not exist
    public void createFile(String pathname) throws IOException {
        File f = new File(pathname);
        if (!fileExists(pathname)) {
            f.createNewFile();
            System.out.println("Created " + f.getPath());
        } else {
            System.out.println(f.getPath() + " already exists");
        }
    }

    // Write the shopping list data to a file
    public void writeListToFile(ArrayList<Item> a, String pathname) {


        File f = new File(pathname);
        try {
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write header
            bufferedWriter.write("Class,Description,Cost,Priority,Quantity");
            bufferedWriter.newLine();

            // Write each item's data
            for (Item item : a) {
                String temp = getString(pathname, item);
                bufferedWriter.write(temp);
                bufferedWriter.newLine();
            }

            System.out.println("Updated " + f.getPath());
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error occurred writing to file!");
        }
    }

    private static String getString(String pathname, Item item) {
        int quantity = 0;
        if (pathname.equals("PurchasedList.csv")){
            quantity = item.getQuantityPurchased();
        } else if (pathname.equals("NotPurchasedList.csv")) {
            quantity = item.getQuantityNotPurchased();
        }

        String temp = (String.valueOf(item.getClass()).split(" ")[1]
                + "," + item.getDescription()
                + "," + item.getCost()
                + "," + item.getPriority()
                + "," + quantity);
        return temp;
    }
}
