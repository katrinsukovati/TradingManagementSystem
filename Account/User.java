package Account;
/*
 * Author: David
 * Description: This class defines what a user account is.
 */

import Account.Account;
import Item.Item;
import Trade.Trade;
import Serializer.*;
import java.io.Serializable;
import java.util.ArrayList;
public class User extends Account implements Serializable {

    //variables
    private boolean frozenStatus;
    private boolean deactivateStatus;
    private int numIncompleteTrades;
    private static int limitPerWeek;
    private int numTradeThisWeek;
    private ArrayList<Trade> pastTrades = new ArrayList<>();
    private ArrayList<Item> wishList = new ArrayList<>();
    private ArrayList<Item> giveList = new ArrayList<>();
    private ArrayList<Item> lentList = new ArrayList<>();
    private ArrayList<Item> borrowedList = new ArrayList<>();
    private final ArrayList<Trade> currentTrades = new ArrayList<>();
    private ArrayList<Item> deactivatedList = new ArrayList<>();

    //constructor
    public User(String username, String password, String name){
        super(username, password, name);
        this.numIncompleteTrades = 0;
        this.numTradeThisWeek = 0;
        this.frozenStatus = false;
        this.deactivateStatus = false;
    }
    //Getter methods for all the variables.
    public int getNumIncompleteTrades(){
        return numIncompleteTrades;
    }
    public int getLimitPerWeek(){
        return limitPerWeek;
    }
    public int getNumTradeThisWeek(){
        return numTradeThisWeek;
    }
    public boolean getFrozenStatus(){
        return frozenStatus;
    }
    public boolean getDeactivateStatus(){return deactivateStatus;}
    public ArrayList<Trade> getPastTrades(){
        return pastTrades;
    }
    public ArrayList<Item> getWishList(){
        return wishList;
    }
    public ArrayList<Item> getGiveList(){
        return giveList;
    }
    public ArrayList<Item> getLentList(){
        return lentList;
    }
    public ArrayList<Item> getBorrowedList(){
        return borrowedList;
    }
    public ArrayList<Trade> getCurrentTrades() {return currentTrades;}
    public ArrayList<Item> getDeactivatedList() {return deactivatedList;}

    //Setters for the non-List variables.
    public void setFrozenStatus(boolean frozenStatus) {
        this.frozenStatus = frozenStatus;
    }
    public void setDeactivateStatus(boolean deactivateStatus) {this.deactivateStatus = deactivateStatus;}
    public void setNumIncompleteTrades(int numIncompleteTrades) {
        this.numIncompleteTrades = numIncompleteTrades;
    }
    public void setLimitPerWeek(int limitPerWeek) {
        User.limitPerWeek = limitPerWeek;
    }
    public void setNumTradeThisWeek(int numTradeThisWeek) {
        this.numTradeThisWeek = numTradeThisWeek;
    }
    public void setPastTrades(ArrayList<Trade> pastTrades) {
        this.pastTrades = pastTrades;
    }
    public void setWishList(ArrayList<Item> wishList) {
        this.wishList = wishList;
    }
    public void setGiveList(ArrayList<Item> giveList) {
        this.giveList = giveList;
    }
    public void setLentList(ArrayList<Item> lentList) {
        this.lentList = lentList;
    }
    public void setBorrowedList(ArrayList<Item> borrowedList) {
        this.borrowedList = borrowedList;
    }
    public void setDeactivatedList(ArrayList<Item> deactivatedList) {this.deactivatedList = deactivatedList;}

}