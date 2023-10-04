import java.util.Scanner;

public class User {

    Item[] shoppingList;

    public int askListSize(){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the length of your shopping list");
        String ui = s.nextLine();

        while (!ui.matches("[1-9][0-9]*")){
            System.out.println("Invalid list length. Please try again:");
            ui = s.nextLine();
        }

        int shopListSize = Integer.parseInt(ui);
        System.out.println("Thank you we have accepted your list length");
        return shopListSize;
    }


    public void createShopList(){
        shoppingList = new Item[askListSize()];
        System.out.println("Your shopping list has been created!");
    }


    public void addToShopList(Item item) {
        if (!item.Present(shoppingList)){
            for (int n=0; n<shoppingList.length; n++){
                if (shoppingList[n] == null){
                    shoppingList[n] = item;
                    break;
                    };
                }
            }
        }
}
