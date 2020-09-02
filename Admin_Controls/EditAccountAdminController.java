package Admin_Controls;/*
 * Author: Shruti
 * Description: This is the class that controls option 4 of the admin menu.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import Account.Admin;
import Account.MenuPresenter;
import User_Controls.*;

public class EditAccountAdminController {

    private final UserSystem allUsers = new UserSystem();
    private final MenuPresenter menu = new MenuPresenter();
    private final AdminManager adminManager = new AdminManager();
    private final Scanner input = new Scanner(System.in);

    /**
     * This is the first method that should be run
     * @param account the users account
     */
    public void run(Admin account) {

        menu.editAccountMenu();
        System.out.println(menu.printMenu());
        String inputNum = input.nextLine();

        switch (inputNum) {
            //Option 1: edit account name
            case "1":
                editName(account);
                break;
            //Option 2: edit account username
            case "2":
                editUsername(account);
                break;
            //Option 2: edit account password
            case "3":
                editPassword(account);
                break;
            //Option 3: print out all account information
            case "4":
                printInfo(account);
                break;
            //Option 5: return to menu
            case "5":
                System.out.println("Redirecting to the previous menu...");
                menu.adminMenu();
                break;
            // Wrong input (anything except 1-5)
            default:
                System.out.println("Invalid input! Please try again");
                run(account);
                break;
        }
    }

    public void editUsername(Admin account) {
        System.out.println("Please enter your new username: ");
        String input;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
            //Checks if username exists
            if (!allUsers.usernameTaken(input)){
                adminManager.updateUsername(account,input);
                System.out.println("Your username has been successfully changed!");
                run(account);
            }
            else{
                System.out.println("Sorry this username is already taken! Enter 1 to try again or 0 to return.");
                input = br.readLine();
                while(!input.equals("0") && !input.equals("1")) {
                    System.out.println("Invalid Input. Press 1 to try again or 0 to return.");
                    input = br.readLine();
                }
                // return
                if(input.equals("0")){
                    run(account);
                }
                // try again
                if(input.equals("1")){
                    editUsername(account);
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid value.");
        }
    }

    /**
     * Edits the user's name
     * @param account the users account
     */
    public void editName(Admin account) {
        System.out.println("Please enter your new name: ");
        String input;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
            adminManager.updateName(account,input);
            System.out.println("Your name has been successfully changed!");
            run(account);
        } catch (Exception e) {
            System.out.println("Please enter a valid value.");
        }
    }

    /**
     * Edits the user's password
     * @param account the users account
     */
    public void editPassword(Admin account) {
        System.out.println("Please enter your new password which must be greater than 8 characters and it must contain only letters and numbers: ");
        String input;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
            //Checks if password is valid (longer than 8 characters and has only letters and numbers)
            if(input.length() > 8 && input.matches("[a-zA-Z0-9]*")){
                adminManager.updatePassword(account, input);
                System.out.println("Your password has been successfully changed!");
                run(account);
            }
            else{
                System.out.println("The password you entered is invalid.");
                editPassword(account);
            }

        } catch (Exception e) {
            System.out.println("Please enter a valid value.");
        }
    }

    /**
     * Prints the account information
     * @param account the users account
     */
    public void printInfo(Admin account){

        System.out.println("Username: "+ account.getUsername() + "\nName: "+ account.getName() + "\nPassword: " + account.getPassword());
        System.out.println("Please enter 0 to go back to the previous menu or any other number to view your information again. ");
        String input;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
            if(input.equals("0")){
                run(account);
            }
            else{
                printInfo(account);
            }

        } catch (Exception e) {
            System.out.println("Please enter a valid value.");
        }
    }
}