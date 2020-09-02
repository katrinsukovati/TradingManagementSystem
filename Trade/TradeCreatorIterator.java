package Trade;/*
Iterator for Trade.Trade.TradeController.getTimeLocationType()
@layer: presenter
@author: Carrie
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TradeCreatorIterator implements Iterator<String> {
    private final List<String> properties = new ArrayList<>();
    private int current = 0;

    /**
     * Constructor
     */
    public TradeCreatorIterator(){
        properties.add("Please input meeting location");
        properties.add("Please input meeting time in the exact format: yyyy/mm/dd hh:mm");
        properties.add("Please input type of trade: permanent or temporary");
    }

    /**
     * getter of the current index
     * @return current
     */
    public int getCurrent(){
        return current;
    }

    @Override
    public boolean hasNext() {
        return current < properties.size();
    }

    @Override
    public String next() {
        String res;
        try {
            res = properties.get(current);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        current += 1;
        return res;
    }

    /**
     * Removes the prompt just returned. Unsupported.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }
}