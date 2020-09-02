package User_Controls;
//Katrin

import java.util.Scanner;
import Account.*;

public class EditAccountController {

    private final Scanner input = new Scanner(System.in);
    private final MenuPresenter menu = new MenuPresenter();
    private final UserSystem allUsers = new UserSystem();
    private final UserManager userManager = new UserManager();

    //This function is run first.
    public void run(User account) {

        menu.editAccountMenu();
        System.out.println(menu.printMenu());
        String inputNum = input.nextLine();

        switch (inputNum) {
            // Input: 1 = Edit the name of the user
            case "1":
                changeName(account);
                break;

            // Input: 2 = Edit username
            case "2":
                changeUsername(account);
                break;

            // Input: 3 = Edit password
            case "3":
                changePassword(account);
                break;

            // Input: 4 = Print account information
            case "4":
                printInfo(account);
                break;

            // Input: 5 = Go back to previous menu
            case "5":
                System.out.println("You will now be redirected to the previous menu...");
                menu.userMenu();
                //System.out.println(menu.printMenu());
                break;

            // Invalid Input
            default:
                System.out.println("Invalid input! Please try again");
                run(account);
                break;
        }
    }

    /**
     * Changes name of specified user
     * @param account the specified user
     */
    public void changeName(User account) {
        System.out.println("Please enter your new name: ");
        String inputNum = input.nextLine();
        userManager.updateName(account, inputNum);
        System.out.println("Your name has been successfully changed to " + inputNum +
                "! You will now be redirected to the previous menu...");
        // Print out menu again
        run(account);
    }

    /**
     * Changes username of specified user
     * @param account specified account
     */
    public void changeUsername(User account){
        System.out.println("Please enter your new username: ");
        String inputNum = input.nextLine();

        // If the username is not taken, change the username
        if (!allUsers.usernameTaken(inputNum)) {
            userManager.updateUsername(account, inputNum);
            System.out.println("Your username has been successfully changed to " + inputNum +
                    "! You will now be redirected to the previous menu...");
            // Print out menu again
            run(account);
        }

        // Username is taken
        else {
            System.out.println("Sorry this username is already taken! Enter 1 to try again or 0 to return.");
            inputNum = input.nextLine();

            while(!inputNum.equals("0") && !inputNum.equals("1")){
                System.out.println("Invalid Input. Press 1 to try again or 0 to return.");
                inputNum = input.nextLine();
            }

            if(inputNum.equals("0")){
                System.out.println("You will now be redirected to the previous menu...");
                run(account);
            }

            else if(inputNum.equals("1")){
                System.out.println("Try again...");
                changeUsername(account);
            }
        }
    }

    /**
     * Changes password of the user
     * @param account the specified user account
     */
    public void changePassword(User account){
        System.out.println("Please enter your new password which must be greater than 8 characters and it must contain only letters and numbers: ");
        String inputNum = input.nextLine();

        if(inputNum.length() > 8 && inputNum.matches("[a-zA-Z0-9]*")){
            userManager.updatePassword(account, inputNum);
            System.out.println("Your password has been successfully changed to" + inputNum +
                    "! You will now be redirected to the previous menu...");
            // Print out menu again
            run(account);
        }

        else{
            System.out.println("The password you entered is invalid. Enter 1 to try again or 0 to return.");
            inputNum = input.nextLine();
            while(!inputNum.equals("0") && !inputNum.equals("1")) {
                System.out.println("Invalid Input. Press 1 to try again or 0 to return.");
                inputNum = input.nextLine();
            }

            if(inputNum.equals("0")){
                run(account);
            }

            else if(inputNum.equals("1")){
                System.out.println("Try again...");
                changePassword(account);
            }
        }
    }

    /**
     * Prints all information about the user
     * @param account the specified user
     */
    public void printInfo(User account){
        System.out.println("Username: "+ account.getUsername() + "\nName: "+ account.getName() + "\nPassword: " + account.getPassword());
        System.out.println("Please enter 0 to go back to the previous menu or 1 to view your information again.");
        String inputNum = input.nextLine();
        while(!inputNum.equals("0") && !inputNum.equals("1")) {
            System.out.println("Invalid Input. Press 1 to try again or 0 to return.");
            inputNum = input.nextLine();
        }

        if(inputNum.equals("0")){
            run(account);
        }

        else if(inputNum.equals("1")){
            printInfo(account);
        }
    }
}