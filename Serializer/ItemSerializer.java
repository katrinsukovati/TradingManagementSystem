// Author: Allan
package Serializer;

import Item.Inventory;
import Item.Item;
import User_Controls.UserSystem;
import Item.ItemRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemSerializer implements Serializable{

    private static final Logger logger = Logger.getLogger(ItemSerializer.class.getName());
    private static final Handler consoleHandler = new ConsoleHandler();
    private final ItemRequest requested;
    private final Inventory inventory;
    private final String requestedFile = "phase2/src/Serializer/requestedItems.ser";
    private final String approvedFile = "phase2/src/Serializer/approvedItems.ser";

    /**
     * serialize item method
     * @param requested item request made by user
     * @param inventory the inventory of items
     */
    public ItemSerializer(ItemRequest requested, Inventory inventory) {
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        this.requested = requested;
        this.inventory = inventory;
    }

    /**
     * serialize requested items method
     * @throws IOException
     */
    public void serializeRequestedItems() throws IOException {
        File file1 = new File(requestedFile);
        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OutputStream file = new FileOutputStream(requestedFile);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(requested.getRequestedItems());
        output.close();
        System.out.println("Requested Items Saved");
    }

    /**
     * serialize approved items
     * @throws IOException
     */
    public void serializeApprovedItems() throws IOException {
        File file2 = new File(approvedFile);
        if (!file2.exists()) {
            file2.createNewFile();
        }

        OutputStream file = new FileOutputStream(approvedFile);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(inventory.getItemStorage());
        output.close();
        System.out.println("Approved Items Saved");
    }

    /**
     * deserialize requested items
     * @param US system of all users
     */
    public void deserializeRequestedItems(UserSystem US){
        File file1 = new File(requestedFile);
        if (file1.exists() && file1.length() != 0) {
            try {
                InputStream file = new FileInputStream(requestedFile);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);

                requested.getRequestedItems().clear();
                ArrayList<Item> newList = (ArrayList<Item>) input.readObject();
                for (Item e : newList) {
                    e.setItemOwner(US.getUser(e.getItemOwner().getUsername()));
                    requested.getRequestedItems().add(e);
                }

                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "Cannot read from input.", ex);
            }
        }
    }

    /**
     * deserialize approved items
     * @param US system of all users
     */
    public void deserializeApprovedItems(UserSystem US){
        File file2 = new File(approvedFile);
        if (file2.exists() && file2.length() != 0) {
            try {
                InputStream file = new FileInputStream(approvedFile);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);

                inventory.getItemStorage().clear();
                ArrayList<Item> newList = (ArrayList<Item>) input.readObject();
                for (Item e : newList) {
                    e.setItemOwner(US.getUser(e.getItemOwner().getUsername()));
                    for (int i = 0; i < e.getPastOwners().size(); i++) {
                        e.getPastOwners().set(i, US.getUser(e.getPastOwners().get(i).getUsername()));
                    }
                    inventory.getItemStorage().add(e);
                }

                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "Cannot read from input.", ex);
            }
        }
    }
}