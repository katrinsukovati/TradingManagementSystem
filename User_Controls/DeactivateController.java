package User_Controls;
/* Author: Jeevika
 * Description: This class is the controller for deactivating and reactivating a user's account. This option is found in the User menu.
 */

import Account.User;

import java.util.Scanner;

public class DeactivateController {

    //this is the function that will be run
    public void run (User user){
        Scanner myObj = new Scanner(System.in);
        DeactivatePresenter prompts = new DeactivatePresenter();
        UserManager userManager = new UserManager();

        String input = "";

        while (!input.equals("0")){

            prompts.deactivateReset();

            //reactivates account
            if (user.getDeactivateStatus()){
                prompts.Reactivate();
            }

            //deactivate account
            else {
                prompts.Deactivate();
            }

            while (prompts.hasNext()) {
                System.out.println(prompts.next());
                input = myObj.nextLine();
            }

            //reactivating account
            if ((user.getDeactivateStatus()) && input.equals("1")) {
                userManager.reactivateUser(user);
                System.out.println("Your account has successfully been reactivated.");
                input = "0";
            }

            //dectivating account
            else if ((!user.getDeactivateStatus()) && input.equals("1")) {
                userManager.deactivateUser(user);
                System.out.println("Your account has successfully been deactivated.");
                input = "0";
            }

            //try again
            else {
                while(!input.equals("1") && !input.equals("0")) {
                    System.out.println("Invalid Input. Enter 0 to return to menu or 1 to try again.");
                    input = myObj.nextLine();
                }
            }
        }
    }
}