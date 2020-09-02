package Admin_Controls;/*
 * Author: Jeevika
 * Description: This class defines all functions that an Admin should be able to do.
 */

import Account.Admin;
import Account.User;
import Item.*;
import User_Controls.*;

import java.util.ArrayList;

public class AdminManager {

    private static int maxIncompleteTrade;
    private static int tradePrerequisite;


    /**
     * The setter method for setting the maximum number of incomplete trade
     * @param IncompleteTrade The maximum number of incomplete trades set by admin.
     */
    public void setMaxIncompleteTrade(int IncompleteTrade) {
        maxIncompleteTrade = IncompleteTrade;
    }

    /**
     * getter for maxIncompleteTrade
     * @return maxIncompleteTrade
     */
    public int getMaxIncompleteTrade(){
        return maxIncompleteTrade;
    }

    /**
     * getter for tradePrerequisite
     * @return tradePrerequisite
     */
    public int getTradePrerequisite(){
        return tradePrerequisite;
    }

    /**
     * The setter method for setting the trade prerequisite
     * @param TradePrerequisite The trade prerequisite for a user.
     */
    public void setTradePrerequisite(int TradePrerequisite) {
        tradePrerequisite = TradePrerequisite;
    }

    /**
     *
     * @param admin The user whose account info will be printed
     * @return A String containing all important account info of an admin
     */
    public String accessAccount(Admin admin) {
        return "Name : " + admin.getName() + "\n" + "User Name : " + admin.getUsername()
                + "\n" + "Password : " + admin.getPassword();
    }

    /**
     * Function to update the users name.
     * @param admin   is the user whose name is to be updated.
     * @param newName is the new name which will get updated.
     */
    public void updateName(Admin admin, String newName) {
        admin.setName(newName);
    }

    /**
     * Function to update the password to the account
     * @param admin       is the user whose password needs to updated.
     * @param newPassword is the new password for the user.
     */
    public void updatePassword(Admin admin, String newPassword) {
        admin.setPassword(newPassword);
    }

    /**
     * Method to update the admin's username
     * @param admin       is the user whose username needs to updated.
     * @param newUsername is the new username for the user.
     */
    public void updateUsername(Admin admin, String newUsername) {
        admin.setUsername(newUsername);
    }


    /**
     * Method to change a user status to frozen/unfrozen.
     * @param user The user whose status needs to be frozen/unfrozen.
     */
    public void accountFrozenStatus(User user, boolean status) {
        user.setFrozenStatus(status);
    }



        /**
         * Method to change a user to an admin
         * @param user       user that needs to be changed to an Admin
         * @param UserSystem Information about all users in the system.
         */
        public void designateAdmin (User user, UserSystem UserSystem){
            String username = user.getUsername();
            String password = user.getPassword();
            String name = user.getName();
            UserSystem.removeUser(user);
            Admin newAdmin = new Admin(username, password, name);
            UserSystem.addAdmin(newAdmin);
        }

        /**
         * Changes the limit per week for all users.
         * @param newLimit   The new limit per week that will be set by an admin
         * @param userSystem Information about all users in the system.
         * @return the new limit per week.
         */
        public int changeLimitPerWeek ( int newLimit, UserSystem userSystem){
            ArrayList<User> allUsers = userSystem.getUsersList();

            for (User allUser : allUsers) {
                allUser.setLimitPerWeek(newLimit);
            }
            return newLimit;
        }

        /**
         * Confirms when an item is added to inventory and deletes item from requested items list.
         * @param item         The item that needs to be added to inventory
         * @param itemRequests Information about all requested items.
         * @param inventory    Item.Inventory of items that have been approved by admin
         * @return true if the item is confirmed.
         */
        public void confirmItem (Item item, ItemRequest itemRequests, Inventory inventory){
            ArrayList<Item> requestedItems = itemRequests.getRequestedItems();

            itemRequests.removeItem(item);
            inventory.addItem(item);
            item.setItemStatus(true);
            User itemOwner = item.getItemOwner();
            itemOwner.getGiveList().add(item);
        }

    /**
     *
     * @return a list of users who have too many incomplete trades - more than the max determined by admin
     */
    public ArrayList<User> incompleteTrades (){
            UserSystem userSystem = new UserSystem();
            ArrayList<User> users = userSystem.getUsersList();
            ArrayList<User> usersIncompleteTrades = new ArrayList<>();
            for (User user : users) {
                if (user.getNumIncompleteTrades() > getMaxIncompleteTrade()) {
                    usersIncompleteTrades.add(user);
                }
            }
            return usersIncompleteTrades;
        }
}