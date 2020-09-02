package Admin_Controls;/*
 * Author: Jeevika
 * Description: This class is the presenter for changing users to admin. This option is found in the Admin menu.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The prompts that a user will see before entering their input.
 */
public class UserToAdminPresenter implements Iterator<String>{
    private List<String> userToAdmin = new ArrayList<>();
    private int current = 0;

    public UserToAdminPresenter(){
        userToAdmin.add("Enter the username of the user that needs to be changed to Admin or enter 0 to return to menu: ");
    }

    /**
     * Checks if there are more prompts that need to be printed.
     * @return true is there are more prompts to be printed.
     */
    @Override
    public boolean hasNext() {
        return current < userToAdmin.size();
    }

    /**
     * Resets all values so that the prompts will start from the beginning.
     */
    public void UserToAdminReset(){
        current = 0;
        userToAdmin = new ArrayList<>();
    }

    /**
     * If there are more prompts to be printed, this method will return that String.
     * @return The next prompt that needs to be printed
     */
    @Override
    public String next() {
        String nextPrompt;
        nextPrompt = userToAdmin.get(current);
        current += 1;
        return nextPrompt;
    }
}