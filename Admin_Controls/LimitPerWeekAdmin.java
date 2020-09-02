package Admin_Controls;
/*
 * Author: Jeevika
 * Description: The controller class for an admin changing the limit per week on trades.
 */

import java.util.*;
import java.util.Scanner;

import User_Controls.UserSystem;

public class LimitPerWeekAdmin {

    /**
     * This is the first method that should be ran.
     */
    public void run(){
        Scanner myObj = new Scanner(System.in);
        LimitPerWeekAdminPresenter prompts = new LimitPerWeekAdminPresenter();
        ArrayList<Integer> temp;
        UserSystem userSystem = new UserSystem();
        AdminManager admin = new AdminManager();

        String input = "1";
        int secondInput = 0;

        while (!input.equals("-1")){

            temp = new ArrayList<>();
            prompts.LimitPerWeekReset();
            prompts = new LimitPerWeekAdminPresenter();
            //try-catch to catch any non-integer inputs
            try {
                while (prompts.hasNext()) {
                    System.out.println(prompts.next());
                    secondInput = myObj.nextInt();
                }

                //if exit code isn't entered, limit per week threshold is changed
                if (secondInput != -1){
                    temp.add(secondInput);
                    admin.changeLimitPerWeek(temp.get(0), userSystem);
                    System.out.println("The limit per week has been changed!");
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