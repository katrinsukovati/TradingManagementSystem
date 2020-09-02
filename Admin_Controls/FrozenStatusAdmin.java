package Admin_Controls;/*
 * Author: Jeevika
 * Description: The controller class for an admin changing the frozen status of a user.
 */

import java.util.*;
import java.util.Scanner;
import Account.*;
import User_Controls.*;

public class FrozenStatusAdmin {

    /**
     * This is the first method that should be ran.
     */
    public void run() {
        Scanner myObj = new Scanner(System.in);
        FrozenStatusAdminPresenter prompts = new FrozenStatusAdminPresenter();
        UserSystem userSystem = new UserSystem();
        UnfreezeRequestInventory unfreezeRequest = new UnfreezeRequestInventory();
        ArrayList<User> users = userSystem.getUsersList();
        AdminManager admin = new AdminManager();
        User user1 = null;


        String input = "";
        int secondInput = 0;

        while (!input.equals("0")) {

            prompts.FrozenStatusReset();

            prompts = new FrozenStatusAdminPresenter();
            ArrayList<UnfreezeRequest> unfreezeRequests = UnfreezeRequestInventory.getUnfreezeRequests();
            // Checks if unfreeze requests list is empty
            if (unfreezeRequests.isEmpty()){
                System.out.println("There are no unfreeze requests. Please enter 0 to return to menu or enter -1 to freeze an account:");
                input = myObj.nextLine();

                if (input.equals("-1")) {
                    ArrayList<User> incompleteTrades = admin.incompleteTrades();
                    if (incompleteTrades.isEmpty()){
                        System.out.println("There are no users that have too many incomplete trades. You can still enter a username to freeze an account or enter 0 to return to menu: ");
                    } else {
                        System.out.println("This is a list of usernames of users with too many incomplete trades:");

                        for (int i=0;  i < incompleteTrades.size() ; i++) {
                            System.out.println((i+1) + ". " + incompleteTrades.get(i).getUsername());
                        }
                        System.out.println("Enter the username of the account you want to freeze or enter 0 to return to menu:");
                    }
                    input = myObj.nextLine();
                    if (input.equals("0")){

                    } else if (userSystem.usernameTaken(input)) {
                        //If username exists then it will be frozen
                        for (User user : users) {
                            if (user.getUsername().equals(input)) {
                                user1 = user;
                            }
                        }
                        admin.accountFrozenStatus(user1, true);
                        if (incompleteTrades.contains(user1)){
                            user1.setNumIncompleteTrades(0);
                        }

                        System.out.println("The user's account status is now Frozen ");
                        input = "0";

                    } else {
                        // If the username doesn't exist
                        System.out.println("This username does not exist. Enter 0 to return to menu or 1 to try again.");
                        input = myObj.nextLine();

                        while (!input.equals("1") && !input.equals("0")) {
                            System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                            input = myObj.nextLine();
                        }

                    }
                } else if (!input.equals("0")){
                    do {
                        System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                        input = myObj.nextLine();
                    } while (!input.equals("1") && !input.equals("0"));
                }

            } else {
                //prints all users with unfreeze requests
                System.out.println("This is a list of users who have unfreeze requests: ");
                for (int i = 1; i < unfreezeRequests.size() + 1; i++) {
                    String user = unfreezeRequests.get(i-1).getUser().getName();
                    System.out.println(i + ". " + unfreezeRequests.get(i-1).getUser().getName());
                }

                //try-catch to catch all non-integer inputs
                try {
                    while (prompts.hasNext()) {
                        System.out.println(prompts.next());
                        secondInput = Integer.parseInt(myObj.nextLine());
                    }

                } catch (Exception e) {
                    do {
                        System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                        input = myObj.nextLine();
                    } while (!input.equals("1") && !input.equals("0"));

                }
                if (secondInput == -1) {
                    ArrayList<User> incompleteTrades = admin.incompleteTrades();
                    //checks if there are any users with too many incomplete trades
                    if (incompleteTrades.isEmpty()){
                        System.out.println("There are no users that have too many incomplete trades. You can still enter a username to freeze an account or enter 0 to return to menu: ");
                    } else {
                        // if there are users with too many incomplete trades, admin can view them and have the option to freeze them
                        System.out.println("This is a list of usernames of users with too many incomplete trades:");

                        for (int i=0;  i < incompleteTrades.size() + 1; i++) {
                            System.out.println(i + ". " + incompleteTrades.get(i).getUsername());
                        }
                        System.out.println("Enter the username of the account you want to freeze or enter 0 to return to menu:");
                    }
                    input = myObj.nextLine();
                    if (input.equals("0")){

                    }
                    //checks if username exists
                    else if(userSystem.usernameTaken(input)) {
                        for (int i = 0; i < users.size(); i++) {
                            if (users.get(i).getUsername().equals(input)) {
                                user1 = users.get(i);
                            }
                        }
                        admin.accountFrozenStatus(user1, true);
                        UnfreezeRequest unfreezeRequest1 = null;
                        unfreezeRequest1.deleteUnfreezeRequest(user1);
                        System.out.println("The user's account status is now Frozen ");
                        input = "0";

                    } else {
                        //username does not exists
                        System.out.println("This username does not exist. Enter 0 to return to menu or 1 to try again.");
                        input = myObj.nextLine();

                        while (!input.equals("1") && !input.equals("0")) {
                            System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                            input = myObj.nextLine();
                        }

                    }
                } else if (secondInput > 0 && secondInput <= unfreezeRequests.size()) {
                    //checks if admin entered a number from the list
                    String username = unfreezeRequests.get(secondInput-1).getUser().getName();
                    String description = unfreezeRequests.get(secondInput-1).getDescription();
                    System.out.println("This is the unfreeze request for the selected user: ");
                    System.out.println("Username: " + username);
                    System.out.println("Description/Reason: " + description);
                    System.out.println("Do you want to unfreeze this account? Enter 0 for N0 and 1 for YES:");
                    input = myObj.nextLine();

                    if (input.equals("0")){
                        System.out.println("This account will remain frozen.");

                    } else if (!input.equals("1")){
                        do {
                            System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                            input = myObj.nextLine();
                        } while (!input.equals("1") && !input.equals("0"));
                    } else {
                        admin.accountFrozenStatus(unfreezeRequests.get(secondInput-1).getUser(), false);
                        unfreezeRequest.removeUnFreezeRequest(unfreezeRequests.get(secondInput-1).getUser());
                        System.out.println("This account is now unfrozen.");
                        input = "0";

                    }
                } else if (secondInput == 0){
                    input = "0";
                } else {
                    do {
                        System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                        input = myObj.nextLine();
                    } while (!input.equals("1") && !input.equals("0"));
                }


            }

        }

    }
}
