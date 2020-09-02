package Admin_Controls;/*
 * Author: Jeevika
 * Description: The presenter class for an admin changing the frozen status of a user.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FrozenStatusAdminPresenter implements Iterator<String> {
    private List<String> frozenStatus = new ArrayList<>();
    private int current = 0;

    /**
     * The prompts that a user will see before entering their input.
     */
    public FrozenStatusAdminPresenter() {
        frozenStatus.add("Enter the number of the corresponding unfreeze request you want to view or enter -1 to freeze an account.\nEnter 0 to return to menu:  ");
    }

    /**
     * Checks if there are more prompts that need to be printed.
     * @return true is there are more prompts to be printed.
     */
    @Override
    public boolean hasNext() {
        return current < frozenStatus.size();
    }

    /**
     * Resets all values so that the prompts will start from the beginning.
     */
    public void FrozenStatusReset() {
        current = 0;
        frozenStatus = new ArrayList<>();
    }

    /**
     * If there are more prompts to be printed, this method will return that String.
     * @return The next prompt that needs to be printed
     */
    @Override
    public String next() {
        String nextPrompt;
        nextPrompt = frozenStatus.get(current);
        current += 1;
        return nextPrompt;
    }
}