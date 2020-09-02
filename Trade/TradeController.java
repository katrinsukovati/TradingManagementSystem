package Trade;
/*
@author: Allan and Carrie.
@layer: Controller
@design: facade
@collaborator: TradeCreatorController, Trade.TradeEditor, TradeEditorIterator,
               TradeFinalizer, TradeHistory.
 */

import Account.MenuController;
import Account.MenuPresenter;
import Account.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TradeController {
    private final tradeHistory th = new tradeHistory();


    /**
     * This is the first method that should be ran.
     * @param user: the user that uses trade function in this program
     */
    public void run(User user) {
        String input = "default";
        while (!input.equals("exit")) {
            try {
                System.out.println("Enter a number to choose an action or 'exit' to return to previous page.");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("1. View/Edit Current Trades \n2. View Past Trades");
                System.out.println("Go back to main menu and select \"Browse Items\" to create a new trade.");
                input = br.readLine();
                if (input.equals("1")) {
                    view(user);
                } else if (input.equals("2")) {
                    viewHistory(user);
                } else {
                    System.out.println("Invalid Input. Please try again.");
                    break;
                }

            } catch (IOException e) {
                System.out.println("Something went wrong :(");
            }
        }


    }


    /**
     * Views trade history
     * @param user: user who wants to view their trade history
     */
    public void viewHistory(User user){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type 1 to view your most recent 3 trades, " +
                "type 2 to view the most recent 3 users you traded with, " +
                "and type 'exit' to quit");
        List<Object> temp = null;
        try {
            String input = br.readLine();
            while (!input.equals("exit")) {
                if (input.equals("1")){
                    temp = Collections.singletonList(th.getRecentTrades(user));
                    input = "exit";
                } else if (input.equals("2")){
                    temp = Collections.singletonList(th.getFrequentPartners(user));
                    input = "exit";
                }
                if (temp.isEmpty()){
                    System.out.println("You have not done any trades.");
                } else {
                    System.out.println(temp);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when viewing your trade history");
        }
    }

    /**
     * Views ongoing Trades
     */
    public void view(User user) {

        //displays all the trades in the user's currentTrades
        System.out.println("Your ongoing trades are: " + "\n" + generateTrades(user) +
                "\nenter the number corresponded to the trade to take action or view details, enter 'exit' to go back.");
        String input = "";
        while (!input.equals("exit")) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                //user enters the trade they want to view
                input = br.readLine();
                try {
                    int choice = Integer.parseInt(input);
                    String details = "";

                    //fetches the trade selected
                    Trade trade = user.getCurrentTrades().get(choice);

                    //builds a string of the trade's detail
                    int index = getIndexFromUser(user, trade);
                    details = details + "Location: " + trade.getLocation();
                    details = details + "\nTime: " + trade.getTime();
                    details = details + "\nUsers involved: " + generateUsers(trade);
                    details = details + "\nType of trade: " + trade.getTypeOfTrade();

                    //checks if the user has confirmed the current trade
                    if (trade.getCurrentStatus()[index]) {
                        details = details + "\nYour status: Confirmed";
                    } else {
                        details = details + "\nYour status: Not confirmed";
                    }

                    //checks if all parties has confirmed
                    TradeEditor te1 = new TradeEditor();
                    if (te1.allUsersStatus(trade)) {
                        details = details + "\nOverall status: Confirmed";
                    } else {
                        details = details + "\nOverall status: Not confirmed";
                    }

                    //fetches the items and their details depending on whether it is a one/two way trade
                    if (!trade.getTypeOfTrade().equals("temporary") || trade.getTransactions().size() == 0) {
                        if (trade.getClass().equals(oneWayTrade.class)) {
                            details = details + "\n" + getItems(user, (oneWayTrade) trade);
                        } else {
                            details = details + "\n" + getItems(user, (twoWayTrade) trade);
                        }
                    } else if (trade.getTypeOfTrade().equals("temporary") && trade.getTransactions().size() != 0){
                        details = details + "\n *ALL ITEMS SHOULD BE RETURNED DURING THIS TRANSACTION*";
                    }

                    //checks if this trade is temporary, if it is, the previous transaction will be logged
                    if (trade.getTypeOfTrade().equals("temporary") && trade.getTransactions().size() > 0) {
                        details = details + "\nTime of logged transaction: " + trade.getTransactions().get(0).getTime();
                        details = details + "\nLocation of logged transaction: " + trade.getTransactions().get(0).getLocation();
                        if (trade.getTransactions().get(0).getStatus()[0] && trade.getTransactions().get(0).getStatus()[1]) {
                            details = details + "\nStatus of logged transaction: confirmed";
                        } else {
                            details = details + "\nStatus of logged transaction: not confirmed";
                        }
                    }

                    //shows the user the built string of details
                    System.out.println(details);
                    System.out.println("enter 'edit' to edit this trade, 'confirm' to confirm  your status on this trade, 'complete' to confirm that your transaction has taken place, or 'exit'to go back. ");
                    input = br.readLine();
                    TradeEditAndAcceptController TEAC = new TradeEditAndAcceptController();

                    //a user can either edit, confirm, or complete a trade
                    switch (input) {
                        case "edit":
                            TEAC.edit(trade, user);
                            input = "exit";
                            break;
                        case "confirm":
                            TEAC.accept(trade, user);
                            input = "exit";
                            break;
                        case "complete":
                            TEAC.finalize(trade, user);
                            input = "exit";

                            break;
                        default:
                            System.out.println("invalid value");
                            input = "exit";
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("please enter valid and corresponded value");
                }
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
        }
    }

    /**
     *
     * @param user the user with the trades
     * @return a String of all current trades of the user
     */
    private String generateTrades(User user) {
        int trades = user.getCurrentTrades().size();
        int curr = 0;
        String stringOfCurrentTrades = "";
        while (curr < trades) {
            Integer curr1 = curr;
            stringOfCurrentTrades = stringOfCurrentTrades + curr1.toString() + " Trade between " +
                    generateUsers(user.getCurrentTrades().get(curr)) + "\n";
            curr++;
        }
        return stringOfCurrentTrades;
    }

    /**
     * Checks which users are involved in a trade and returns it as a String
     * @param trade the trade that is being looked at
     * @return a String of all users involved in the requested trade
     */
    private String generateUsers(Trade trade) {
        String users = "";
        for (int i = 0; i < trade.getUsersInvolved().length; i++) {
            users = users + "/" + trade.getUsersInvolved()[i].getName();
        }
        return users;
    }

    /**
     *
     * @param user the user whose involved in the trade
     * @param trade the trade that the user is a part of
     * @return the index of the requested user in this trade
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
     * Get the items for a one way trade
     * @param user a user involved in the trade
     * @param trade the one way trade made by the user
     * @return a String of the item involved
     */
    private String getItems(User user, oneWayTrade trade) {
        String detail = "";
        int index = getIndexFromUser(user, trade);
        if (index == 0) {
            detail = "Item.Item you offered: \n";
            for (int i = 0; i < trade.getItemsOffered().length; i++) {
                Integer number = i;
                detail = detail + "\nitem " + number.toString() + "\n" +
                        trade.getItemsOffered()[i];
            }
        } else if (index == 1) {
            detail = "Items you will receive: \n";
            for (int i = 0; i < trade.getItemsOffered().length; i++) {
                Integer number = i;
                detail = detail + "\nitem " + number.toString() + "\n" +
                        trade.getItemsOffered()[i];
            }
        }
        return detail;
    }

    /**
     * get the items in the two way trade
     * @param user a user involved in the trade
     * @param trade the two way trade made by the user
     * @return a Strings of the items involved
     */
    private String getItems(User user, twoWayTrade trade) {
        String detail = "";
        int index = getIndexFromUser(user, trade);
        if (index == 0) {
            detail = "Item.Item you offered: \n";
            for (int i = 0; i < trade.getItemsOffered().length; i++) {
                Integer number = i;
                detail = detail + "\nitem " + number.toString() + "\n" +
                        trade.getItemsOffered()[0][i];
            }
            detail = "Item.Item you will receive: \n";
            for (int i = 0; i < trade.getItemsOffered().length; i++) {
                Integer number = i;
                detail = detail + "\nitem " + number.toString() + "\n" +
                        trade.getItemsOffered()[1][i];
            }
        } else if (index == 1) {
            detail = "Item.Item you offered: \n";
            for (int i = 0; i < trade.getItemsOffered().length; i++) {
                Integer number = i;
                detail = detail + "\nitem " + number.toString() + "\n" +
                        trade.getItemsOffered()[1][i];
            }
            detail = "Item.Item you will receive: \n";
            for (int i = 0; i < trade.getItemsOffered().length; i++) {
                Integer number = i;
                detail = detail + "\nitem " + number.toString() + "\n" +
                        trade.getItemsOffered()[0][i];
            }
        }
        return detail;
    }

}