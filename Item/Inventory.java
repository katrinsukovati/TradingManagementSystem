package Item;
/*
  Author: David
  Description: This class stores all the items registered and have been accepted by an admin account.
 */

import java.util.ArrayList;

public class Inventory {
    private static ArrayList<Item> itemStorage = new ArrayList<>();

    /**
     * @param toAdd item to add to the inventory
     */
    public void addItem(Item toAdd){
        itemStorage.add(toAdd);
    }

    /**
     * @param toRemove item to remove from the inventory
     */
    public void removeItem(Item toRemove){
        itemStorage.remove(toRemove);
    }

    /**
     * @return the items in storage
     */
    public ArrayList<Item> getItemStorage() {
        return itemStorage;
    }
}