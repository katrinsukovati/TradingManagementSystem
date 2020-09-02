package Item;
/*
 * Author: David
 * Description: This class contains the properties in each type of item to present in Item.ItemRegistrationController.
 */
import java.util.ArrayList;
import java.util.Iterator;

public class ItemPropertiesIterator implements Iterator<String> {
    ArrayList<String> itemProperties = new ArrayList<>();
    private int current = 0;

    public ItemPropertiesIterator(){
        itemProperties.add("Item name:");
        itemProperties.add("Description:");
    }

    /**
     * @return the properties of all the items
     */
    public ArrayList<String> getItemProperties() {
        return itemProperties;
    }

    @Override
    public boolean hasNext() {
        return current < itemProperties.size();
    }

    //resets the account properties
    public void accountPropertiesReset(){
        current = 0;
    }

    public String next(){
        String nextPrompt;
        nextPrompt = itemProperties.get(current);
        current += 1;
        return nextPrompt;
    }
}