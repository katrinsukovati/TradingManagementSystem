package Admin_Controls;
/* Author: Jeevika
* Description: The controller class for editing a user's account - found in admin menu.
*/

import Account.MenuPresenter;
import Account.User;
import User_Controls.UserSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class EditUserController {

    /**
     * This is the first method that should be ran.
     */
    public void run(){
        Scanner myObj = new Scanner(System.in);
        EditUserPresenter prompts = new EditUserPresenter();
        ArrayList<String> temp;
        MenuPresenter menu = new MenuPresenter();
        UserSystem userSystem = new UserSystem();
        ArrayList<User> users = userSystem.getUsersList();
        ViewListsController viewListsController = new ViewListsController();
        User user1 = null;


        String input = "";

        while (!input.equals("0")) {

            temp = new ArrayList<>();
            prompts.EditUserReset();

            prompts = new EditUserPresenter();
            //prints all prompts
            while (prompts.hasNext()) {
                System.out.println(prompts.next());
                input = myObj.nextLine();
                temp.add(input);
            }

            if (!input.equals("0")) {
                //checks if username exists
                if (userSystem.usernameTaken(input)) {
                    for (User user : users) {
                        if (user.getUsername().equals(input)) {
                            user1 = user;

                        }
                    }

                    while (!input.equals("0")) {
                        //lets user enter username of the user they wish to acess
                        prompts.EditUserReset();
                        prompts.EditUserPresenterSub();
                        menu.adminAccessUserMenu();
                        System.out.println(menu.printMenu());
                        while (prompts.hasNext()) {
                            System.out.println(prompts.next());
                            input = myObj.nextLine();
                            temp.add(input);
                        }

                        // to deactivate or reactivate user account
                        if (input.equals("1")) {
                            while (!input.equals("0")){
                                //checks if user is currently deactivated; if they are then admin is asked if they want to reactivate
                                if (user1.getDeactivateStatus()){
                                    System.out.println("This account is currently deactivated. Would you like to reactivate it? Enter 0 for NO and 1 for YES.");
                                    input = myObj.nextLine();
                                    if (input.equals("1")){
                                        user1.setDeactivateStatus(false);
                                        System.out.println("This account has been reactivated.");
                                        input = "0";
                                    } else if (!input.equals("0")){
                                        do {
                                            System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                                            input = myObj.nextLine();
                                        } while (!input.equals("1") && !input.equals("0"));
                                    }
                                } else {
                                    //if user is active, they have option to deactivate or not
                                    System.out.println("This account is currently active. Would you like to deactivate it? Enter 0 for NO and 1 for YES.");
                                    input = myObj.nextLine();
                                    if (input.equals("1")){
                                        user1.setDeactivateStatus(true);
                                        System.out.println("This account has been deactivated.");
                                        input = "0";
                                    } else if (!input.equals("0")){
                                        do {
                                            System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                                            input = myObj.nextLine();
                                        } while (!input.equals("1") && !input.equals("0"));
                                    }
                                }
                            }
                            input = "";

                        } // to change user's name
                        else if (input.equals("2")) {
                            while (!input.equals("0")) {
                                System.out.println("This user's name is currently: " + user1.getName());
                                System.out.println("Please enter the new name for this user or enter 0 to return to menu:");
                                input = myObj.nextLine();
                                boolean successCreate = true;
                                //checks if new name is valid
                                if (!(input.length() > 0)) {
                                    successCreate = false;
                                }
                                // if name is valid then it is changed
                                if (successCreate && !input.equals("0")) {
                                    user1.setName(input);
                                    System.out.println("The name has successfully been changed.");
                                    input = "0";
                                }
                                //if they enter 0, nothing happens and they return to menu
                                else if (input.equals("0")) {
                                }
                                //if name is not valid, admin is asked to try again or return to meny
                                else {
                                    System.out.println("This is not a valid name. A name cannot be blank. Enter 0 to return to menu or 1 to try again.");
                                    input = myObj.nextLine();
                                    while (!input.equals("1") && !input.equals("0")) {
                                        System.out.println("Invalid Input. Enter 0 to return to menu or 1 to try again.");
                                        input = myObj.nextLine();
                                    }

                                }
                            }
                            input = "";
                        }
                        // to reset user's password
                        else if (input.equals("3")) {
                            while (!input.equals("0")) {
                                System.out.println("Please enter the new password requested by the user or enter 0 to return to menu.");
                                input = myObj.nextLine();
                                boolean successCreate = true;
                                //checks if password is valid
                                if(!(input.length() > 8 && input.matches("[a-zA-Z0-9]*")) ) {
                                    successCreate = false;
                                }
                                // if password is valid, then it will be changed
                                if (successCreate && !input.equals("0")) {
                                    user1.setPassword(input);
                                    System.out.println("The password has successfully been changed.");
                                    input = "0";
                                }
                                // return to menu
                                else if(input.equals("0")){
                                }
                                //not valid password
                                else {
                                    System.out.println("This is not a valid password. Passwords must be longer than 8 characters and only contain letters and numbers. Enter 0 to return to menu or 1 to try again.");
                                    input = myObj.nextLine();
                                    while (!input.equals("1") && !input.equals("0")) {
                                        System.out.println("Invalid Input. Enter 0 to return to menu or 1 to try again.");
                                        input = myObj.nextLine();
                                    }
                                }
                            }
                            input = "";

                        }
                        //admin can view the user's lists(wish list, give list, lent list, etc)
                        else if (input.equals("4")) {
                            viewListsController.run(user1);
                            input = "";
                        } else if(input.equals("5")){
                            //undo
                            System.out.println("Enter 1 to confirm undo or enter 0 to return to menu.");
                            input = myObj.nextLine();
                            if (input.equals("1")) {
                                ActionManager actionManager = new ActionManager(user1.getUsername());
                                boolean result = actionManager.undo(user1);
                                if (result){
                                    System.out.println("Undo for user: " + user1.getName() + " is successful.");
                                } else {
                                    System.out.println("Oops! " + user1.getName() + " is not qualified for undo.");
                                }

                            }
                            input = "";
                        }
                        //return to menu
                        else if (input.equals("6")) {
                            input = "0";
                        } else {
                            //invalid inputs
                            do {
                                System.out.println("Invalid Input. Press 0 to return to menu or 1 to try again.");
                                input = myObj.nextLine();
                            } while (!input.equals("1") && !input.equals("0"));
                        }
                    }
                } else {
                    //username does not exist
                    System.out.println("This username does not exist. Enter 0 to return to menu or 1 to try again.");
                    input = myObj.nextLine();

                    while (!input.equals("1") && !input.equals("0")) {
                        System.out.println("Invalid Input. Enter 0 to return to menu or 1 to try again.");
                        input = myObj.nextLine();
                    }

                }
            }
        }
    }
}
