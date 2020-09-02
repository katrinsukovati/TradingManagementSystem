package Trade;/*
author: allan
description: stores transaction of trade after trade has been confirmed
 */

import java.io.Serializable;

public class Transaction implements Serializable {

    private final String time;
    private final String location;
    private final boolean[] status;
    private boolean finalStatus;

    /**
     * @param time time of transaction
     * @param location location of transaction
     * @param participants number of participants of transaction
     */
    public Transaction(String time, String location, int participants) {
        this.time = time;
        this.location = location;
        this.status = new boolean[participants];
        this.finalStatus = false;
    }

    /**
     * Setter for FinalStatus of transaction
     * @param b status of transaction
     */
    public void setFinalStatus(boolean b){
        this.finalStatus = b;
    }


    /**
     * Getter for FinalStatus of transaction
     * @return the final status of the transaction
     */
    public boolean getFinalStatus(){
        return finalStatus;
    }

    /**
     * @return getter for time
     */
    public String getTime() {
        return time;
    }

    /**
     * @return getter for location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return getter for status
     */
    public boolean[] getStatus() {
        return status;
    }

    /**
     * @param userIndex the index of the user is reflected by the user's index in usersInvolved of the original Trade
     * @param status the new status to be set
     */
    public void setStatus(int userIndex, boolean status) {
        this.status[userIndex] = status;
    }
}