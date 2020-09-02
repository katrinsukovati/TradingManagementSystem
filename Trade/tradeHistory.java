package Trade;/*
 * Checks for recent 3 items the Account.User traded and checks for Account.User’s 3 most frequent trading partners.
 * @layer: Use case
 * @collaborator: Account.User, Trade
 * @author: Katrin
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Account.User;
import Item.*;

public class tradeHistory {

    /**
     * @param user the user checking
     * @return Recent 3 trades the Account.User traded
     */
    public List<Trade> getRecentTrades(User user){
        if (user.getPastTrades().size() >= 3) {
            return user.getPastTrades().subList(user.getPastTrades().size() - 3, user.getPastTrades().size());
        } else {
            return user.getPastTrades().subList(0,user.getPastTrades().size());
        }
    }

    /**
     * @param user the user checking
     * @return Account.User’s 3 most frequent trading partners
     */
    public List<User> getFrequentPartners(User user){
        ArrayList<User> pastPartners = new ArrayList<>();
        // Loop through all the past trades and populate an array list including all past trading partners
        for (int i = 0; i < user.getPastTrades().size(); i++){
            for (Map.Entry<User, User> entry : user.getPastTrades().get(i).getLenderRelations().entrySet()) {
                User lender = entry.getKey();
                User borrower = entry.getValue();
                if (!lender.getName().equals(user.getName())){ pastPartners.add(lender); }
                if (!borrower.getName().equals(user.getName())){ pastPartners.add(borrower); }
            }
        }

        // A hash map that indicates the amount of times the partner traded with the user
        HashMap<User, Integer> frequentPartners = new HashMap<>();

        // Populate hashmap
        for (User pastPartner : pastPartners) {
            // Account.User not in hashmap
            if (!frequentPartners.containsKey(pastPartner)) {frequentPartners.put(pastPartner, 1);}
            // Account.User exists in hashmap thus, increment value by 1
            else { frequentPartners.replace(pastPartner, frequentPartners.get(pastPartner) + 1);}
        }

        return frequentPartners.entrySet().stream().sorted(Map.Entry.<User, Integer>comparingByValue()
                .reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());

    }
}