package Account;/*
 * Author: David
 * Description: This class decides which menu to bring up depending on what is selected and inputted as well as the type
 * of Account.
 */
import Item.ItemRegistrationController;
import Trade.TradeController;
import Admin_Controls.*;
import User_Controls.DeactivateController;
import User_Controls.EditAccountController;

import java.util.Scanner;

public class MenuController {
    private final MenuPresenter menu = new MenuPresenter();
    Scanner input = new Scanner(System.in);

    //Required for User Menu
    private EditAccountController editUserAccount = new EditAccountController();
    private AccountListsController accController = new AccountListsController();
    private UnfreezeRequestController unfreezeController = new UnfreezeRequestController();
    private BrowseItemsController browsing = new BrowseItemsController();
    private TradeController tradeCon = new TradeController();
    private ItemRegistrationController createItem = new ItemRegistrationController();
    private DeactivateController deactivateAccount = new DeactivateController();

    //Required for Admin Menu
    private FrozenStatusAdmin FrozenStatus = new FrozenStatusAdmin();
    private UserToAdmin userToAdmin = new UserToAdmin();
    private ConfirmItem confirmItem = new ConfirmItem();
    private EditAccountAdminController editAdminAccount = new EditAccountAdminController();
    private LimitPerWeekAdmin limitPerWeekAdmin = new LimitPerWeekAdmin();
    private EditIncompleteTrades editIncompleteTrades = new EditIncompleteTrades();
    private EditNoLentItems editNoLentItems = new EditNoLentItems();
    private EditUserController editUserController = new EditUserController();

    //User Main Menu
    public void run(User currentUser){
        menu.userMenu();
        System.out.println(menu.printMenu());
        String inputNum = input.nextLine();

        //Display the menu
        while(!inputNum.equals("8")){
            switch (inputNum) {
                case "1":
                    //Edit Account Details
                    editUserAccount.run(currentUser);
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "2":
                    //View Account Item Lists
                    accController.run(currentUser);
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "3":
                    //Browse Items
                    browsing.run(currentUser);
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "4":
                    //Trade
                    if(!(currentUser instanceof DemoUser)){
                        tradeCon.run(currentUser);
                    }else{
                        System.out.println("This option is only available to general users. " +
                                "Please make a general user account to access trading.");
                    }
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "5":
                    //Register Item
                    if(!(currentUser instanceof DemoUser)){
                        createItem.run(currentUser);
                    }else{
                        System.out.println("This option is only available to general users. " +
                                "Please make a general user account to register new items for your account.");
                    }
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "6":
                    //Request an unfreeze
                    if(!(currentUser instanceof DemoUser)){
                        unfreezeController.run(currentUser);
                    }else{
                        System.out.println("This option is only available to general users. " +
                                "Please make a general user account to access this function.");
                    }
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "7":
                    //Deactivate or Reactivate account
                    deactivateAccount.run(currentUser);
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                default:
                    //Non 1-8 input
                    System.out.println("Invalid Input. Please try again.");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
            }
        }
    }

    //Admin Main Menu
    public void run(Admin currentAdmin){
        menu.adminMenu();
        System.out.println(menu.printMenu());
        String inputNum = input.nextLine();

        while(!inputNum.equals("9")){
            switch (inputNum) {
                case "1":
                    FrozenStatus.run();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "2":
                    userToAdmin.run();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "3":
                    confirmItem.run();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "4":
                    editAdminAccount.run(currentAdmin);
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "5":
                    limitPerWeekAdmin.run();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "6":
                    editIncompleteTrades.run();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "7":
                    editNoLentItems.run();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                case "8":
                    editUserController.run();
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;

                default:
                    System.out.println("Invalid Input. Please try again.");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
            }
        }
    }
}