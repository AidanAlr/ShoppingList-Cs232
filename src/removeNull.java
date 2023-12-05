public class removeNull {

    public Item[] execute (Item[] list){

        // Count the number of non-null items
        int ctr = 0;
        for (Item i: list){
            if (i != null){
                ctr++;
            }
        }
        // Create a new list of the same size as the number of non-null items
        Item[] newList = new Item[ctr];
        // Copy the non-null items to the new list
        ctr = 0;
        for (Item i: list){
            if (i != null){
                newList[ctr] = i;
                ctr++;
            }
        }
        return newList;
    }
}
