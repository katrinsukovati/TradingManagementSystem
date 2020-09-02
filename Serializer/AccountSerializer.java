// Author: Allan
package Serializer;

import Account.Admin;
import Account.User;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import User_Controls.*;


public class AccountSerializer implements Serializable{

    private static final Logger logger = Logger.getLogger(AccountSerializer.class.getName());
    private static final Handler consoleHandler = new ConsoleHandler();
    private final UserSystem US;
    private final String userPath = "phase2/src/Serializer/users.ser";
    private final String adminPath = "phase2/src/Serializer/admins.ser";


    /**
     * account serializer method
     * @param system system of all users
     */
    public AccountSerializer(UserSystem system) {
        US = system;
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    /**
     * serialize user method
     */
    public void serializeUsers(){
        File newfile = new File(userPath);
        if (!newfile.exists()){
            try {
                newfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream file = null;
        try {
            file = new FileOutputStream(userPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("something went wrong, files are not saved");

        }
        OutputStream buffer = new BufferedOutputStream(file);
        try {
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(US.getUsersList());
            output.close();
            System.out.println("Users Saved");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("something went wrong, files are not saved");
        }
    }


    /**
     * serialize admin method
     */
    public void serializeAdmin(){
        File newfile1 = new File(adminPath);
        if (!newfile1.exists()){
            try {
                newfile1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream file = null;
        try {
            file = new FileOutputStream(adminPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("something went wrong, files are not saved");

        }
        OutputStream buffer = new BufferedOutputStream(file);
        try {
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(US.getAdminsList());
            output.close();
            System.out.println("Admins Saved");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("something went wrong, files are not saved");
        }

    }


    /**
     * deserilaize user method
     */
    public void deserializeUser(){
        File file1 = new File(userPath);
        if (file1.exists() && file1.length() != 0) {
            try {
                InputStream file = new FileInputStream(userPath);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);
                US.getUsersList().clear();

                ArrayList<User> users = (ArrayList<User>) input.readObject();
                for (User e : users) {
                    US.getUsersList().add(e);
                }

                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "Cannot read from input.", ex);
            }
        }
    }

    /**
     * deserialzation admin method
     */
    public void deserializeAdmin() {
        File file2 = new File(adminPath);
        if (file2.exists() && file2.length() != 0) {
            try {
                InputStream file = new FileInputStream(adminPath);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);
                US.getAdminsList().clear();

                ArrayList<Admin> admins = (ArrayList<Admin>) input.readObject();
                for (Admin e : admins) {
                    US.getAdminsList().add(e);
                }

                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "Cannot read from input.", ex);
            }
        }
    }
}