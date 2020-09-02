// Author: Allan
package Trade;

import Account.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TradeEditAndAcceptController {

    /**
     * Get index of the user involved in the trade
     * @param user the user involved in the trade
     * @param trade the specific trade
     * @return the index of the user
     */
    private int getIndexFromUser(User user, Trade trade) {
        User[] users = trade.getUsersInvolved();
        int index = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] == user) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Checks if all users have confirmed the completetion of this trade
     * @param transaction the confirmed trade
     * @return true if transaction is status is confirmed
     */
    private boolean transactionStatusAll(Transaction transaction){
        boolean ret = true;
        for (int i = 0; i < transaction.getStatus().length; i++){
            if (!transaction.getStatus()[i]){
                ret = false;
            }
        }
        return ret;
    }

    /**
     * resolves the trade if all users have confirmed the completion of transactions
     * @param trade the specified trade
     * @param user the user involved in this trade
     * @param transaction the confirmed trade between the users
     */
    private void resolve(Trade trade, User user, int transaction){
        int index = getIndexFromUser(user, trade);

        //records that the user has confirmed the completion of this transaction
        trade.getTransactions().get(transaction).setStatus(index, true);

        //resolves the trade if all users has confirmed the completion of transaction
        if (transactionStatusAll(trade.getTransactions().get(transaction))) {

            //updates inventories
            TradeFinalizer tf = new TradeFinalizer();
            if (trade.getClass().equals(oneWayTrade.class)) {
                tf.PostTradeUserUpdate((oneWayTrade) trade);
            } else if (trade.getClass().equals(twoWayTrade.class)) {
                tf.PostTradeUserUpdate((twoWayTrade) trade);
            }

            //removes trade from current trades and into past trades.
            for (User user1 : trade.getUsersInvolved()) {
                user1.getCurrentTrades().remove(trade);
                user1.getPastTrades().add(trade);
            }
            trade.getTransactions().get(transaction).setFinalStatus(true);
            System.out.println("This trade has been completed and resolved.");
        } else {

            //no action is taken until all parties has confirmed
            System.out.println("your response has been recorded, changes will be made once other parties confirms.");
        }
    }

    /**
     * Finalize the trade if all users have confirmed
     * @param trade the specified trade between the users
     * @param user the user involved in the trade
     */
    public void finalize(Trade trade, User user) {
        TradeEditor te = new TradeEditor();

        //checks if all users has confirmed
        if (!te.allUsersStatus(trade)){
            System.out.println("Trade has not been confirmed yet, please confirm first to set up transaction");
        } else {

            //resolves a permanent trade
            if (trade.getTypeOfTrade().equals("permanent")) {
                resolve(trade, user, 0);

                //if the current trade is temporary and no transactions has been made yet, no transactions can be confirmed
            } else if (trade.getTypeOfTrade().equals("temporary") && trade.getTransactions().size() == 1) {
                System.out.println("This is a temporary trade, please confirm the transaction for the resolving transaction.");

                //resolves the closing transaction of a temporary trade
            } else if (trade.getTypeOfTrade().equals("temporary") && trade.getTransactions().size() == 2) {
                resolve(trade, user, 1);
            }
        }
    }

    /**
     * Allows user to confirm the trade
     * @param trade the specified trade
     * @param user the user involved in the trade
     */
    public void accept(Trade trade, User user) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        int index = getIndexFromUser(user, trade);

        //checks if the user has already confirmed
        if (trade.getCurrentStatus()[index]) {
            System.out.println("You have already confirmed");
        } else {
            TradeEditor te = new TradeEditor();

            //logs the user's confirmation
            te.changeCurrentStatus(trade, user, true);
            System.out.println("Your confirmation has been logged.");

            //if all users has confirmed to this trade, a transaction is created
            if (te.allUsersStatus(trade)) {
                if (trade.getTypeOfTrade().equals("permanent")) {
                    Transaction t1 = new Transaction(trade.getTime(), trade.getLocation(), trade.getUsersInvolved().length);
                    trade.getTransactions().add(t1);
                    System.out.println("The transaction is confirmed, please log the success of the transaction after completion by entering 'complete' in the previous menu.");

                    //if this is a temporary trade and  this is the first transaction, a second transaction date will be automatically set up
                    // one month after the first, users can edit details
                } else if(trade.getTypeOfTrade().equals("temporary") && trade.getTransactions().size() == 0) {
                    try {
                        Transaction t1 = new Transaction(trade.getTime(), trade.getLocation(), trade.getUsersInvolved().length);
                        trade.getTransactions().add(t1);
                        Date date1 = format.parse(trade.getTime());
                        Calendar c = Calendar.getInstance();
                        c.setTime(date1);
                        c.add(Calendar.MONTH, 1);
                        Date newDate = c.getTime();
                        trade.getEditsDone()[0] = 0;
                        trade.getEditsDone()[1] = 0;
                        trade.setTime(newDate.toString());
                        trade.setCurrentStatus(0, false);
                        trade.setCurrentStatus(1, false);

                        //the first transaction of a temporary trade will not require confirmation of transaction after meetup
                        //inventories are updated for temporary transaction
                        TradeFinalizer tf = new TradeFinalizer();
                        if (trade.getClass().equals(oneWayTrade.class)) {
                            tf.PostTradeUserUpdate((oneWayTrade) trade);
                        } else if (trade.getClass().equals(twoWayTrade.class)) {
                            tf.PostTradeUserUpdate((twoWayTrade) trade);
                        }

                        //displays the details of the automatically generated second transaction
                        System.out.println("This temporary transaction has been logged, please confirm the finalizing transaction");
                        System.out.println("Time: " + newDate.toString());
                        System.out.println("Location: " + trade.getLocation());
                        System.out.println("enter 'confirm' to confirm this transaction, 'edit' to change the parameters");
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        try{

                            //allows user to either confirm the detail or ammend to it
                            String input = br.readLine();
                            if (input.equals("edit")) {
                                edit(trade, user);
                            } else if (input.equals("confirm")) {
                                accept(trade, user);
                            } else {
                                System.out.println("Invalid input");
                            }
                        } catch (IOException e) {
                            System.out.println("something went wrong.");
                        }
                    } catch (ParseException e) {
                        System.out.println("Request not processed because time is in the wrong format.");
                    }

                    //if this is the closing (second) transaction of a temporary trade, the transaction will require
                    // confirmation of completion after meetup
                } else if (trade.getTypeOfTrade().equals("temporary") && trade.getTransactions().size() == 1) {
                    Transaction t2 = new Transaction(trade.getTime(), trade.getLocation(), trade.getUsersInvolved().length);
                    trade.getTransactions().add(t2);
                    System.out.println("The transaction is confirmed, please log the success of the transaction after completion  by entering 'complete' in  the  previous menu.");
                }
            }
        }
    }

    /**
     * Allows user to edit the trade
     * @param trade the specified trade
     * @param user the user involved in the trade
     */
    public void edit(Trade trade, User user) {

        TradeEditor te1 = new TradeEditor();
        if (te1.hasEdits(trade, user)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean status = false;
            TradeEditorIterator iterator = new TradeEditorIterator();
            ArrayList<String> temp = new ArrayList<>();
            try {

                //checks if user wants to continue edit
                System.out.println("Type 'yes' if you would like to continue with this edit and 'no' to go back");
                String input = br.readLine();
                if (input.equals("yes")) {
                    while (!status) {
                        iterator.reset();
                        temp.clear();

                        //iterator displays the fields the user has to fill
                        while (iterator.hasNext()) {
                            System.out.println("enter '/' to skip the current field");
                            System.out.println(iterator.next());
                            input = br.readLine();

                            //temp stores the information entered by user
                            temp.add(input);
                        }

                        //displays changes and asks user if they are okay with change
                        String str = "Enter 'yes' if you agree with the changes and 'no' to change again \n New time:" +
                                temp.get(0) + "\n" + "New location:" + temp.get(1);
                        System.out.println(str);
                        input = br.readLine();
                        if (input.equals("yes")) {
                            status = true;
                        }
                    }


                    if (temp.size() != 0) {
                        boolean statusError = false;
                        if (!temp.get(0).equals("/")) {

                            //checks if the trade is temporary, if it is and this is the second transaction,
                            //program checks if the time is more than a month after the first transaction.
                            if(trade.getTransactions().size()>0 && trade.getTypeOfTrade().equals("temporary")) {
                                try{
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                                    Date date1 = format.parse(trade.getTransactions().get(0).getTime());
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(date1);
                                    c.add(Calendar.MONTH, 1);
                                    Date maxDate = c.getTime();
                                    Date date2 = format.parse(temp.get(0));
                                    if (date2.compareTo(maxDate) > 0) {
                                        System.out.println("The return date must be within a month after the first transaction.");
                                        statusError = true;
                                    } else {
                                        te1.timeEditor(trade, temp.get(0), user);
                                    }
                                } catch(ParseException e) {
                                    System.out.println("Date format incorrect");
                                    statusError = true;
                                }
                            } else {

                                //updates time of trade
                                te1.timeEditor(trade, temp.get(0), user);
                            }
                        }
                        if (!temp.get(1).equals("/") && !statusError) {

                            //updates location of trade
                            te1.locationEditor(trade, temp.get(1), user);
                        }

                        //if no exceptions were raised, statuses of other users will be updated
                        if(!statusError) {
                            te1.changeCurrentStatus(trade, user, true);
                            te1.changeStatusAllElse(trade, user);
                            te1.addEdit(trade, user);
                            System.out.println("changes has been made.");
                        } else {
                            System.out.println("Your request was not processed, please try again.");
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }

        } else {
            System.out.println("No edits left.");
        }

    }
}