package Admin_Controls;/*
* @author: Carrie
* @layer: entity
* @collaborator: UnfreezeRequestInventory
* Contains features of unfreeze requests.
 */

import Account.User;

import java.io.Serializable;
import java.util.ArrayList;

public class UnfreezeRequest implements Serializable {
    private final User user; // only when userAccount.frozenStatus == true can they apply for this
    private String description; // description of the reason to unfreeze account

    /**
     * Constructor
     */
    public UnfreezeRequest(User user, String description){
        this.user = user;
        this.description = description;
    }

    /**
     * getter for username
     * @return Account.username
     */
    public User getUser() {
        return user;
    }

    /**
     * getter for description
     * @return description of why they file the request
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for description
     */
    public void setDescription(String newDescription){
        description = newDescription;
    }

    /**
     * toString
     * @return A String of the unfreeze request user and description
     */
    public String toString(UnfreezeRequest r){
        return "Unfreeze request by " + user + ":" + description;
    }

    /**
     * Adds a new admin.UnfreezeRequest
     * @param user: user that requests to unfreeze account. They can only request for themselves
     * @param description: description of why they file the request
     */
    public void addUnfreezeRequest(User user, String description){
        // confirms the user is frozen
        if (user.getFrozenStatus()){
            UnfreezeRequest r = new UnfreezeRequest(user, description);
        }
    }

    /**
     * Deletes a admin.UnfreezeRequest
     * @param user: user that requests to delete unfreezeRequest
     * @return true if successful, false if unsuccessful
     */
    public boolean deleteUnfreezeRequest(User user){
        ArrayList<UnfreezeRequest> userUnfreezeRequest =
                UnfreezeRequestInventory.getSpecificUnfreezeRequest(user);
        if (userUnfreezeRequest.size() != 0){
            for (UnfreezeRequest r: userUnfreezeRequest){
                userUnfreezeRequest.remove(r);
            }
            return true;
        } else {
            return false;
        }
    }
}