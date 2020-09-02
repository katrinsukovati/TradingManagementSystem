// Author: Allan
package Serializer;

import Admin_Controls.UnfreezeRequest;
import Admin_Controls.UnfreezeRequestInventory;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnfreezeRequestSerializer implements Serializable {

    private static final Logger logger = Logger.getLogger(UnfreezeRequestSerializer.class.getName());
    private static final Handler consoleHandler = new ConsoleHandler();
    private final String path = "phase2/src/Serializer/unFreezeRequest.ser";

    public UnfreezeRequestSerializer(){
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    /**
     * serialize unfreeze requests
     */
    public void serializeRequests(){
        File newfile = new File(path);
        if (!newfile.exists()){
            try {
                newfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream file = null;
        try {
            file = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("something went wrong, files are not saved");

        }
        OutputStream buffer = new BufferedOutputStream(file);
        try {
            ObjectOutput output = new ObjectOutputStream(buffer);
            UnfreezeRequestInventory UF = new UnfreezeRequestInventory();
            output.writeObject(UnfreezeRequestInventory.getUnfreezeRequests());
            output.close();
            System.out.println("Unfreeze Request Saved");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("something went wrong, files are not saved");
        }
    }

    /**
     * deserialize unfreeze requests
     */
    public void deserializeRequests() {
        File file2 = new File(path);

        if (file2.exists() && file2.length() != 0) {
            try {
                InputStream file = new FileInputStream(path);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);
                ArrayList<UnfreezeRequest> requests = (ArrayList<UnfreezeRequest>) input.readObject();
                UnfreezeRequestInventory UF = new UnfreezeRequestInventory();
                for (UnfreezeRequest e : requests) {
                    UF.addUnfreezeRequest(e.getUser(), e.getDescription());
                }
                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "Cannot read from input.", ex);
            }
        }
    }
}