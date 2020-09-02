package Item;
/*
 * Author: David
 * Description: This class decides what kind of item is made depending on user input.
 */

import java.util.HashMap;
import java.util.Scanner;

import Account.*;
import Account.User;

public class ItemRegistrationController {
    private Scanner input = new Scanner(System.in);
    private ItemPropertiesIterator itemProperties = new ItemPropertiesIterator();
    private MenuPresenter menu = new MenuPresenter();
    private ItemRequest listOfRequestedItems = new ItemRequest();
    private HashMap<String, String> tempProperties = new HashMap<>();

    //gets all the data required
    public void getData(){
        while(itemProperties.hasNext()){
            String tempPrompt = itemProperties.next();
            System.out.println(tempPrompt);
            String inputStr = input.nextLine();
            tempProperties.put(tempPrompt, inputStr);
        }
    }

    //this is the function that is first run
    public void run(User user){
        menu.itemTypeMenu();
        System.out.println(menu.printMenu());
        String inputNum = input.nextLine();
        while(!inputNum.equals("0")){
            switch(inputNum){
                case "1": //Beauty Product
                    getData();
                    Item newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Beauty Product", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "2": //Camping & Outdoors
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Camping & Outdoors", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "3": //Cleaning Products
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Cleaning Products", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "4": //Clothing
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Clothing", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "5": //Fashion Accessory
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Fashion Accessory", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "6": //Construction Tools
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Construction Tools", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "7": //Furniture
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Furniture", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "8": //Gardening Tools
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Gardening Tools", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "9": //Health
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Health", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "10": //House
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "House", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "11": //Sport Goods
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Sport Goods", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "12": //"Accessories and Care for Vehicles"
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Accessories and Care for Vehicles", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "13": //Vehicles
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Vehicles", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "14": //Electronics item
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            "Electronics", false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                case "15": //Other item
                    System.out.println("What type of item would you like to register?");
                    String tempItemType;
                    tempItemType = input.nextLine();
                    getData();
                    newItem = new Item(user, tempProperties.get(itemProperties.getItemProperties().get(0)),
                            tempProperties.get(itemProperties.getItemProperties().get(1)),
                            tempItemType, false);
                    listOfRequestedItems.addItems(newItem);
                    itemProperties.accountPropertiesReset();
                    System.out.println("The item request has been sent to administration!");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
                default: //This happens when user gives invalid input (not 0-15)
                    System.out.println("Invalid Input. Please try again.");
                    System.out.println(menu.printMenu());
                    inputNum = input.nextLine();
                    break;
            }
        }
    }
}