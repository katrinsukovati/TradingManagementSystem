package Trade;

import Account.MenuPresenter;
import Account.User;
import Item.Item;
import User_Controls.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
A class responsible for creating trade.
Is called in browse items in the main menu.
@author: Carrie
@layer controller
 */
public class TradeCreatorController{
    private Item borrowing = null;
    private boolean undoable = false;


    /**
     * Gets the borrowing item
     * Helper method for create()
     * @param user: the user of the program
     */
    public Item getBorrowing(User user){
        return borrowing;
    }

    /**
     * Sets the borrowing item
     * Used in BrowseItemController
     * @param user: the user of the program
     * @param newBorrowing: set this item to be the item to borrow
     */
    public void setBorrowing(User user, Item newBorrowing){
        borrowing = newBorrowing;
    }

    /**
     * Resets borrowing to null after a trade
     *
     */
    private void resetBorrowing(){
        borrowing = null;
    }

    /**
     *
     * @param user1 user creating trade
     * @param user2 the target of trade
     * @return Arraylist of items both in the user's givelist and the target's wishlist
     */
    public ArrayList<Item> itemSuggestion(User user1, User user2){
        ArrayList<Item> availableItems = new ArrayList<>();
        for (Item i: user1.getGiveList()) {
            if (user2.getWishList().contains(i)) {
                availableItems.add(i);
            }
        }
        return availableItems;
    }

    /**
     * Gets the lending item from user input from viewing their givelist
     * Helper method for create()
     * @param user: the user of the program
     */
    private Item getLending(User user, User user2) {
        UserGivelistIterator prompts;
        String input1 = "1";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Item lending = null;
        while (!input1.equals("2")) {
            System.out.println("Enter a number to perform an action");
            System.out.println("1. Use item suggestion tool \n2. Continue without suggestion tool");
            try {
                input1 = br.readLine();
                if (input1.equals("1")  && itemSuggestion(user, user2).size() > 0) {
                    SuggestionListIterator prompts1 = new SuggestionListIterator(itemSuggestion(user, user2));

                    while (!input1.equals("3")) {
                        if (user.getGiveList().size() != 0) {
                            System.out.println("Item: " + user.getGiveList().get(prompts1.getCurrent()).getItemName());
                            System.out.println("1. Choose the current item to lend\n2. Go to the next item\n" +
                                    "3. Exit suggestion tool");
                            input1 = br.readLine();
                            if (input1.equals("1")) {
                                lending = user.getGiveList().get(prompts1.getCurrent());
                                return lending;
                            } else if (input1.equals("2")) {
                                if (prompts1.hasNext()) {
                                    prompts1.next();
                                } else {
                                    System.out.println("There are no more suggested items. Enter 1 to view suggested items again or enter 3 to exit suggestion tool.");
                                    input1 = br.readLine();
                                    if (input1.equals("1")) {
                                        prompts1.suggestionListReset();
                                    }
                                }
                            } else if (!input1.equals("3")) {
                                System.out.println("Invalid input. Enter 3 to exit suggestion tool.");
                            }
                        } else {
                            System.out.println("There is nothing you can lend, because your givelist is empty");
                            input1 = "3";
                        }
                    }
                    System.out.println("Suggestion tool closed, now displaying your items available for lending:");
                    break;
                } else if (input1.equals("1") && itemSuggestion(user, user2).size() == 0){
                    System.out.println("No items to suggest");
                } else if (!input1.equals("2")){
                    System.out.println("Invalid input.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("You will now be directed to your give list inventory.");
        prompts = new UserGivelistIterator(user);
        int current = 0;
        try {
            String input = "1";
            if (user.getGiveList().size() > 0) {
                while (!input.equals("3")) {
                    System.out.println("Item: " + user.getGiveList().get(current).getItemName());
                    System.out.println("1. Choose the current item to lend\n2. Go to the next item\n" +
                            "3. Skip this section");
                    input = br.readLine();
                    if (input.equals("1")) {
                        lending = user.getGiveList().get(prompts.getCurrent());
                        input = "3";
                        return lending;
                    } else if (input.equals("2")) {
                        if (current + 1 < user.getGiveList().size()) {
                            prompts.next();
                            current += 1;
                        } else {
                            System.out.println("You have no more items to lend. Enter 1 to view items again or enter 3 to skip this section.");
                            input = br.readLine();
                            if (input.equals("1")) {
                                prompts.UserGiveListIteratorReset();
                                current = 0;
                            }
                        }
                    } else if (!input.equals("3")) {
                        System.out.println("Invalid input. Enter 3 to exit suggestion tool.");
                    }

                }
            } else {
                System.out.print("Your give list is empty, this trade will continue without an item from you. \n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lending;
    }

    /**
     * Gets the time, location, & type of trade from user input when creating a trade
     * Helper method for create()
     * @param user: the user of the program
     */
    private ArrayList<String> getTimeLocationType(User user){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TradeCreatorIterator prompts = new TradeCreatorIterator();
        ArrayList<String> temp = new ArrayList<>();
        int curr = 0;
        String input = "";
        try {
            while (prompts.hasNext() && temp.size() < 4) {
                if (prompts.hasNext()) {
                    System.out.println(prompts.next());
                    input = br.readLine();
                }
                temp.add(input);
                curr++;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while inputting time, location, & type of trade");
        }
        return temp;
    }

    /**
     * If user is doing a 1-way lending trade, they need to input the username of that borrower
     * Helper method for create()
     * @param user: the user of the program
     */
    private User getBorrower(User user){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        UserSystem us = new UserSystem();
        System.out.println("You are now creating a one-way trade to lend an item");
        System.out.println("Please type in the username of the person you want to trade with");
        String username = null;
        try {
            username = br.readLine();
        } catch (IOException e) {
            System.out.println("Something went wrong when getting the username of your borrower");
        }
        return us.getUser(username);
    }

    /**
     * Creates a new Trade - oneWayTrade or twoWayTrade
     * @param user: the user of the program
     */
    public void create(User user, User user2) {
        MenuPresenter menu = new MenuPresenter();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type 'exit' to quit or ok to continue.");
        if (user.getNumTradeThisWeek() > user.getLimitPerWeek()) {
            System.out.println("Trade cannot be created because you have surpassed the limit of trades " +
                    "that can be created per week.");
        } else {
            try {
                String input = br.readLine();
                while (!input.equals("exit")) {
                    Item borrowing = getBorrowing(user);
                    Item lending = getLending(user, user2);
                    ArrayList<String> timeLocationType = getTimeLocationType(user);
                    String time = timeLocationType.get(0);
                    String location = timeLocationType.get(1);
                    // get permanent || temporary
                    String type = timeLocationType.get(2);
                    // distinguish between 2-way, 1-way borrowing, & 1-way lending trade
                    User[] usersInvolved = new User[0];
                    User[][] lenderRelations = new User[0][];
                    Item[][] itemsOffered = new Item[0][];
                    Item[] itemOffered = new Item[0];
                    if (borrowing != null && lending != null) {
                        // auto-generate Item.Item[] || Item.Item[][] itemsOffered
                        itemsOffered = new Item[][]{{borrowing}, {lending}};
                        // auto-generate User[] usersInvolved
                        User lender;
                        if (borrowing.getPastOwners().isEmpty()){
                            lender =borrowing.getItemOwner();
                        } else {
                            lender = borrowing.getPastOwners().get(borrowing.getPastOwners().size() - 1);
                        }
                        usersInvolved = new User[]{lender, user};
                        // auto-generate User[][] lenderRelations
                        lenderRelations = new User[][]{{lender, user}};
                    } else if (borrowing != null) {
                        // the user is only borrowing
                        // auto-generate Item.Item[] || Item.Item[][] itemsOffered
                        itemOffered = new Item[]{borrowing};
                        // auto-generate User[] usersInvolved
                        User lender;
                        if (borrowing.getPastOwners().isEmpty()){
                            lender = borrowing.getItemOwner();
                        } else {
                            lender = borrowing.getPastOwners().get(borrowing.getPastOwners().size() - 1);
                        }
                        usersInvolved = new User[]{lender, user};
                        // auto-generate User[][] lenderRelations
                        lenderRelations = new User[][]{{lender, user}};
                    } else if (lending != null) {
                        // the user is only lending
                        // auto-generate Item.Item[] || Item.Item[][] itemsOffered
                        itemOffered = new Item[]{lending};
                        // auto-generate User[] usersInvolved
                        User borrower = getBorrower(user);
                        usersInvolved = new User[]{user, borrower};
                        // auto-generate User[][] lenderRelations
                        lenderRelations = new User[][]{{user, borrower}};
                    }

                    // start evaluating the trade in tradeCreator
                    tradeCreator tc = new tradeCreator();
                    if (tc.isTradeValid(location, time, usersInvolved, type, itemsOffered, lenderRelations)) {
                        System.out.println("Two-way trade successfully created!");
                        user.setNumTradeThisWeek(user.getNumTradeThisWeek() + 1);
                        becomesUndoable();
                    } else if (tc.isTradeValid(location, time, usersInvolved, type, itemOffered, lenderRelations)) {
                        System.out.println("One-way trade successfully created!");
                        user.setNumTradeThisWeek(user.getNumTradeThisWeek() + 1);
                        becomesUndoable();
                    } else {
                        System.out.println("This trade is invalid or you don't have permission for this trade. \nPress enter to continue.");
                    }
                    resetBorrowing();
                    input = br.readLine();
                }
            } catch (IOException e1) {
                System.out.println("Something went wrong while creating this trade.");
            } catch (NullPointerException e2) {
                System.out.println("You are all good!");
            }
        }

    }

    //Below are Undo-related methods

    /**
     * getter for undoable
     * @return undoable
     */
    public boolean getUndoable(){
        return undoable;
    }
    /**
     * when a trade is successfully created, undoable changes to true
     */
    public void becomesUndoable(){
        undoable = true;
    }

    /**
     * resets undoable after undo
     */
    public void resetsUndoable(){
        undoable = false;
    }
}