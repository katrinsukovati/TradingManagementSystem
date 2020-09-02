package Account;/*
 * Author: David
 * Description: This class is the initial login screen that is first used.
 */

import Admin_Controls.ActionManager;
import User_Controls.UserSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginController {
    private final Scanner input = new Scanner(System.in);
    private final AccountPropertiesPresenter loginInfoIterator = new AccountPropertiesPresenter();
    private final MenuPresenter menu = new MenuPresenter();
    private final UserSystem allUsers = new UserSystem();
    private final MenuController menuController = new MenuController();
    private final AccountFactory accountMaker= new AccountFactory();

    public void loginMainMenu() {
        menu.initialMenu();
        System.out.println(menu.printMenu());
        String inputNum = input.nextLine();

        //Display the menu to the user
        while (!inputNum.equals("3")) {
            // This is used to log in to an account.
            if (inputNum.equals("1")) {
                System.out.println("Please provide the following information:");
                loginInfoIterator.accountPropertiesReset();
                ArrayList<String> temp = new ArrayList<>();
                //Prints out all prompts required for logging in and stores it
                loginInfoIterator.loginProperties();

                while (loginInfoIterator.hasNext()) {
                    System.out.println(loginInfoIterator.next());
                    String inputStr = input.nextLine();
                    temp.add(inputStr);
                }

                //Checks if the account is valid
                Account currentAccount = allUsers.getUser(temp.get(0), temp.get(1));
                if (currentAccount == null){
                    currentAccount = allUsers.getAdmin(temp.get(0), temp.get(1));
                }

                if (currentAccount == null) {
                    System.out.println("Incorrect Username or password. Press 1 to try again or 0 to return.");
                    inputNum = input.nextLine();

                    while(!inputNum.equals("0") && !inputNum.equals("1")) {
                        System.out.println("Invalid Input. Press 1 to try again or 0 to return.");
                        inputNum = input.nextLine();
                    }

                    if(inputNum.equals("0")){
                        System.out.println(menu.printMenu());
                        inputNum = input.nextLine();
                    }
                //If account is valid, it checks for the account type before running the appropriate menu for user or admin
                }

                else{
                    System.out.println("You have successfully logged in!");
                    if(currentAccount instanceof Admin){
                        Admin currentAdmin = (Admin)currentAccount;
                        menuController.run(currentAdmin);
                    }

                    else {
                        User currentUser = (User)currentAccount;
                        menuController.run(currentUser);
                        String username = currentUser.getUsername();
                        ActionManager actionManager = new ActionManager(username);
                    }
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                }
                //Creating an account.
            }

            else if (inputNum.equals("2")) {
                //Asks if they want to make a general user account or a demo user account.
                String newInputNum = "";
                menu.accountTypeMenu();
                System.out.println(menu.printMenu());
                inputNum = input.nextLine();

                while(!inputNum.equals("0")){
                    boolean successCreate;
                    switch (inputNum){
                        case "1":
                            successCreate = accountMaker.makeAccount(AccountType.USER);
                            if(successCreate){
                                System.out.println("The general user account as been successfully created.");
                                inputNum = "0";
                            }

                            else{
                                System.out.println("Either the username is taken, your password is not longer than 8 characters, or " +
                                        "one of the inputs were blank. Press 0 to return to menu or 1 to retry.");
                                inputNum = input.nextLine();

                                while(!inputNum.equals("0") && !inputNum.equals("1")){
                                    System.out.println("Invalid Input. Press 1 to try again or 0 to return.");
                                    inputNum = input.nextLine();
                                }

                                if(inputNum.equals("0")){
                                    break;
                                }

                                else{
                                    inputNum = "2";
                                }
                            }
                            break;

                        case "2":
                            successCreate = accountMaker.makeAccount(AccountType.DEMO_USER);
                            if(successCreate){
                                System.out.println("The demo user account as been successfully created.");
                                inputNum = "0";
                            }

                            else{
                                System.out.println("Either the username is taken, your password is not longer than 8 characters, or " +
                                        "one of the inputs were blank. Press 0 to return to menu or 1 to retry.");
                                inputNum = input.nextLine();

                                while(!inputNum.equals("0") && !inputNum.equals("1")){
                                    System.out.println("Invalid Input. Press 1 to try again or 0 to return.");
                                    inputNum = input.nextLine();
                                }
                                if(inputNum.equals("0")){
                                    break;
                                }

                                else{
                                    inputNum = "2";
                                }
                            }
                            break;

                        default:
                            System.out.println("Invalid Input. Please try again.");
                            System.out.println(menu.printMenu());
                            inputNum = input.nextLine();
                            break;
                    }
                }
                menu.initialMenu();
                System.out.println(menu.printMenu());
                inputNum = input.nextLine();

            }

            else{
                System.out.println("Invalid input! Please try again.");
                System.out.println(menu.printMenu());
                inputNum = input.nextLine();
            }
        }
    }
}