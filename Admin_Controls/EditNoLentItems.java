package Admin_Controls;/*
 * Author: Shruti
 * Description: This is the controller class that manages option 7 of admin menu.
 */

import User_Controls.UserSystem;

import java.util.*;
import java.util.Scanner;

public class EditNoLentItems {

    /**
     * This is the first method that should be ran.
     */
    public void run(){
        Scanner myObj = new Scanner(System.in);
        EditNoLentItemsPresenter prompts = new EditNoLentItemsPresenter();
        ArrayList<Integer> temp;
        AdminManager admin = new AdminManager();

        String input = "1";
        int secondInput = 0;

        while (!input.equals("-1")){

            temp = new ArrayList<>();
            prompts.EditNoLentItemsReset();
            prompts = new EditNoLentItemsPresenter();
            //try-catch is to catch any non-integer inputs
            try {
                while (prompts.hasNext()) {
                    System.out.println(prompts.next());
                    secondInput = myObj.nextInt();
                }
                //if exit code is not entered, number of lent items threshold will be changed
                if (secondInput != -1) {
                    temp.add(secondInput);
                    admin.setTradePrerequisite(temp.get(0));
                    System.out.println("The no. of min items to be lent before the user can borrow has been changed!");
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