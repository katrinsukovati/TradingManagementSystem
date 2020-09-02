package User_Controls;
/*
Iterator for Trade.Trade.TradeController.create() to display user wishlist
@layer: presenter
@author: Carrie
 */

import Account.User;
import Item.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class UserWishlistIterator implements Iterator<String> {
    private final List<String> wishlist = new ArrayList<>();
    private int current = 0;

    /**
     * Constructor
     * @param user: the user of the wishlist
     */
    public UserWishlistIterator(User user){
        for (Item item : user.getWishList()){
            wishlist.add(item.getItemName());
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
        return current < wishlist.size();
    }

    @Override
    public String next() {
        String res;
        try {
            res = wishlist.get(current);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        current += 1;
        return res;
    }
}