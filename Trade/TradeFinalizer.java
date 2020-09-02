package Trade;/*
* Deals with changes in Account.User after a Trade is complete.
* @layer: Use case
* @collaborator: Account.User, Trade, Trade.oneWayTrade, Trade.twoWayTrade
* @author: Carrie
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import Account.User;
import Item.*;

public class TradeFinalizer {

    /**
     * For the trade of a single item, Updates wishList, giveList, borrowedList, & lentList
     * of the borrower and the lender
     * A helper method for PostTradeUserUpdate
     * @param item:     the item in the trade
     * @param lender:   Account.User who lends item
     * @param borrower: Account.User who borrows item
     */
    private void PostTrade1ItemUserUpdateTemp(Item item, User lender, User borrower) {
        ArrayList<Item> newGivenList = lender.getGiveList();
        newGivenList.remove(item);
        lender.setGiveList(newGivenList);
        ArrayList<Item> newLentList = lender.getLentList();
        newLentList.add(item);
        lender.setLentList(newLentList);
        ArrayList<Item> newWishList = borrower.getWishList();
        newWishList.remove(item);
        borrower.setWishList(newWishList);
        ArrayList<Item> newBorrowedList = borrower.getBorrowedList();
        newBorrowedList.add(item);
        borrower.setBorrowedList(newBorrowedList);
    }

    /**
     * Update the item owner and the list of past owners for the item after trade occurs
     * @param item the item in the trade
     * @param lender The lender of the item
     * @param borrower The borrower of the item
     */
    private void PostTrade1ItemUserUpdatePerm(Item item, User lender, User borrower) {
        ArrayList<Item> newGivenList = lender.getGiveList();
        newGivenList.remove(item);
        lender.setGiveList(newGivenList);
        ArrayList<Item> newWishList = borrower.getWishList();
        newWishList.remove(item);
        borrower.setWishList(newWishList);
        ArrayList<Item> newGiveList = borrower.getGiveList();
        newGiveList.add(item);
        borrower.setBorrowedList(newGiveList);
        item.setItemOwner(borrower);
        item.addPastOwners(lender);
    }

    /**
     * Updates wishList, giveList, borrowedList, & lentList of the borrower and the lender, for Trade.oneWayTrade
     * @param trade: the Trade.oneWayTrade that happened
     * @overloading
     */
    public void PostTradeUserUpdate(oneWayTrade trade) {
        // Get items
        Item[] items = trade.getItemsOffered();
        // Get keys and values (Users) from Hashmap from trade.getLenderRelations()
        for (Map.Entry<User, User> entry : trade.getLenderRelations().entrySet()) {
            User lender = entry.getKey();
            User borrower = entry.getValue();
            // Updates lender & borrower lists
            for (Item item : items) {
                if (trade.getTypeOfTrade().equals("permanent")) {
                    PostTrade1ItemUserUpdatePerm(item, lender, borrower);
                } else {
                    PostTrade1ItemUserUpdateTemp(item, lender, borrower);
                }
            }
        }
    }

    /**
     * Updates wishlist and inventory of the borrower and the lender, for Trade.twoWayTrade
     * @param trade: the Trade.twoWayTrade that happened
     * @overloading
     */
    public void PostTradeUserUpdate(twoWayTrade trade) {
        // Get items
        Item[][] items = trade.getItemsOffered();
        // get Users and Items
        User lender0 = trade.getUsersInvolved()[0]; // get the first lender, who is the second borrower
        User lender1 = trade.getUsersInvolved()[1]; // get the second lender, who is the first borrower
        Item[] items0 = items[0]; // get the first items
        Item[] items1 = items[1]; // get the second items
        // Update first Item.Item.Item.Item Trade
        for (Item item0 : items0) {
            if (trade.getTypeOfTrade().equals("permanent")) {
                PostTrade1ItemUserUpdatePerm(item0, lender0, lender1);
            } else {
                PostTrade1ItemUserUpdateTemp(item0, lender0, lender1);
            }
        }
        // Update second Item.Item.Item.Item Trade
        for (Item item1 : items1) {
            if (trade.getTypeOfTrade().equals("permanent")) {
                PostTrade1ItemUserUpdatePerm(item1, lender1, lender0);
            } else {
                PostTrade1ItemUserUpdateTemp(item1, lender1, lender0);
            }
        }
    }

}