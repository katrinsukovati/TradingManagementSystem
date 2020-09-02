package Admin_Controls;
/*
 * Author: Jeevika
 */

import java.util.Scanner;
import Account.*;

public class UnfreezeRequestController {

    //new code
    private final Scanner input = new Scanner(System.in);
    private final MenuPresenter menu = new MenuPresenter();

    /**
     * This is the first method that should be run
     * @param account the users account
     */
    public void run(User account) {

        menu.unfreezeReqMenu();
        System.out.println(menu.printMenu());
        String inputNum = input.nextLine();

        // Input: 1 = Request Unfreeze Account
        if (inputNum.equals("1")) { unfreeze(account); }
        //Input: 2 = Return to previous menu
        else if (inputNum.equals("2")) {
        }
        else {
            System.out.println("Invalid input! Please try again");
            run(account);
        }
    }

    /**
     * @param account the users account
     */
    public void unfreeze(User account) {
        if(!account.getFrozenStatus()){
            System.out.println("You are not frozen and will now be redirected to the previous menu...");
            run(account);
        }
        else{
            System.out.println("Please enter a description of the reason as to why you would like to unfreeze your account: ");
            String inputNum = input.nextLine();
            UnfreezeRequest req = new UnfreezeRequest(account, inputNum);
            UnfreezeRequestInventory unfreezeRequestInventory = new UnfreezeRequestInventory();
            req.addUnfreezeRequest(account,inputNum);
            //added to unfreeze request inventory
            unfreezeRequestInventory.addUnfreezeRequest(account, inputNum);
            System.out.println("Your request has been made! You will now be redirected to the previous menu...");
            run(account);
            }
        }
}