package Admin_Controls;
/*
 * Author: Jeevika
 * Description: The presenter class for an admin changing the limit per week on trades.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The prompts that a user will see before entering their input.
 */
public class LimitPerWeekAdminPresenter implements Iterator<String>{
    private List<String> newLimits = new ArrayList<>();
    private int current = 0;

    public LimitPerWeekAdminPresenter(){
        newLimits.add("Enter the new Limit per Week (Number Input) or enter -1 to return to menu: ");
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
     * Resets all values so that the prompts will start from the beginning.
     */
    public void LimitPerWeekReset() {
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