package Account;
/*
 * Author: David
 * Description: This class defines what a DemoUser account is.
 */

public class DemoUser extends User {
    /**
     * @param username username for this account
     * @param password password for this account
     * @param name     the name of the owner of this account
     */
    public DemoUser(String username, String password, String name) {
        super(username, password, name);
    }
}
