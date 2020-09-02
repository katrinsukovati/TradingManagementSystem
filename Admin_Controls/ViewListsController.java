package Admin_Controls;
/*
 * Author: Shruti
 * Description: This is the class that controls option 4 of the adminAccessUserMenu.
 */

import Account.User;
import Account.MenuPresenter;
import User_Controls.UserSystem;
import java.util.Scanner;

public class ViewListsController {
    private final MenuPresenter menu = new MenuPresenter();
    private final Scanner input = new Scanner(System.in);

    public void run(User account) {

        menu.adminAccessUserListsMenu();
        System.out.println(menu.printMenu());
        String inputNum = input.nextLine();

        switch (inputNum) {
            // prints out user's wish list
            case "1":
                viewWishList(account);
                break;
            //prints out the user's give list
            case "2":
                viewGiveList(account);
                break;
            //prints out the user's lent list
            case "3":
                viewLentList(account);
                break;
            //prints out the user's borrowed list
            case "4":
                viewBorrowedList(account);
                break;
            //returns to menu
            case "5":
                System.out.println("Redirecting to the previous menu...");
                menu.adminAccessUserMenu();
                break;
            // if invalid input is entered
            default:
                System.out.println("Invalid input! Please try again");
                run(account);
                break;
        }
    }

    /**
     * @param account the users account
     */
    public void viewWishList(User account) {
        System.out.println(account.getWishList());
    }

    /**
     * @param account the users account
     */
    public void viewGiveList(User account) {
        System.out.println(account.getGiveList());
    }

    /**
     * @param account the users account
     */
    public void viewLentList(User account) {
        System.out.println(account.getLentList());
    }

    /**
     * @param account the users account
     */
    public void viewBorrowedList(User account) {
        System.out.println(account.getBorrowedList());
    }
}