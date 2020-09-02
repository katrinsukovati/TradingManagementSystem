package Account;
/*
 *Author: David
 * Description: Account.Account superclass that Account.User and Account.Admin.Account.Admin will inherit from.
 */
import java.io.Serializable;

public abstract class Account implements Serializable {
    private String username;
    private String password;
    private String name;

    /**
     * @param username username for this account
     * @param password password for this account
     * @param name the name of the owner of the account
     */
    public Account(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }

    //getters
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {this.password = password;}
}