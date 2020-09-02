package Account;
/*
@author: Carrie
 */

import Item.Browse;
import Item.Inventory;
import Item.Item;
import Trade.TradeCreatorController;
import User_Controls.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class BrowseItemsController {

    // creating the inventory
    private final Inventory inventoryToBrowse = new Inventory();

    /**
     * @param user the users account
     */
    public void run(User user) {
        Scanner myObj = new Scanner(System.in);
        UserManager userManager = new UserManager();
        MenuPresenter menu = new MenuPresenter();
        String input;

        browseTheInventory(user);

        if(!inventoryToBrowse.getItemStorage().isEmpty()) {
            System.out.println("Choose from the following options: (Enter numbers only)");
            System.out.println("Enter 0 to return to the main menu.");
            System.out.println("Enter the number of the item you are interested for more options.");
        }
        try {
            input = myObj.nextLine();

            while (isNumeric(input) && !input.equals("0")) {
                int itemIndex = parseInt(input) - 1;
                if (0 <= itemIndex && itemIndex < (inventoryToBrowse.getItemStorage().size())) {
                    Item item = inventoryToBrowse.getItemStorage().get(itemIndex);
                    menu.itemActionMenu();
                    System.out.println(menu.printMenu());
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    String input1 = br.readLine();
                    switch (input1) {
                        case "1":
                            if (userManager.addToWishList(user, item)) {
                                System.out.println("Item added to wishlist.");
                                System.out.println("Enter 0 to return to menu.");
                            } else {
                                System.out.println("An error has occurred, item not added.");
                                System.out.println("This item is already in your wishlist or you own this item.");
                                System.out.println("Enter 0 to return to menu.");

                            }
                            break;

                        case "2":
                            if (!item.getItemOwner().getUsername().equals(user.getUsername())) {
                                TradeCreatorController tradeCreatorController = new TradeCreatorController();
                                tradeCreatorController.setBorrowing(user, item);
                                tradeCreatorController.create(user, item.getItemOwner());
                            } else {
                                System.out.println("You cannot create a trade with yourself.");
                                System.out.println("Enter 0 to return to previous menu.");
                            }
                            break;

                        case "00":
                            browseTheInventory(user);
                            break;

                        default:
                            System.out.println("Invalid Input. Enter 0 to return to the main menu.");
                            break;
                    }
                }
                input = myObj.nextLine();
            }

            System.out.println("You will now be redirected to the previous menu...");

        }
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when browsing items.");
        }
    }

    /**
     * Checks if string input is numeric
     * @param input: string input
     * @return whether this string is numeric
     */
    private boolean isNumeric(String input) {
        return input.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * @param user the users account
     */
    public void browseTheInventory(User user) {
        if(inventoryToBrowse.getItemStorage().isEmpty()){
            System.out.println("There are no items in the inventory. Enter 0 to return to the menu." );
        }

        else{
            Browse inventoryBrowse = new Browse();
            System.out.println("Here are a list of items in the inventory.");
            System.out.println(inventoryBrowse.itemsToView(inventoryToBrowse.getItemStorage(),1,inventoryToBrowse.getItemStorage().size()));
        }

    }
}