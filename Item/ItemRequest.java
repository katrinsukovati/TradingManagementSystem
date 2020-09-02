package Item;

import java.util.ArrayList;

public class ItemRequest {
    private static final ArrayList<Item> requestedItems = new ArrayList<>();

    /**
     * @return the item requested list
     */
    public ArrayList<Item> getRequestedItems() {
        return requestedItems;
    }

    /**
     * @param item: new item added by user pending review by an admin
     */
    public void addItems(Item item) {
        requestedItems.add(item);
    }

    /**
     * @param item: item removed by admin post-review
     */
    public void removeItem(Item item) {
        for (int i = 0; i < requestedItems.size(); i++) {
            if (requestedItems.get(i) == item) {
                requestedItems.remove(i);
                break;
            }
        }
    }
}