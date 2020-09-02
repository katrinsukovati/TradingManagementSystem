package Item;
/*
 * Author: David
 * Description: Item.Browse use case class that allows a user to select a page to view with a set number of items.
 */

import java.util.ArrayList;
import java.util.List;

public class Browse {

    /**
     * @param pageNum the page number (splitting it depending on itemsPerPage)
     * @param itemsPerPage the number of items in each page
     * @param itemsToBrowse is the items available to browse
     * @return a StringBuilder that can be outputted for display.
     */
    public StringBuilder itemsToView(ArrayList<Item> itemsToBrowse, int pageNum, int itemsPerPage){
        StringBuilder output = new StringBuilder();
        if(itemsToBrowse.isEmpty()){

            output.append("This list is empty!");
        }else{
            //This determines how many items should appear on last page.
            int checkPage = pageNum*itemsPerPage;
            while(checkPage > itemsToBrowse.size()) {
                checkPage--;
            }

            //This creates the StringBuilder to print out in other classes.
            List<Item> inventorySubset = itemsToBrowse.subList(pageNum*itemsPerPage-itemsPerPage, checkPage);
            int counter = 1;
            while (counter - 1 < inventorySubset.size()){
                output.append(pageNum*itemsPerPage-itemsPerPage+counter).append(". ").append(inventorySubset.get(counter - 1).getItemName()).append('\n');
                counter ++;
            }
        }
        return output;
    }
}