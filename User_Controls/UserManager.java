package User_Controls;
/*
 * Author: Shruti
 * Description: This class defines all the methods that's required by users
 */

import Account.DemoUser;
import Account.User;
import Item.Item;
import Trade.Trade;
import Admin_Controls.UnfreezeRequestInventory;
import Item.Inventory;
import Item.ItemRequest;

import java.util.ArrayList;

public class UserManager {

    /**
     * @param newUserName is the new user name that is to be added to all the users in the system.
     * @param newName is the name of the new user to be added to users.
     * @param newPassword is the password of the new user.
     * @param users is an instance of User.UserSystem.
     * @return true if account is created.
     */
    public boolean createUser(String newUserName, String newName, String newPassword, UserSystem users){
        if(!users.usernameTaken(newUserName)) {
            User newUser = new User(newUserName, newPassword, newName);
            users.addUser(newUser);
            return true;
        }
        return false;
    }

    /**
     * @param newUserName is the new user name that is to be added to all the users in the system.
     * @param newName is the name of the new user to be added to users.
     * @param newPassword is the password of the new user.
     * @param users is an instance of User.UserSystem.
     * @return true if account is created.
     */
    public boolean createDemoUser(String newUserName, String newName, String newPassword, UserSystem users){
        if(!users.usernameTaken(newUserName)) {
            DemoUser newDemoUser = new DemoUser(newUserName, newPassword, newName);
            users.addUser(newDemoUser);
            return true;
        }
        return false;
    }

    /**
     * @param user is the user whose account details we would want to see.
     * @return the account details of the user.
     */
    public String accessAccount(User user){
        return "Name : " + user.getName() + "\n" + "Account.User Name : " + user.getUsername()
                + "\n" + "Password : " + user.getPassword();
    }

    /**
     * Function to update the users name.
     * @param user is the user whose name is to be updated.
     * @param newName is the new name which will get updated.
     */
    public void updateName(User user, String newName){
        user.setName(newName);
    }

    /**
     * Function to update the password to the account
     * @param user is the user whose password needs to updated.
     * @param newPassword is the new password for the user.
     */
    public void updatePassword(User user, String newPassword){
        user.setPassword(newPassword);
    }

    /**
     * @param user is the user whose username needs to updated.
     * @param newUsername is the new username for the user.
     */
    public void updateUsername(User user, String newUsername){
        user.setUsername(newUsername);
    }

    /**
     * @param requestInventory is the Unfreeze Request Item.Item.Item.Inventory.
     */
    UnfreezeRequestInventory requestInventory;

    /**
     * Function to submit unfreeze request to the admin.
     * @param user is the user who wants to submit a unfreeze request.
     * @param description is the reason for requesting the unfreeze request.
     * @return true if the request is submitted.
     */
    public boolean unfreezeRequest(User user, String description){
        requestInventory.addUnfreezeRequest(user, description);
        return true;
    }

    /**
     * @param user is the user to whose list its getting added to.
     * @param item is the item to be added.
     * @return true is item is added.
     */
    public boolean addToWishList(User user, Item item){
        if(item.getItemOwner() == user || user.getWishList().contains(item)){
            return false;
        }else{
            user.getWishList().add(item);
            return true;
        }
    }

    /**
     * @param user is the user to whose list its getting added to.
     * @param item is the item to be added.
     * @return true is item is added.
     */
    public boolean addToGiveList(User user, Item item){
        user.getGiveList().add(item);
        return true;
    }

    /**
     * @param user is the user to whose list its getting added to.
     * @param item is the item to be added.
     * @return true is item is added.
     */
    public boolean addToLentList(User user, Item item){
        user.getLentList().add(item);
        return true;
    }

    /**
     * @param user is the user to whose list its getting added to.
     * @param item is the item to be added.
     * @return true is item is added.
     */
    public boolean addToBorrowedList(User user, Item item){
        user.getBorrowedList().add(item);
        return true;
    }

    /**
     * @param user is the user to whose list its getting removed from.
     * @param item is the item to be removed.
     * @return true is item is removed.
     */
    public boolean removeFromWishList(User user, Item item){
        user.getWishList().remove(item);
        return true;
    }

    /**
     * @param user is the user to whose list its getting removed from.
     * @param item is the item to be removed.
     * @return true is item is removed.
     */
    public boolean removeFromGiveList(User user, Item item){
        user.getGiveList().remove(item);
        return true;
    }

    /**
     * @param user is the user to whose list its getting removed from.
     * @param item is the item to be removed.
     * @return true is item is removed.
     */
    public boolean removeFromLentList(User user, Item item){
        user.getLentList().remove(item);
        return true;
    }

    /**
     * @param user is the user to whose list its getting removed from.
     * @param item is the item to be removed.
     * @return true is item is removed.
     */
    public boolean removeFromBorrowedList(User user, Item item){
        user.getBorrowedList().remove(item);
        return true;
    }

    /**
     * Add a ongoing trade to this user's currentTrade list.
     * @param toAdd the trade to be added to currentTrades
     */
    public void addCurrentTrade(Trade toAdd, User user) { user.getCurrentTrades().add(toAdd);}

    /**
     * Remove a completed trade from this user's currentTrades
     * @param toRemove the trade to be removed from currentTrades
     */
    public void removeCurrentTrade(Trade toRemove, User user) {user.getCurrentTrades().remove(toRemove);}

    /**
     * Deactivates the user's account
     * @param user the specified user
     */
    public void deactivateUser(User user) {
        user.setFrozenStatus(true);
        ItemRequest itemRequest = new ItemRequest();
        ArrayList<Item> requestedItems = itemRequest.getRequestedItems();
        Inventory inventory = new Inventory();
        ArrayList<Item> inventoryList = inventory.getItemStorage();
        ArrayList<Item> deactivateList = new ArrayList<Item>();

        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getItemOwner().getName() == user.getName()){
                deactivateList.add(inventoryList.get(i));
            }
        }

        for (int i = 0; i < requestedItems.size(); i++) {
            if (requestedItems.get(i).getItemOwner().getName() == user.getName()){
                deactivateList.add(requestedItems.get(i));
            }
        }

        for (int i = 0; i < deactivateList.size(); i++){
            if (deactivateList.get(i).getItemStatus()){
                inventory.removeItem((deactivateList.get(i)));
            } else {
                requestedItems.remove(deactivateList.get(i));
            }
        }
        user.setDeactivatedList(deactivateList);
        user.setDeactivateStatus(true);
    }

    /**
     * Reactivate the user's account
     * @param user the specified user
     */
    public void reactivateUser(User user){
        user.setFrozenStatus(false);
        ArrayList<Item> deactivatedItems = user.getDeactivatedList();

        ItemRequest itemRequest = new ItemRequest();
        Inventory inventory = new Inventory();

        for (int i = 0; i < deactivatedItems.size(); i++){
            if (deactivatedItems.get(i).getItemStatus()){
                inventory.addItem(deactivatedItems.get(i));
            } else {
                itemRequest.addItems(deactivatedItems.get(i));
            }
        }
        deactivatedItems.clear();
        user.setDeactivateStatus(false);
    }
}