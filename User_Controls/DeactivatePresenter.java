package User_Controls;
 /* Author: Jeevika
  * Description: This class is the presenter for deactivating and reactivating a user's account. This option is found in the User menu.
  */

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DeactivatePresenter implements Iterator<String> {

    private List<String> deactivate = new ArrayList<>();
    private int current = 0;

    //deactivate prompt
    public void Deactivate(){
        deactivate.add("Your account is currently active. Would you like to deactivate your account? Enter 0 to return to menu or 1 to deactivate your account.\nNOTE: Deactivating an account means other users will not see the items you are offering. Also all your requests to register an item will\nbe put on hold until your account is reactivated.");
    }

    //reactivate prompt
    public void Reactivate(){
        deactivate.add("Your account is currently deactivated. Would you like to reactivate your account? Enter 0 to return to menu or 1 to reactivate your account.");
    }

    /**
     * Checks if there are more prompts that need to be printed.
     * @return true is there are more prompts to be printed.
     */
    @Override
    public boolean hasNext() {
        return current < deactivate.size();
    }

    /**
     * Resets all values so that the prompts will start from the beginning.
     */
    public void deactivateReset(){
        current = 0;
        deactivate = new ArrayList<>();
    }

    /**
     * If there are more prompts to be printed, this method will return that String.
     * @return The next prompt that needs to be printed
     */
    @Override
    public String next() {
        String nextPrompt;
        nextPrompt = deactivate.get(current);
        current += 1;
        return nextPrompt;
    }
}