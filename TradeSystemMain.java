/*
 * Author: David
 * Description: Test Code trying out Browse
 */
import Account.LoginController;
import Item.Inventory;
import Serializer.*;
import Trade.*;
import User_Controls.UserSystem;
import Account.Admin;
import Item.ItemRequest;

import java.io.IOException;

public class TradeSystemMain {

    public static void main(String[] args) {

        TimeSerializer TS = new TimeSerializer();
        TS.deserializeTime();
        Inventory iv = new Inventory();
        ItemRequest ir = new ItemRequest();
        UserSystem us = new UserSystem();
        AccountSerializer ac = new AccountSerializer(us);
        ac.deserializeAdmin();
        ac.deserializeUser();
        ItemSerializer is = new ItemSerializer(ir, iv);
        is.deserializeApprovedItems(us);
        is.deserializeRequestedItems(us);

        UserSystem allAccounts = new UserSystem();
        if(allAccounts.getAdminsList().isEmpty()){
            Admin firstAdmin = new Admin("admin", "admin", "admin");
            allAccounts.addAdmin(firstAdmin);
        }
        UnfreezeRequestSerializer UFS = new UnfreezeRequestSerializer();
        UFS.deserializeRequests();
        CSVReader CR = new CSVReader();
        CR.readFromCSV();
        BackgroundTradeChecker bk = new BackgroundTradeChecker();
        bk.checkAllTrades();
        TradeTimeTracker TTT = new TradeTimeTracker();
        TTT.checkForRefresh();
        LoginController startScreen = new LoginController();

        //phase 1 menu
        startScreen.loginMainMenu();
        try {
            is.serializeApprovedItems();
            is.serializeRequestedItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ac.serializeUsers();
        ac.serializeAdmin();
        UFS.serializeRequests();
        CR.writeToCSV();
        TS.serializeTime();

        System.out.println("The program will now close");
    }
}