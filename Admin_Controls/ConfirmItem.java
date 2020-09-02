package Admin_Controls;/*
 * Author: Shruti
 * Description: This is the controller class that manages option 3 of admin menu.
 */

import java.util.*;
import java.util.Scanner;
import Account.*;
import Item.*;

public class ConfirmItem {
    Scanner myObj = new Scanner(System.in);
    ConfirmItemPresenter prompts = new ConfirmItemPresenter();
    ArrayList<Integer> temp = new ArrayList<Integer>();
    MenuPresenter menu = new MenuPresenter();
    ItemRequest itemRequests = new ItemRequest();
    Inventory inventory = new Inventory();
    AdminManager admin = new AdminManager();
    Browse browse = new Browse();

    /**
     * This is the first method that should be ran.
     */
    public void run() {
        String input = "";
        int secondInput = 0;
        int thirdInput = 0;

        while (!input.equals("0")){
            temp = new ArrayList<>();
            prompts.ConfirmItemReset();

            prompts = new ConfirmItemPresenter();
            // Check if there are any items requested by the user
            ArrayList<Item> requestedItems = itemRequests.getRequestedItems();
            if (requestedItems.isEmpty()) {
                System.out.println("There are no requested items. Please enter 0 to return to menu.");
                input = myObj.nextLine();

                if (!input.equals("0")) {
                    do {
                        System.out.println("Invalid Input. Enter 0 to return to menu ");
                        input = myObj.nextLine();
                    } while (!input.equals("0"));
                }
            } else {

                //When there are requested items
                input = "1";
                while (!input.equals("0")){
                    // try-catch to catch any non-integer values entered by user
                    try {
                        prompts.ConfirmItemReset();
                        prompts = new ConfirmItemPresenter();
                        System.out.println("This is a list of requested items: ");
                        System.out.println(browse.itemsToView(itemRequests.getRequestedItems(),1,requestedItems.size()));
                        while (prompts.hasNext()) {
                            System.out.println(prompts.next());
                            secondInput = myObj.nextInt();
                            myObj.nextLine();
                            input = "0";
                        }

                    } catch (Exception e) {
                        myObj.nextLine();
                        do {
                            System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                            input = myObj.nextLine();
                        } while (!input.equals("1") && !input.equals("0"));

                    }
                }

                //checks if entered number is on the requested items list
                if(secondInput > 0 && secondInput <= requestedItems.size()) {
                    temp.add(secondInput);
                    Item item = requestedItems.get(secondInput-1);
                    input = "1";
                    while (!input.equals("0")){
                        try {
                            System.out.println(item);
                            menu.confirmMenu();
                            System.out.println(menu.printMenu());
                            thirdInput = myObj.nextInt();
                            myObj.nextLine();
                            input = "0";

                        } catch (Exception e) {
                            myObj.nextLine();
                            do {
                                System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                                input = myObj.nextLine();
                            } while (!input.equals("1") && !input.equals("0"));

                        }
                    }

                    if (thirdInput == 1) {
                        // confirming item
                        admin.confirmItem(item, itemRequests, inventory);
                        System.out.println("The item has been confirmed");
                        input = "1";
                    } else if (thirdInput == 2) {
                        // item is deleted from requests and not added to inventory
                        itemRequests.removeItem(item);
                        System.out.println("The item has been denied");
                        input = "1";
                    } else if (thirdInput != 3) {
                        input = "2";
                        while (!input.equals("1") && !input.equals("0")){
                            System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                            input = myObj.nextLine();
                        }
                        thirdInput = 3;
                    } else {
                        input = "1";
                    }
                } else if (secondInput == 0) {
                    input = "0";

                } else{
                    do {
                        System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                        input = myObj.nextLine();
                    } while (!input.equals("1") && !input.equals("0"));
                }
                }



            }


        }
    }