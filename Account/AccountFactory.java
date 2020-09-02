package Account;
/*
 * Author: David
 * Description: This class makes an account when the makeAccount method is called.
 */
import User_Controls.UserSystem;
import User_Controls.UserManager;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountFactory {
    private final Scanner input = new Scanner(System.in);
    private final AccountPropertiesPresenter loginInfoIterator = new AccountPropertiesPresenter();
    private final UserSystem allUsers = new UserSystem();
    private final UserManager userManager = new UserManager();

    /**
     * @param type is the type of account to be made
     * @return if te account is made
     */
    public boolean makeAccount(AccountType type){
        switch(type){
            //for a user
            case USER:
                System.out.println("Please provide the following information to make a general user account:");
                ArrayList<String> temp = new ArrayList<>();
                loginInfoIterator.createAccountProperties();
                while (loginInfoIterator.hasNext()) {
                    System.out.println(loginInfoIterator.next());
                    String inputStr = input.nextLine();
                    temp.add(inputStr);
                }

                boolean successCreate = true;
                //Username can not be a blank
                if(!(temp.get(0).trim().length() > 0)) {
                    successCreate = false;
                }

                //Password must be greater than 8 characters and contains letters and numbers only.
                if(!(temp.get(1).length() > 8 && temp.get(1).matches("[a-zA-Z0-9]*"))) {
                    successCreate = false;
                }

                //Name can not be a blank
                if(!(temp.get(2).trim().length() > 0)) {
                    successCreate = false;
                }

                //If the above checks are passed, it checks if a user as already been created and gives proper prompts.
                if(successCreate){
                    successCreate = userManager.createUser(temp.get(0), temp.get(2), temp.get(1), allUsers);
                }

                return successCreate;

            //for a demo user
            case DEMO_USER:
                System.out.println("Please provide the following information to make a demo user account:");
                temp = new ArrayList<>();
                loginInfoIterator.createAccountProperties();
                while (loginInfoIterator.hasNext()) {
                    System.out.println(loginInfoIterator.next());
                    String inputStr = input.nextLine();
                    temp.add(inputStr);
                }

                //Username can not be a blank
                successCreate = temp.get(0).trim().length() > 0;

                //Password must be greater than 8 characters and contains letters and numbers only.
                if(!(temp.get(1).length() > 8 && temp.get(1).matches("[a-zA-Z0-9]*"))) {
                    successCreate = false;
                }

                //Name can not be a blank
                if(!(temp.get(2).trim().length() > 0)) {
                    successCreate = false;
                }

                //If the above checks are passed, it checks if a user as already been created and gives proper prompts.
                if(successCreate){
                    successCreate = userManager.createDemoUser(temp.get(0), temp.get(2), temp.get(1), allUsers);
                }

                return successCreate;

            default:
                return false;
        }
    }
}