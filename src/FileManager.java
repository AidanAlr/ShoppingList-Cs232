import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileManager {
    public boolean fileExists(String pathname){
        File f = new File(pathname);
        try{
            Scanner sc = new Scanner(f);
            sc.hasNextLine();
            return true;
        }
        catch(Exception e){
            System.out.println("Could not find" + f.getPath());
            return false;
        }
    }

    public void createFile(String pathname) throws IOException {
        File f = new File(pathname);
        if(!fileExists(pathname)){
             f.createNewFile();
             System.out.println("Created " + f.getPath());
         }
         else{
             System.out.println(f.getPath() + " already exists");
         }
    }

    public void WriteListToFile(ArrayList<Item> a, String pathname){
        File f = new File(pathname);
        try{
            FileWriter filewriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
            bufferedWriter.write("Class,Description,Cost,Priority,Quantity");
            bufferedWriter.newLine();
            for(Item item : a){
                String temp = (String.valueOf(item.getClass()).split(" ")[1]+ "," + item.getDescription() + "," + item.getCost() + "," + item.getPriority() + "," + item.getQuantity());
                bufferedWriter.write(temp);
                bufferedWriter.newLine();
            }
            System.out.println("Updated "+f.getPath());
            bufferedWriter.close();
        }
        catch (Exception e) {
            System.out.println("Error occurred writing to file!");
        }
    }

    public void WriteNotPurchasedListToFile(ArrayList<Item> a, String pathname){
        File f = new File(pathname);
        try{
            FileWriter filewriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
            bufferedWriter.write("Class,Description,Cost,Priority,Quantity");
            bufferedWriter.newLine();
            for(Item item : a){
                String temp = (String.valueOf(item.getClass()).split(" ")[1]+ "," + item.getDescription() + "," + item.getCost() + "," + item.getPriority() + "," + item.getQuantityNotPurchased());
                bufferedWriter.write(temp);
                bufferedWriter.newLine();
            }
            System.out.println("Updated "+f.getPath());
            bufferedWriter.close();
        }
        catch (Exception e) {
            System.out.println("Error occurred writing to file!");
        }
    }


}
