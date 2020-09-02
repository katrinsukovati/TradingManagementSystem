// Author: Allan
package Trade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TradeEditorIterator implements Iterator<String> {
    private final List<String> properties = new ArrayList<>();
    private int current = 0;

    public TradeEditorIterator() {
        properties.add("New meeting time (YYYY/MM/DD hh:mm): ");
        properties.add("New meeting location: ");
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

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void reset() {
        current = 0;
    }
}