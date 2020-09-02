package Trade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import Account.User;
/*
 * Author: Allan
 * Description: This is an abstract class storing relative information regarding a trade
 */

public abstract class Trade implements Serializable {

    private String location;
    private String time;
    private final User[] usersInvolved;
    private final int[] editsDone = new int[]{0, 0};
    private final String typeOfTrade;
    private final boolean[] currentStatusList;
    private final boolean[] finalStatus = new boolean[] {false, false};
    private final HashMap<User, User> lenderRelations = new HashMap<>();
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * @param location: string of meetup location for trade to take place
     * @param time: string of time for meetup
     * @param usersInvolved: array storing the two users involved
     * @param typeOfTrade: permanent or temporary
     * @param lenderRelation: embedded array where index 0 is the lender and index 1 is the borrower
     */
    public Trade(String location, String time, User[] usersInvolved, String typeOfTrade, User[][] lenderRelation) {
        this.location = location;
        this.time = time;
        this.usersInvolved = new User[] {usersInvolved[0], usersInvolved[1]};
        this.typeOfTrade = typeOfTrade;
        for (User[] users : lenderRelation) {
            this.lenderRelations.put(users[0], users[1]);
        }
        this.currentStatusList = new boolean[usersInvolved.length];
    }

    /**
     * @return location of trade
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return time of trade
     */
    public String getTime() {
        return time;
    }

    /**
     * @return array of the two users involved
     */
    public User[] getUsersInvolved() {
        return usersInvolved;
    }

    /**
     * @return array of edits done by both users
     */
    public int[] getEditsDone() {
        return editsDone;
    }

    /**
     * @return type of trade
     */
    public String getTypeOfTrade() {
        return typeOfTrade;
    }

    /**
     * @return array of two arrays storing item offered by both users
     */
    public abstract Object[] getItemsOffered();

    /**
     * @return current boolean status of trade
     */
    public boolean[] getCurrentStatus() {
        return currentStatusList;
    }

    /**
     * @return array of boolean final status of trade
     */
    public boolean[] getFinalStatus(){
        return finalStatus;
    }

    /**
     * @return hashmap of lenderrelations where key is the lender and value is the borrower
     */
    public HashMap<User, User> getLenderRelations() {
        return lenderRelations;
    }

    /**
     * @return Arraylist of current Trade.Trade.Transaction Status
     */
    public boolean[] getCurrentStatusList() {
        return currentStatusList;
    }

    /**
     * @return transactions of this trade
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @param location new location of trade
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @param time new time of trade
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @param user the index of the user whom edited in the same order as usersInvolved
     */
    public void setEditsDone(int user) {
        editsDone[user] ++;
    }


    /**
     * @param status new boolean status
     */
    public void setCurrentStatus(int user, boolean status) {
        currentStatusList[user] = status;
    }

    /**
     * @param user the index of the user whom edited in the same order as usersInvolved
     * @param status the boolean final status of the user
     */
    public void setFinalStatus(int user, boolean status) {
        finalStatus[user] = status;
    }
}