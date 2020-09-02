// Author: Allan
package Serializer;

import Account.User;
import Admin_Controls.AdminManager;

import java.io.*;

//read/write file

public class CSVReader {

    private final String location = "phase2/src/Serializer/limit.csv";

    /**
     * Reads data from CSV file
     */
    public void readFromCSV() {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        File file1 = new File(location);
        if (file1.exists()) {

            try {
                br = new BufferedReader(new FileReader(location));
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(cvsSplitBy);
                    User user = new User("1", "1", "1");
                    user.setLimitPerWeek(Integer.parseInt(values[0]));
                    AdminManager AM = new AdminManager();
                    AM.setMaxIncompleteTrade(Integer.parseInt(values[1]));
                    AM.setTradePrerequisite(Integer.parseInt(values[2]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            User user = new User("1", "1", "1");
            user.setLimitPerWeek(3);
            AdminManager AM = new AdminManager();
            AM.setMaxIncompleteTrade(3);
            AM.setTradePrerequisite(0);
        }
    }

    /**
     * Writes/adds data to CSV file
     */
    public void writeToCSV(){
        try {
            File newfile = new File(location);
            if (!newfile.exists()){
                try {
                    newfile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter writer = new FileWriter(location, false);
            User user = new User("1", "1", "1");
            AdminManager AM = new AdminManager();
            String csv = (user.getLimitPerWeek()) + "," + (AM.getMaxIncompleteTrade()) + "," + (AM.getTradePrerequisite());
            writer.append(csv);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}