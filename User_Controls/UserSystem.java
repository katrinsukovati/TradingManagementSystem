// Author: Allan & Carrie
package User_Controls;

import Account.User;
import Account.Admin;
import Trade.Trade;

import java.util.ArrayList;

public class UserSystem {
    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Admin> admins = new ArrayList<>();

    /**
     * @param user the user to be added to userSystem
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * @param admin the admin to be added to userSystem
     */
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    /**
     * @param user the user to be removed from userSystem
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * @param admin the admin to be removed from adminSystem
     */
    public void removeAdmin(Admin admin) {
        admins.remove(admin);
    }

    /**
     * @param username the username to be checked for conflicts
     * @return returns true iff the system contains an user/admin with the same username
     */
    public boolean usernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param admin the Account.Admin.Account.Admin object to be checked
     * @return boolean representing whether or not system contains admin
     */
    public boolean containsAdmin(Admin admin) {
        return admins.contains(admin);
    }

    /**
     * @param username the user's username
     * @param password the user's password
     * @return the desired Account.User object
     */
    public User getUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @param username the user's username
     * @return the desired user
     */
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * username -> user's name
     * @param username: the username of the user
     * @return name of the user with username
     * @author Carrie
     */
    public String getNameFromUsername(String username){
        if (usernameTaken(username)){
            User user = getUser(username);
            return user.getName();
        }
        return null;
    }

    /**
     * @param username the desired Admin object
     * @param password the user's password
     * @return the target Admin object
     */
    public Admin getAdmin(String username, String password) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    /**
     * @return array list of all users in this system
     */
    public ArrayList<User> getUsersList() {
        return users;
    }

    /**
     * @return array list of all admins in this system
     */
    public ArrayList<Admin> getAdminsList() {
        return admins;
    }

    /**
     * duplicates a User
     * this method is used in admin.ActionManager for temporary memory
     * @param user: the original user
     * @author Carrie
     */
    public User duplicateUser(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        User duplicate = new User(username, password, name);
        UserManager userManager = new UserManager();
        duplicate.setNumIncompleteTrades(user.getNumIncompleteTrades());
        duplicate.setNumTradeThisWeek(user.getNumTradeThisWeek());
        duplicate.setLimitPerWeek(user.getLimitPerWeek());
        duplicate.setFrozenStatus(user.getFrozenStatus());
        duplicate.setDeactivateStatus(user.getDeactivateStatus());
        duplicate.setPastTrades(user.getPastTrades());
        duplicate.setWishList(user.getWishList());
        duplicate.setLentList(user.getLentList());
        duplicate.setGiveList(user.getGiveList());
        duplicate.setBorrowedList(user.getBorrowedList());
        duplicate.setDeactivatedList(user.getDeactivatedList());
        // no setCurrentTrades(); so just use other methods to achieve the duplication
        ArrayList<Trade> currentTrades = user.getCurrentTrades();
        for (Trade trade: currentTrades){
            userManager.addCurrentTrade(trade, duplicate);
        }
        return duplicate;
    }

    /**
     * duplicates a User
     * this method is used in admin.ActionManager for temporary memory
     * @param username: the original user's username
     * @author Carrie
     */
    public User duplicateUser(String username){
        User user = getUser(username);
        return duplicateUser(user);
    }

    /**
     * replace the information of user with duplicate
     * (username, password, and name unchanged)
     * @author Carrie
     */
    public void replaceMemory(User user, User duplicate){
        user.setNumIncompleteTrades(duplicate.getNumIncompleteTrades());
        user.setNumTradeThisWeek(duplicate.getNumTradeThisWeek());
        user.setLimitPerWeek(duplicate.getLimitPerWeek());
        user.setFrozenStatus(duplicate.getFrozenStatus());
        user.setDeactivateStatus(duplicate.getDeactivateStatus());
        user.setPastTrades(duplicate.getPastTrades());
        user.setWishList(duplicate.getWishList());
        user.setLentList(duplicate.getLentList());
        user.setGiveList(duplicate.getGiveList());
        user.setBorrowedList(duplicate.getBorrowedList());
        user.setDeactivatedList(duplicate.getDeactivatedList());
    }
}