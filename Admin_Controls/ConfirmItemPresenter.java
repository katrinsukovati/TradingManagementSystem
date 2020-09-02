package Admin_Controls;/*
 * Author: Shruti
 * Description: This is the presenter class that manages option 3 of admin menu.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The prompts that a user will see before entering their input.
 */
public class ConfirmItemPresenter implements Iterator<String> {
    private List<String> newLimits = new ArrayList<>();
    private int current = 0;

    public ConfirmItemPresenter(){
        newLimits.add("Which item do you want to view? Enter the number of the corresponding item or enter 0 to return to menu. ");
    }

    /**
     * Checks if there are more prompts that need to be printed.
     * @return true is there are more prompts to be printed.
     */
    @Override
    public boolean hasNext() {
        return current < newLimits.size();
    }

    /**
     * resets the newLimts array so that prompts start from beginning
     */
    public void ConfirmItemReset() {
        current = 0;
        newLimits = new ArrayList<>();
    }

    /**
     * If there are more prompts to be printed, this method will return that String.
     * @return The next prompt that needs to be printed
     */
    @Override
    public String next() {
        String nextPrompt;
        nextPrompt = newLimits.get(current);
        current += 1;
        return nextPrompt;
    }
}