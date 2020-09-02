package Account;

import java.util.*;

public class AccountPropertiesPresenter implements Iterator<String> {
    private ArrayList<String> accountProperties = new ArrayList<>();
    private int current = 0;

    public void loginProperties(){
        accountProperties.add("Username: ");
        accountProperties.add("Password: ");
    }

    public void createAccountProperties(){
        accountProperties.add("Username: ");
        accountProperties.add("Password: ");
        accountProperties.add("Name: ");
    }

    @Override
    public boolean hasNext() {
        return current < accountProperties.size();
    }

    //reset the account properties
    public void accountPropertiesReset(){
        current = 0;
        accountProperties = new ArrayList<>();
    }

    public String next(){
        String nextPrompt;
            nextPrompt = accountProperties.get(current);
            current += 1;
            return nextPrompt;
    }
}