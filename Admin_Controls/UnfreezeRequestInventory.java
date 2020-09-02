package Admin_Controls;/*
 * @author: Carrie
 * @layer: entity
 * @collaborator: Account.Admin.Account.Admin.admin.UnfreezeRequest
 * Stores all unfreeze requests that have not been reviewed by admin.
 */

import Account.User;

import java.io.Serializable;
import java.util.ArrayList;

public class UnfreezeRequestInventory implements Serializable {
    private static final ArrayList<UnfreezeRequest> unfreezeRequests = new ArrayList<>();


    /**
     * Gets all the unfreeze requests
     */
    public static ArrayList<UnfreezeRequest> getUnfreezeRequests() {
        return unfreezeRequests;
    }

    /**
     * Gets the unfreeze requests from a designated Account.User
     * @param user: the unfreeze request source
     * @return a list of the unfreeze requests made by a certain user
     */
    public static ArrayList<UnfreezeRequest> getSpecificUnfreezeRequest(User user) {
        ArrayList<UnfreezeRequest> returnList = new ArrayList<>();
        for (UnfreezeRequest r : unfreezeRequests) {
            if (r.getUser() == user){
                returnList.add(r);
            }
        }
        return returnList;
    }

    /**
     * Adds a new Account.Admin.Account.Admin.admin.UnfreezeRequest to unfreezeRequests
     * @param user: user that requests to unfreeze account. They can only request for themselves
     * @param description: description of why they file the request
     */
    public void addUnfreezeRequest(User user, String description){
        // confirms the user is frozen
        if (user.getFrozenStatus()){
            UnfreezeRequest r = new UnfreezeRequest(user, description);
            unfreezeRequests.add(r);
        }
    }

    /**
     * Admins can remove reviewed requests
     * @param user: remove the request by that user
     * @return true if successful, false if unsuccessful
     */
    public boolean removeUnFreezeRequest(User user){
        int numRemoved = 0;
        for (int i = 0; i < unfreezeRequests.size(); i++){
            if (unfreezeRequests.get(i).getUser() == user){
                unfreezeRequests.remove(unfreezeRequests.get(i));
                numRemoved++;
            }
        }
        return numRemoved != 0;
    }
}