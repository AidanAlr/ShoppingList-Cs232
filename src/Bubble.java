public class Bubble{
    public Item[] bubbleSort(Item[] shoppingList){{
                int n = shoppingList.length;
                for (int i = 0; i < n - 1; i++)
                    for (int j = 0; j < n - i - 1; j++)
                        if (shoppingList[j].getPriority() > shoppingList[j + 1].getPriority()) {
                            // swap temp and arr[i]
                            Item temp = shoppingList[j];
                            shoppingList[j] = shoppingList[j + 1];
                            shoppingList[j + 1] = temp;
                        }
            }
            return shoppingList;
        }
}
