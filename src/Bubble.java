import java.util.ArrayList;

public class Bubble{
    public ArrayList<Item> bubbleSort(ArrayList<Item> shoppingList){{
                int n = shoppingList.size();
                for (int i = 0; i < n - 1; i++)
                    for (int j = 0; j < n - i - 1; j++)
                        if (shoppingList.get(j).getPriority() > shoppingList.get(j + 1).getPriority()) {
                            // swap temp and arr[i]
                            Item temp = shoppingList.get(j);
                            shoppingList.set(j, shoppingList.get(j + 1));
                            shoppingList.set(j + 1, temp);
                        }
            }
            return shoppingList;
        }
}
