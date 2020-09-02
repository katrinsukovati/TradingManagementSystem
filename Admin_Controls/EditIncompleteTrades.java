package Admin_Controls;/*
 * Author: Shruti
 * Description: This is the controller class that manages option 6 of admin menu.
 */

import User_Controls.UserSystem;

import java.util.*;
import java.util.Scanner;

public class EditIncompleteTrades {

    /**
     * This is the first method that should be ran.
     */
    public void run(){
        Scanner myObj = new Scanner(System.in);
        EditIncompleteTradesPresenter prompts = new EditIncompleteTradesPresenter();
        ArrayList<Integer> temp;
        AdminManager admin = new AdminManager();

        String input = "1";
        int secondInput = 0;

        while (!input.equals("-1")){

            temp = new ArrayList<>();
            prompts.EditIncompleteTradesReset();
            prompts = new EditIncompleteTradesPresenter();
            //try-catch to catch any non-integer inputs
            try {
                while (prompts.hasNext()) {
                    System.out.println(prompts.next());
                    secondInput = myObj.nextInt();
                }

                //If user doesn't enter exit code -1, incomplete trade threshold will be changed
                if (secondInput != -1) {
                    temp.add(secondInput);
                    admin.setMaxIncompleteTrade(temp.get(0));
                    System.out.println("The Incomplete Trades that can be allowed has been changed!");
                }
                input = "-1";

            } catch (Exception e) {
                myObj.nextLine();
                do {
                    System.out.println("Invalid Input. Press -1 to return to menu or 1 to try again.");
                    input = myObj.nextLine();
                } while (!input.equals("1") && !input.equals("-1"));

            }

        }

    }
}