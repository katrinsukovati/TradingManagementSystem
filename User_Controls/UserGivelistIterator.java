package User_Controls;
/*
Iterator for Trade.TradeController.create() to display user givelist
@layer: presenter
@author: Carrie
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import Account.User;
import Item.*;

public class UserGivelistIterator implements Iterator<String> {
    private List<String> userGiveList = new ArrayList<>();
    private int current = 0;

    /**
     * Constructor
     * @param user: the user of the givelist
     */
    public UserGivelistIterator(User user){
        for (Item item : user.getGiveList()){
            userGiveList.add(item.getItemName());
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
        return current < userGiveList.size();
    }

    //reset the users give list
    public void UserGiveListIteratorReset(){
        current = 0;
        userGiveList = new ArrayList<>();
    }

    @Override
    public String next() {
        String res;
        try {
            res = userGiveList.get(current);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        current += 1;
        return res;
    }
}