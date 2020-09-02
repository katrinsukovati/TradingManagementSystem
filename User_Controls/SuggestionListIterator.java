// Author: Allan
package User_Controls;

import Item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class SuggestionListIterator implements Iterator<String>{
    private List<String> itemList = new ArrayList<>();
    private int current = 0;

    public SuggestionListIterator(ArrayList<Item> list1){
        for (Item item : list1){
            itemList.add(item.getItemName());
        }
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
        return current < itemList.size();
    }

    public void suggestionListReset(){
        current = 0;
        itemList = new ArrayList<>();
    }

    @Override
    public String next() {
        String res;
        try {
            res = itemList.get(current);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        current += 1;
        return res;
    }
}