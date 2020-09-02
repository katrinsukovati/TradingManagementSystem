package Item;
/*
 * Author: Shruti
 * Description: This is the abstract parent class defines all items.
 */

import java.io.Serializable;
import java.util.*;

import Account.User;

public class Item implements Serializable {
    /**
     * itemOwner is name of the owner of the item
     * itemName is the name od the object
     * itemDescription is the description of the item
     * itemStatus is the status of the item (if approved by admin or not)
     * pastOwners is the list of past owners of the object
     */

    private User itemOwner;
    private String itemName;
    private String itemDescription;
    private String itemType;
    private boolean itemStatus;
    private final ArrayList<User> pastOwners;

    //Constructor
    public Item(User itemOwner, String itemName, String itemDescription, String itemType, boolean itemStatus) {
        this.itemOwner = itemOwner;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
        this.itemStatus = itemStatus;
        this.pastOwners = new ArrayList<>();
    }

    /**
     * @return the name of owner of the item
     */
    public User getItemOwner() {
        return itemOwner;
    }

    /**
     * @return the name of the item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @return the description of the item
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @return the item type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @return the status of the item (if it's approved by the admin or not)
     */
    public boolean getItemStatus() {
        return itemStatus;
    }

    /**
     * @return is the list of past owners of the object
     */
    public ArrayList<User> getPastOwners() {
        return pastOwners;
    }

    /**
     * @param itemOwner is the new item's owner
     */
    public void setItemOwner(User itemOwner) {
        this.itemOwner = itemOwner;
    }

    /**
     * @param itemStatus is the new items admin approval status
     */
    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * @param owner is the new owner to be added to the list of owners of the item
     */
    public void addPastOwners(User owner) {
        pastOwners.add(owner);
    }

    public String toString(){
        return "Item owner: " + itemOwner.getName() + "\nItem type: " + itemType + "\nItem name: "
                + itemName + "\nItem description: " + itemDescription  + '\n';
    }
}