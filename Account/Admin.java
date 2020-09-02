package Account;/*
 * Author: David
 * Description: This class defines what an admin account is.
 */

import java.io.Serializable;

public class Admin extends Account implements Serializable {
    /**
     * @param username username for this account
     * @param password password for this account
     * @param name the name of the owner of the account
     */
    public Admin(String username, String password, String name) {
        super(username, password, name);
    }
}