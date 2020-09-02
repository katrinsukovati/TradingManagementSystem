package Trade;
/*
 * Deals with checking if the trade is valid and then instantiates a trade.
 * @layer: Use case
 * @collaborator: Account.User, Trade
 * @author: Katrin
 *
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import Account.User;
import Item.*;

public class tradeCreator {

    /**
     * @param location: string of meetup location for trade to take place
     * @param time: string of time for meetup
     * @param usersInvolved: array storing the two users involved
     * @param typeOfTrade: permanent or temporary
     * @param itemsOffered: the items offered in this trade that's 1 way
     * @param lenderRelation: embedded array where index 0 is the lender and index 1 is the borrower
     * @return a trade object if it is valid
     */
    public boolean isTradeValid(String location, String time, User[] usersInvolved, String typeOfTrade,
                                Item[] itemsOffered, User[][] lenderRelation) {

        //checks if the type is correct
        if (!typeOfTrade.equals("temporary") && !typeOfTrade.equals("permanent")){
            System.out.println("type of trade entered is invalid");
            return false;
        }



        // One way trade
        if (itemsOffered.length == 1) {
            // Instantiate a trade
            oneWayTrade testTrade = new oneWayTrade(location, time, usersInvolved, typeOfTrade, itemsOffered, lenderRelation);

            // Get the lender since its a 1 way trade
            for (Map.Entry<User, User> entry : testTrade.getLenderRelations().entrySet()) {
                User lender = entry.getKey();

                // If the borrower lent more than borrowed, owns the item, and none of the users are frozen, trade is valid
                if ((lender.getLentList().size() >= lender.getBorrowedList().size())
                        && (!testTrade.getUsersInvolved()[0].getFrozenStatus())
                        && (!testTrade.getUsersInvolved()[1].getFrozenStatus())
                        && (lender.getGiveList().contains(itemsOffered[0]))) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @param location: string of meetup location for trade to take place
     * @param time: string of time for meetup
     * @param usersInvolved: array storing the two users involved
     * @param typeOfTrade: permanent or temporary
     * @param itemsOffered: the items offered in this trade that's 1 way
     * @param lenderRelation: embedded array where index 0 is the lender and index 1 is the borrower
     * @return a trade object if it is valid
     */
    public boolean isTradeValid(String location, String time, User[] usersInvolved, String typeOfTrade, Item[][] itemsOffered, User[][] lenderRelation) {

        // Instantiate a 2 way trade
        twoWayTrade testTrade = new twoWayTrade(location,time,usersInvolved,typeOfTrade,itemsOffered,lenderRelation);

        //checks if the type is correct
        if (!typeOfTrade.equals("temporary") && !typeOfTrade.equals("permanent")){
            System.out.println("type of trade entered is invalid");
            return false;
        }



        // Get the lender and borrower
        for (Map.Entry<User, User> entry : testTrade.getLenderRelations().entrySet()) {
            User lender = entry.getKey();
            User borrower = entry.getValue();

            boolean user1Owns = false;
            boolean user2Owns = false;

            // Check if both users contain the items offered
            for (int i = 0; i < itemsOffered.length; i ++){
                for (int j = 0; i< itemsOffered.length; i++){
                    if (lender.getGiveList().contains(itemsOffered[i][j])){ user1Owns = true;}
                    if (borrower.getGiveList().contains(itemsOffered[i][j])){ user2Owns = true;}
                }
            }

            // If both users own items they are offering and none of the users are frozen, the trade is valid
            if (!testTrade.getUsersInvolved()[0].getFrozenStatus() && (!testTrade.getUsersInvolved()[1].getFrozenStatus())
                    && user1Owns && user2Owns){
                return true;
            }
        }
        // trade is not valid
        return false;
    }

}