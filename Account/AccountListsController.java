package Account;
/*
 *Author: Katrin
 * Description: Account list controller that manages all the lists.
 */

import java.util.Scanner;
import User_Controls.UserManager;
import Item.*;

public class AccountListsController {

    private final Scanner input = new Scanner(System.in);
    private final MenuPresenter menu = new MenuPresenter();

    /**
     * @param account the users account
     */
    //this is the first function to be run
    public void run(User account) {

        menu.accountListMenu();
        System.out.println(menu.printMenu());
        String inputNum;
        inputNum = input.nextLine();

        while (!inputNum.equals("5")) {
            switch (inputNum) {
                // Input: 1 = View the items that you can give to other users
                case "1":
                    giveLst(account);
                    menu.accountListMenu();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                //Input: 2 = View the items that you have lent to other users
                case "2":
                    lentLst(account);
                    menu.accountListMenu();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                // Input: 3 = View the items that you have borrowed from other users
                case "3":
                    borrowedLst(account);
                    menu.accountListMenu();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                // Input: 4 = View/edit your wish list
                case "4":
                    wishLst(account);
                    menu.accountListMenu();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                default:
                    System.out.println("Invalid input! Please try again");
                    menu.accountListMenu();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
            }
        }
    }

    /**
     * @param account the users account
     */
    public void giveLst(User account) {
        if (account.getGiveList().isEmpty()) {
            System.out.println("You have no items to give to others. You will now be redirected to the previous menu...");
        }

        else {
            Browse giveBrowse = new Browse();
            System.out.println("Here is the list of the items that you can give to others: ");
            System.out.println(giveBrowse.itemsToView(account.getGiveList(), 1, account.getGiveList().size()));
            System.out.println("You will now be redirected to the previous menu...");
        }
    }

    /**
     * @param account the users account
     */
    public void lentLst(User account) {

        if (account.getLentList().isEmpty()) {
            System.out.println("You have not lent any items to other users. You will now be redirected to the previous menu...");
        }

        else {
            Browse lentBrowse = new Browse();
            System.out.println("Here is the list of the items that you have lent to others: ");
            System.out.println(lentBrowse.itemsToView(account.getLentList(), 1, account.getLentList().size()));
            System.out.println("You will now be redirected to the previous menu...");
        }
    }

    /**
     * @param account the users account
     */
    public void borrowedLst(User account) {

        if (account.getBorrowedList().isEmpty()) {
            System.out.println("You have not borrowed any items from other users. You will now be redirected to the previous menu...");
        }

        else {
            Browse borrowedBrowse = new Browse();
            System.out.println("Here is a list of the items that you borrowed: ");
            System.out.println(borrowedBrowse.itemsToView(account.getBorrowedList(), 1, account.getBorrowedList().size()));
            System.out.println("You will now be redirected to the previous menu...");
        }
    }

    /**
     * @param account the users account
     */
    public void wishLst(User account) {

        if (account.getWishList().isEmpty()) {
            System.out.println("You have no items in your wish list. You will now be redirected to the previous menu...");
        }

        else {
            //Print out wish list
            System.out.println("Here is your wish list: ");
            Browse wishBrowse = new Browse();
            System.out.println(wishBrowse.itemsToView(account.getWishList(), 1, account.getWishList().size()));

            // Users options
            menu.wishListViewMenu();
            System.out.println(menu.printMenu());
            String inputNum = input.nextLine();
            int itemSelect;
            switch (inputNum) {
                case "1":
                    System.out.println("Please enter the number of the item that you wish to view or 0 to return: ");
                    itemSelect = input.nextInt();
                    input.nextLine();

                    while (itemSelect < 0 || itemSelect > account.getWishList().size()) {
                        System.out.println("That item does not exit.");
                        System.out.println("Please enter the number of the item that you wish to view or 0 to return: ");
                        itemSelect = input.nextInt();
                        input.nextLine();
                    }

                    if (itemSelect != 0) {
                        System.out.println(account.getWishList().get(itemSelect - 1));
                    }
                    break;

                case "2":
                    //Remove item from wishlist
                    UserManager userManager = new UserManager();
                    System.out.println("Please enter the number of the item that you wish to remove: ");
                    try {
                        int inputNum2 = Integer.parseInt(input.nextLine());

                        if (inputNum2 > 0 && inputNum2 <= account.getWishList().size()){
                            userManager.removeFromWishList(account, account.getWishList().get(inputNum2-1));
                            System.out.println("The item has been removed. You will now be redirected to the previous menu...");
                        }

                        else {
                            System.out.println("This item is not on your wishlist. You will now be redirected to the previous menu... ");
                        }
                    }

                    catch (Exception e) {
                        System.out.println("Invalid Input. You will now be redirected to the previous menu... ");
                    }
                    break;

                case "3":
                    System.out.println("You will now be redirected to the previous menu...");
                    break;

                default:
                    System.out.println("Invalid Input. Please try again.");
                    System.out.println(menu.printMenu());
                    break;
            }
        }
    }
}