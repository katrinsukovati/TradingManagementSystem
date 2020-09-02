package Admin_Controls;/*
 * Author: Shruti
 * Description: This is the presenter class that manages option 7 of admin menu.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The prompts that a user will see before entering their input.
 */
public class EditNoLentItemsPresenter implements Iterator<String>{
    private List<String> newNoLentItems = new ArrayList<>();
    private int current = 0;

    public EditNoLentItemsPresenter(){
        newNoLentItems.add("Enter the new no. of min items to be lent before the user can borrow or -1 to return to the menu(Number Input): ");
    }

    /**
     * Checks if there are more prompts that need to be printed.
     * @return true is there are more prompts to be printed.
     */
    @Override
    public boolean hasNext() {
        return current < newNoLentItems.size();
    }

    /**
     * Resets all values so that the prompts will start from the beginning.
     */
    public void EditNoLentItemsReset() {
        current = 0;
        newNoLentItems = new ArrayList<>();
    }

    /**
     * If there are more prompts to be printed, this method will return that String.
     * @return The next prompt that needs to be printed
     */
    @Override
    public String next() {
        String nextPrompt;
        nextPrompt = newNoLentItems.get(current);
        current += 1;
        return nextPrompt;
    }
}