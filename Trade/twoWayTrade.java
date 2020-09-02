package Trade;

import java.io.Serializable;
import Item.*;
import Account.User;

/*
 * author: Allan
 * Description: This class extends the Trade.Trade class storing one additional variable itemsOffered.
 */

public class twoWayTrade extends Trade implements Serializable {
    private final Item[][] itemsOffered;

    /**
     * Constructor
     * @param location: string of meetup location for trade to take place
     * @param time: string of time for meetup
     * @param usersInvolved: array storing the two users involved
     * @param typeOfTrade: permanent or temporary
     * @param itemsOffered: the items offered in this trade
     */
    public twoWayTrade(String location, String time, User[] usersInvolved, String typeOfTrade, Item[][] itemsOffered, User[][] lenderRelation) {
        super(location, time, usersInvolved, typeOfTrade, lenderRelation);
        this.itemsOffered = itemsOffered;
    }

    /**
     * getter
     * @return items offered by users
     */
    @Override
    public Item[][] getItemsOffered() {
        return itemsOffered;
    }
}