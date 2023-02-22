/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import utils.Util;

/**
 *
 * @author Quang Phat
 */
public class Menu {
    
    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.Customer,
        MenuItem.Product,
        MenuItem.Order,
    };

    private final MenuItem[] customerOptions = {
        MenuItem.BACK,
        MenuItem.Customer_CreateNewCustomer,
        MenuItem.Customer_search,
        MenuItem.Customer_UpdateCustomer,
        MenuItem.Customer_PrintAll,
        MenuItem.Customer_SaveToFile,
    };

    private final MenuItem[] productOptions = {
        MenuItem.BACK,
        MenuItem.Product_PrintAll,
    };
    
    private final MenuItem[] orderOptions = {
        MenuItem.BACK,
        MenuItem.Order_CreateNewOrder,
        MenuItem.Order_Update,
        MenuItem.Order_Delete,
        MenuItem.Order_ListAllPendingOrder,
        MenuItem.Order_ListAllOrderAsc,
        MenuItem.Order_SaveToFile,
        
    }; 

    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }

    public MenuItem getUserChoice() {
        do {
            if (subOption == MenuItem.BACK) {
                primaryOption = getChoice(null);
            }
            if (primaryOption != MenuItem.EXIT) {
                subOption = getChoice(primaryOption);
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }

    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "Book store:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);

        return optionList[choice];
        //        UserController userController = Singleton.getInstance(UserController.class);
        //        for (MenuItem item : optionList) {
        ////            if (userController.checkCurrentUserRole(item.getRole())) {
        //            if (choice == 0) {
        //                return item;
        //            }
        //            choice--;
        ////            }
        //        }
        //        return optionList[0];
    }

    private int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
//        UserController userController = Singleton.getInstance(UserController.class);
        for (int i = 1; i < optionList.length; i++) {
//            if (userController.checkCurrentUserRole(optionList[i].getRole())) {
            System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
            numItems++;
//            }
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;

    }

    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            optionList = switch (option) {
                case Customer ->
                    customerOptions;
                case Product ->
                    productOptions;
                case Order ->
                    orderOptions;
                default ->
                    primaryOptions;
            };
        }

        return optionList;
    }
}
