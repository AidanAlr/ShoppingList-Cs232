import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileManager {
   String fileName = "purchased_list.csv";
    File myFile = new File(fileName);
    public boolean fileExists(){
        try{
            Scanner sc = new Scanner(myFile);
            sc.hasNextLine();
            return true;
        }
        catch(Exception e){
            System.out.println("Could not find file!");
            return false;
        }
    }

    public void createFile() throws IOException {
         if(!fileExists()){
             myFile.createNewFile();
             System.out.println("Created new file!");
         }
         else{
             System.out.println("File Already Exists");
         }

    }

    public void WriteToFile(ArrayList<Item> a ){
        try{
            FileWriter filewriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
            bufferedWriter.write("Class,Description,Cost,Priority,Quantity");
            bufferedWriter.newLine();
            for(Item item : a){
                String temp = (String.valueOf(item.getClass()).split(" ")[1]+ "," + item.getDescription() + "," + item.getCost() + "," + item.getPriority() + "," + item.getQuantity());
                bufferedWriter.write(temp);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        catch (Exception e) {
            System.out.println("Error occurred writing to file!");

        }
    }

}
