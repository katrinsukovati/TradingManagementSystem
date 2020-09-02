package Trade;/*
Author: Allan
Description: this class edits instances of Trade
The timeEditor and locationEditor methods does not do automatically add one edit to the user which edited it, that will
be done in the controller ************
 */

import Account.User;

public class TradeEditor {

    /**
     * @param trade trade instance to be edited
     * @param time the new time of trade
     * @param user the user making the edit
     */
    public void timeEditor(Trade trade, String time, User user) {
        if (this.hasEdits(trade, user)) {
            trade.setTime(time);
        }
    }

    /**
     * @param trade the trade to be edited
     * @param location the new location of trade
     * @param user the user making the edit
     */
    public void locationEditor(Trade trade, String location, User user) {
        if (this.hasEdits(trade, user)) {
            trade.setTime(location);
        }
    }

    /**
     * @param trade the trade to be edited
     * @param user the user which made the edit
     * @return true iff the user has made less than 3 edits
     */
    public boolean hasEdits(Trade trade, User user) {
        return trade.getEditsDone()[this.getIndexFromUser(user, trade)] < 3;
    }

    /**
     * @param trade the trade to be edited
     * @param user the user which is making the edit
     * @param status the new status of user
     */
    public void changeCurrentStatus(Trade trade, User user, Boolean status) {
        trade.setCurrentStatus(this.getIndexFromUser(user, trade), status);
    }

    /**
     * @param trade the trade to be edited.
     * @return true iff all users agrees to the current conditions of the trade
     */
    public boolean allUsersStatus(Trade trade) {
        boolean[] status = trade.getCurrentStatus();
        for (int i = 0; i < status.length; i++) {
            if (!status[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Changes status of trade to false if involved user doesn't match the mentioned user
     * @param trade the specified trade
     * @param user the user
     */
    public void changeStatusAllElse(Trade trade,  User user) {
        for (int i = 0; i < trade.getUsersInvolved().length; i++) {
            if (!trade.getUsersInvolved()[i].getUsername().equals(user.getUsername()) ||
                    !trade.getUsersInvolved()[i].getPassword().equals(user.getPassword())) {
                trade.setCurrentStatus(i, false);
            }
        }
    }

    /**
     * @param trade the trade to be edited
     * @param user the user which made the edit
     */
    public void addEdit(Trade trade, User user) {
        trade.setEditsDone(this.getIndexFromUser(user, trade));
    }

    /**
     * Gets the index of the user
     * @param user the user involved in the trade
     * @param trade specified trade
     * @return
     */
    private int getIndexFromUser(User user, Trade trade) {
        User[] users = trade.getUsersInvolved();
        int index = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] == user) {
                index = i;
            }
        }
        return index;
    }
}