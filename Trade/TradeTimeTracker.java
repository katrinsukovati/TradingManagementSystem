// Author: Allan
package Trade;

import Account.User;
import User_Controls.UserSystem;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TradeTimeTracker implements Serializable {

    private static Date currWeek;

    /**
     * Checks if currWeek needs to be refeshed - used when new week starts
     */
    public void checkForRefresh(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            if (currWeek == null) {
                currWeek = format.parse("2020/08/13 00:00");
            }
            LocalDateTime dt = LocalDateTime.now();
            TradeTime TT = new TradeTime(Date.from(dt.atZone(ZoneId.systemDefault()).toInstant()));
            if (currWeek.compareTo(TT.getThisWeek()) < 1){
                refreshAll();
                currWeek = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for current week for trade
     * @return current week
     */
    public Date getCurrWeek(){
        return currWeek;
    }

    /**
     * setter for current week of trade
     * @param date current week
     */
    public void setCurrWeek(Date date){
        currWeek = date;
    }

    /**
     * Resets the number of trades a user has done this week back to 0
     */
    private void refreshAll(){
        UserSystem US = new UserSystem();
        for (User u: US.getUsersList()){
            u.setNumTradeThisWeek(0);
        }
    }
}