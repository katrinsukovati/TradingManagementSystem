// Author: Allan
package Serializer;

import Trade.TradeTimeTracker;

import java.io.*;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeSerializer implements Serializable {

    private static final Logger logger = Logger.getLogger(TimeSerializer.class.getName());
    private static final Handler consoleHandler = new ConsoleHandler();
    private final String path = "phase2/src/Serializer/time.ser";

    public TimeSerializer(){
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    /**
     * serialize time method
     */
    public void serializeTime(){
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
            TradeTimeTracker TTT = new TradeTimeTracker();
            output.writeObject(TTT.getCurrWeek());
            output.close();
            System.out.println("Time Saved");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("something went wrong, files are not saved");
        }
    }

    /**
     * deserialize time method
     */
    public void deserializeTime() {
        File file2 = new File(path);

        if (file2.exists() && file2.length() != 0) {
            try {
                InputStream file = new FileInputStream(path);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);
                Date time = (Date) input.readObject();
                TradeTimeTracker TTT = new TradeTimeTracker();
                TTT.setCurrWeek(time);

                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "Cannot read from input.", ex);
            }
        }
    }
}