/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Services.FileDataService;
import Services.OrderManagement;
import Services.UserManagetment;
import Services.ProductManagement;
import Services.CustomersManagement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;
import model.Products;
import model.Customers;

/**
 *
 * @author Quang Phat
 */
public class Main {

    private static String productDataFilePath = "product.txt";
    private static String ordderDataFilePath = "order.txt";
    private static String customerDataFilePath = "customer.txt";

//    public Main() {
//        init();
//    }
    private OrderManagement orderManagement;
    private ProductManagement productManagement;
    private CustomersManagement customerManagement;

    private void init() {
        try {
            // order
            this.orderManagement = orderManagement.getInstace();
            this.orderManagement.setDatabaseService(new FileDataService(Main.ordderDataFilePath, Orders.getAttributesHeader()));
            this.orderManagement.loadData();
            // product
            this.productManagement = productManagement.getInstance();
            this.productManagement.setDatabaseService(new FileDataService(Main.productDataFilePath, Products.getAttributesHeader()));
            this.productManagement.loadData();
            // customer
            this.customerManagement = customerManagement.getInstance();
            this.customerManagement.setDatabaseService(new FileDataService(Main.customerDataFilePath, Customers.getAttributesHeader()));
            this.customerManagement.loadData();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addCustomer() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            System.out.println("Add new Customer: ");
            Customers c = customerManagement.addNewCustomers();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    private void updateCustomerByID() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            System.out.println("Update information");
            customerManagement.updateCustomer();
        } else {
            System.out.println("Dont have permission need Admin role");
        }

    }

    private void searchCustomerByID() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            System.out.println("Search information");
            customerManagement.search();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    private void SaveToFile() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            System.out.println("Save to File");
            customerManagement.saveData();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    private void PrintFromFile() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            System.out.println("Save to File");
            customerManagement.printAll();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    // product
    private void printAllProduct() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.USER) == true) {
            System.out.println("Print all product in product.txt");
            productManagement.PrintAll();
        }
        
    }

//    //order
//    private void CreateNewOrder(){
//        System.out.println("CreateNewOrder");
//        orderManagement.AddOrder();
//    }
    private void printAllOrder() {
        System.out.println("Print all orders(ascending)");
        orderManagement.printAllAsc();
    }

    private void createNewOrder() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            Orders orders = this.orderManagement.addOrders();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    private void deleteOrders() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            System.out.println("Delete the orders");
            orderManagement.delete();
            OrderManagement.getInstace().saveData();
        } else {
            System.out.println("Dont have permission need Admin role");
        }

    }

    private void listAllPendingOrders() {
        System.out.println("list All Pending Orders");
        orderManagement.listAllPendingOrders();

    }

    private void saveToFile() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            System.out.println("Save to file");
            orderManagement.saveData();
        } else {
            System.out.println("Dont have permission need Admin role");
        }

    }

    private void updateOrders() {
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            System.out.println("Update");
            orderManagement.update();
            orderManagement.saveData();
        } else {
            System.out.println("Dont have permission need Admin role");
        }

    }

    private void process() {
        Menu menu = new Menu();
        int option = Integer.MAX_VALUE;
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case Customer_CreateNewCustomer:
                    addCustomer();
                    break;
                case Customer_UpdateCustomer:
                    updateCustomerByID();
                    break;
                case Customer_search:
                    searchCustomerByID();
                    break;
                case Customer_SaveToFile:
                    SaveToFile();
                    break;
                case Customer_PrintAll:
                    PrintFromFile();
                    break;

                case Product_PrintAll:
                    printAllProduct();
                    break;

                case Order_CreateNewOrder:
                    createNewOrder();
                    break;
                case Order_Delete:
                    deleteOrders();
                    break;
                case Order_ListAllPendingOrder:
                    listAllPendingOrders();
                    break;
                case Order_ListAllOrderAsc:
                    printAllOrder();
                    break;
                case Order_SaveToFile:
                    saveToFile();
                    break;
                case Order_Update:
                    updateOrders();
                    break;

                case EXIT:
                    System.out.println("Exited!");
                    break;

                default:
                    System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    private void start() {
        System.out.println("Order management");
        if (UserManagetment.getInstance().login()) {
            UserManagetment.getInstance().getCurrentUser().output();
            init();
            process();
        } else {
            System.out.println("Login failed!");
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
