package Trade;

import Account.User;
import Item.Item;

import java.io.Serializable;

public class oneWayTrade extends Trade implements Serializable {
    private Item[] itemsOffered;

    /**
     * constructor
     * @param location: string of meetup location for trade to take place
     * @param time: string of time for meetup
     * @param usersInvolved: array storing the two users involved
     * @param typeOfTrade: permanent or temporary
     * @param itemsOffered: the items offered in this trade
     */
    public oneWayTrade(String location, String time, User[] usersInvolved, String typeOfTrade, Item[] itemsOffered, User[][] lenderRelation) {
        super(location, time, usersInvolved, typeOfTrade, lenderRelation);
        this.itemsOffered = itemsOffered;
    }

    /**
     * getter
     * @return the items offered by the user
     */
    @Override
    public Item[] getItemsOffered() {
        return itemsOffered;
    }

    /**
     * setter
     * @param itemsOffered: new items offered
     */
    public void setItemsOffered(Item[] itemsOffered) {
        this.itemsOffered = itemsOffered;
    }
}