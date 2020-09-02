package Admin_Controls;

import Account.User;
import Trade.TradeCreatorController;
import User_Controls.UserSystem;
import java.util.Observable;
import java.util.Observer;

/*
This class is responsible for undo-able change that takes place in one user's account.
Undo-able:
Create a trade

It saves the account details before the most recent undo-able change in User's account.
When a user log in to their account, their account's ActionManager will run in the background (main)
When a user logs out/closes program, their accounts ActionManager clears its memory.
@author: Carrie
@layer use case
 */
public class ActionManager{
    /**
     * this action manager is of the user with username
     */
    private final String username;

    /**
     * the duplicate of the user with username
     */
    private final User duplicate;

    private UserSystem userSystem = new UserSystem();

    /**
     * constructor
     * @param username: the username of the user this ActionManager is for.
     */
    public ActionManager(String username){

        this.username = username;
        duplicate = userSystem.duplicateUser(username);

    }

    /**
     * the main function of undoing an undoable action
     * @param user the user that performs the undo action
     * @return if undo was successful
     */
    public boolean undo(User user){
        //undo creating trade
        TradeCreatorController tcc = new TradeCreatorController();
        boolean undoable = tcc.getUndoable();
        if (undoable){
            //Undo
            userSystem.replaceMemory(user, duplicate);
            //Reset
            tcc.resetsUndoable();
            return true;
        }
        return false;
    }

}
