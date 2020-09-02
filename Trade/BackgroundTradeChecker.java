package Trade;

import Account.User;
import User_Controls.UserSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class BackgroundTradeChecker {

    private Date now;

    public BackgroundTradeChecker(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime dt = LocalDateTime.now();
        this.now = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("current time is: " + dtf.format(dt));
    }

    /**
     * Checks whether trade is permanent or permanent and checks trade accordingly
     */
    public void checkAllTrades(){
        UserSystem US = new UserSystem();
        for (User u: US.getUsersList()){
            for (Trade t: u.getCurrentTrades()){
                if (t.getTransactions().size() > 0){
                    if (t.getTypeOfTrade().equals("permanent")){
                        tradeChecker(t.getTransactions().get(0), t);
                    } else if (t.getTypeOfTrade().equals("temporary") && t.getTransactions().size() == 2){
                        tradeChecker(t.getTransactions().get(1), t);
                        returnDeadlineChecker(t.getTransactions().get(1), t);
                    }
                }
            }
        }
    }

    /**
     * Checks if all users agree on a trade
     * @param b an array of booleans - true if a user confirms a trade
     * @return true if all users agree and false if not
     */
    private boolean allAgree(boolean [] b){
        boolean hi = true;
        for (boolean v: b){
            if (!v) {
                hi = false;
                break;
            }
        }
        return hi;
    }

    /**
     * Checks deadline for the transaction being made. Also checks if deadline is passed.
     * @param transac the transaction made when trade is confirmed
     * @param trade the trade between two users
     */
    private void returnDeadlineChecker(Transaction transac, Trade trade){
        if (!allAgree(transac.getStatus())){
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date date1;
            try {
                date1 = format.parse(trade.getTransactions().get(0).getTime());
                Calendar c = Calendar.getInstance();
                c.setTime(date1);
                c.add(Calendar.MONTH, 1);
                Date deadline = c.getTime();
                if (now.compareTo(deadline) > 0){
                    for (User u: trade.getUsersInvolved()) {
                        u.getCurrentTrades().remove(trade);
                        u.setNumIncompleteTrades(u.getNumIncompleteTrades() + 1);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if trade is good and deadline has not passed.
     * @param transac the transaction made when trade is confirmed
     * @param trade the trade between two users
     */
    private void tradeChecker(Transaction transac, Trade trade){
        if (!transac.getFinalStatus()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            try {
                Date date1 = format.parse(transac.getTime());
                Calendar c = Calendar.getInstance();
                c.setTime(date1);
                c.add(Calendar.HOUR, 24);
                Date deadline = c.getTime();
                if (now.compareTo(deadline) > 0){
                    for (User u: trade.getUsersInvolved()) {
                        u.getCurrentTrades().remove(trade);
                        u.setNumIncompleteTrades(u.getNumIncompleteTrades() + 1);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}