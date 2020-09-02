package Admin_Controls;
/* Author: Jeevika
* Description: The presenter class for editing a users info - found in admin menu.
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EditUserPresenter implements Iterator<String> {
    private List<String> editUser = new ArrayList<>();
    private int current = 0;

    public EditUserPresenter(){
        editUser.add("Enter the username of the user you want to access/edit or enter 0 to return to menu:");
    }

    public void EditUserPresenterSub () {
        editUser.add("Select what you want to do or access: ");
    }

    /**
     * Checks if there are more prompts that need to be printed.
     * @return true is there are more prompts to be printed.
     */
    @Override
    public boolean hasNext() {
        return current < editUser.size();
    }

    /**
     * Resets all values so that the prompts will start from the beginning.
     */
    public void EditUserReset(){
        current = 0;
        editUser = new ArrayList<>();
    }

    /**
     * If there are more prompts to be printed, this method will return that String.
     * @return The next prompt that needs to be printed
     */
    @Override
    public String next() {
        String nextPrompt;
        nextPrompt = editUser.get(current);
        current += 1;
        return nextPrompt;
    }
}