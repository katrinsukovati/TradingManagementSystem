package Admin_Controls;/*
 * Author: Jeevika
 * Description: This class is the controller for changing users to admin. This option is found in the Admin menu.
 */

import Account.MenuPresenter;
import Account.User;
import User_Controls.UserSystem;

import java.util.*;
import java.util.Scanner;

public class UserToAdmin {

    /**
     * This is the first method that should be ran.
     */
    public void run(){
        Scanner myObj = new Scanner(System.in);
        UserToAdminPresenter prompts = new UserToAdminPresenter();
        ArrayList<String> temp;
        UserSystem userSystem = new UserSystem();
        ArrayList<User> users = userSystem.getUsersList();
        AdminManager admin = new AdminManager();
        User user1 = null;


        String input = "";


        while (!input.equals("0")) {

            temp = new ArrayList<>();
            prompts.UserToAdminReset();

            prompts = new UserToAdminPresenter();
            while (prompts.hasNext()) {
                System.out.println(prompts.next());
                input = myObj.nextLine();
                temp.add(input);
            }

            // if exit code isn't entered, it will check if username exists
            if (!input.equals("0")) {
                if (userSystem.usernameTaken(input)) {
                    for (User user : users) {
                        if (user.getUsername().equals(input)) {
                            user1 = user;
                        }
                    }
                    // user will be changed to admin
                    admin.designateAdmin(user1, userSystem);
                    System.out.println("The user has been changed to an Admin!");
                    input = "0";
                } else {
                    //username doesn't exist
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