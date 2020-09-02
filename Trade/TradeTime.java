// Author: Allan
package Trade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TradeTime {

    private static Date thisWeek;

    /**
     * Determines the time that the trade will happen
     * @param now the present date and time
     */
    public TradeTime(Date now){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        while (day > 2){
            dayOfMonth = dayOfMonth - 1;
            day = day - 1;
        }
        try {
            thisWeek = format.parse(year + "/" + month + "/" +
                    day + " 00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for ThisWeek
     * @return Date for this week
     */
    public Date getThisWeek(){
        return thisWeek;
    }

}