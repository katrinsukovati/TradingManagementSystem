package Account;/*
 * Author: Everyone
 * Description: This class contains the many menu screens and a method to print it.
 */
import java.util.ArrayList;

public class MenuPresenter {
    private ArrayList<String> menuOptions = new ArrayList<>();
    private int current = 0;

    /**
     * Inital menu that user sees
     */
    public void initialMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Welcome to G26 Trading! Please select an option (number input only): " + '\n');
        menuOptions.add("1. Login to account" + '\n');
        menuOptions.add("2. Create account" + '\n');
        menuOptions.add("3. Exit");
    }

    /**
     * Confirm item in admin menu
     */
    public void confirmMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Select what you want to do or access (number input): " + '\n');
        menuOptions.add("1. Confirm this item" + '\n');
        menuOptions.add("2. Delete this item" + '\n');
        menuOptions.add("3. Go back to the list of items" + '\n');
    }

    /**
     * User menu
     */
    public void userMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Select what you want to do or access (number input): " + '\n');
        menuOptions.add("1. Edit Account Info" + '\n');
        menuOptions.add("2. View Account Item Lists" + '\n');
        menuOptions.add("3. Browse Items" + '\n');
        menuOptions.add("4. Trade" + '\n');
        menuOptions.add("5. Register Item" + '\n');
        menuOptions.add("6. Request Unfreeze Account" + '\n');
        menuOptions.add("7. Deactivate or Reactivate Account" + '\n');
        menuOptions.add("8. Logout");
    }

    /**
     * When user wants to edit their account info
     */
    public void editAccountMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Select what you want to do or access (number input): " + '\n');
        menuOptions.add("1. Edit your name" + '\n');
        menuOptions.add("2. Edit your username" + '\n');
        menuOptions.add("3. Edit your password" + '\n');
        menuOptions.add("4. Display your information" + '\n');
        menuOptions.add("5. Go back to the previous menu");
    }

    /**
     * When user can view all of their current lists
     */
    public void accountListMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Select what you want to do or access (number input): " + '\n');
        menuOptions.add("1. View the items that you can give to other users" + '\n');
        menuOptions.add("2. View the items that you have lent to other users" + '\n');
        menuOptions.add("3. View the items that you have borrowed from other users" + '\n');
        menuOptions.add("4. View/edit your wish list" + '\n');
        menuOptions.add("5. Go back to the previous menu");
    }

    /**
     * User can request to be unfrozen by admim
     */
    public void unfreezeReqMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Select what you want to do or access (number input): " + '\n');
        menuOptions.add("1. Request to unfreeze your account" + '\n');
        menuOptions.add("2. Go back to the previous menu");
    }

    /**
     * Admin menu
     */
    public void adminMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Select what you want to do or access (number input): " + '\n');
        menuOptions.add("1. Change the frozen status of an account" + '\n');
        menuOptions.add("2. Change user status to admin" + '\n');
        menuOptions.add("3. Confirm an item" + '\n');
        menuOptions.add("4. Edit the account information" + '\n');
        menuOptions.add("5. Change the limit per week for trading" + '\n');
        menuOptions.add("6. Change the no. of incomplete trades for freezing an account" + '\n');
        menuOptions.add("7. Change no. of items needed to be lent before borrowing" + '\n');
        menuOptions.add("8. Access and edit a user's account"+ '\n');
        menuOptions.add("9. Logout");
    }

    /**
     * If admin wants to access a user's account to view/edit
     */
    public void adminAccessUserMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("These are the following options: " + '\n');
        menuOptions.add("1. Change the Deactivate Status of an account" + '\n');
        menuOptions.add("2. Change the User's Name" + '\n');
        menuOptions.add("3. Reset the User's Password" + '\n');
        menuOptions.add("4. View the User's Lists" + '\n');
        menuOptions.add("5. Undo the User's action" + '\n');
        menuOptions.add("6. Go back to the previous menu" + '\n');
    }

    /**
     * Admin can view all user's lists
     */
    public void adminAccessUserListsMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Select what you want to do or access (number input): " + '\n');
        menuOptions.add("1. View the Users Wish List" + '\n');
        menuOptions.add("2. View the Users Give List" + '\n');
        menuOptions.add("3. View the Users Lent List" + '\n');
        menuOptions.add("4. View the Users Borrowed List" + '\n');
        menuOptions.add("5. Go back to the previous menu" + '\n');
    }

    /**
     * List of all types of items - is seen when a user registers an item
     */
    public void itemTypeMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Please select the type of item you wish to register or press 0 to return." + '\n');
        menuOptions.add("1. Beauty Products " + '\n');
        menuOptions.add("2. Camping & Outdoors " + '\n');
        menuOptions.add("3. Cleaning Products " + '\n');
        menuOptions.add("4. Clothing " + '\n');
        menuOptions.add("5. Fashion Accessories" + '\n');
        menuOptions.add("6. Construction Tools" + '\n');
        menuOptions.add("7. Furniture " + '\n');
        menuOptions.add("8. Gardening Tools " + '\n');
        menuOptions.add("9. Health " + '\n');
        menuOptions.add("10. House " + '\n');
        menuOptions.add("11. Sport Goods" + '\n');
        menuOptions.add("12. Accessories and Care for Vehicles " + '\n');
        menuOptions.add("13. Vehicles " + '\n');
        menuOptions.add("14. Electronics" + '\n');
        menuOptions.add("15. Other ");
    }

    /**
     * When user creates an account, the two type of accounts that can be created
     */
    public void accountTypeMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Please select the type of account you want to make or enter 0 to return." + '\n');
        menuOptions.add("1. General User" + '\n');
        menuOptions.add("2. Demo User");
    }

    /**
     * Options when user views their wishlist
     */
    public void wishListViewMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Please select what you want to do: " + '\n');
        menuOptions.add("1. View item details"+ '\n');
        menuOptions.add("2. Remove item from wishlist"+ '\n');
        menuOptions.add("3. Return to previous page");
    }

    /**
     * Options when user views an item in browse
     */
    public void itemActionMenu(){
        menuOptions = new ArrayList<>();
        menuOptions.add("Choose from the following options:" + '\n');
        menuOptions.add("1. Add this item to your wishlist."+ '\n');
        menuOptions.add("2. Create a trade with this item."+ '\n');
        menuOptions.add("0. Return to main menu."+ '\n');
        menuOptions.add("00. Return to the inventory.");
    }

    /**
     * Checks if there is another option to be printed in the menu
     * @return true if there is more to be printed in the menu
     */
    public boolean hasNext() {
        return current < menuOptions.size();
    }

    /**
     * Prints the menu
     * @return A StringBuilder of the specified menu
     */
    public StringBuilder printMenu(){
        StringBuilder display = new StringBuilder();
        while(hasNext()){
            display.append(menuOptions.get(current));
            current += 1;
        }
        current = 0;
        return display;

    }
}