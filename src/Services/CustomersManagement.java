/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customers;

import model.Customers;

import utils.Util;

/**
 *
 * @author Quang Phat
 */
public class CustomersManagement extends DataManagement<Customers> {

    private static CustomersManagement instance = new CustomersManagement();

    public static CustomersManagement getInstance() {
        return instance;
    }

//    private List<Customers> customerList;
//
//    public List<Customers> getCustomerList() {
//        return customerList;
//    }
    /**
     * return the list of customers
     */
//    private CustomersManagement() {
//        this.customerList = new ArrayList();
//    }
    public Customers getCustomersByID(String cID) {
        if (cID != null && !cID.isBlank()) {
            for (Customers customers : entityList) {
                if (cID.equalsIgnoreCase(customers.getCustomerID())) {
                    return customers;
                }
            }
        }
        return null;
    }

//    public Customers createCustomer() {
//        Customers customer = new Customers();
//        customer.input();
//
//        // check exists
//        if (getCustomersByID(customer.getCustomerID()) == null) {
//            if (this.entityList.add(customer)) {
//                return customer;
//            }
//        } else {
//            System.out.println("This Customer already exists.");
//        }
//        return null;
//    }
    public Customers addNewCustomers() {
        String CustomerID = Customers.inputId();
        Customers customer = getCustomersByID(CustomerID);
        if (customer == null) {
            try {
                customer = new Customers();
                customer.setCustomerID(CustomerID);
                customer.input();
                if (entityList.add(customer)) {
                    insertData(customer);
                }
            } catch (Exception ex) {
                Logger.getLogger(CustomersManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("This Customer [" + CustomerID + "] already exists.");
        }
        return customer;
    }

//    public Customers addCustomer() {
//        String customerId = null;
//        String customerName = null;
//        String customerAddress = null;
//        String customerPhone = null;
//        boolean cont = false;
//        do {
//            customerId = Util.inputString("Enter the Id", false);
//            if (utils.CustomerValidation.checkCustomerID(customerId) == false) {
//                cont = true;
//                System.out.println("The customerID is invalid");
//            }
//
//            if (checkDuplicateCustomer(customerId) != null) {
//                cont = true;
//                System.out.println("The customerID is already exist");
//            }
//
//        } while (cont);
//
//        do {
//            customerName = Util.inputString("Enter the customer name: ", false);
//            if (utils.CustomerValidation.checkCustomerName(customerName) == false) {
//                cont = true;
//                System.out.println("The customerName is invalid");
//            }
//        } while (cont);
//
//        do {
//            customerAddress = Util.inputString("Enter the customer Address: ", false);
//            if (utils.CustomerValidation.checkCustomerAddress(customerAddress) == false) {
//                cont = true;
//                System.out.println("The customer Address is invalid");
//            }
//        } while (cont);
//
//        do {
//            customerPhone = Util.inputString("Enter the customer Phone: ", false);
//            if (utils.CustomerValidation.checkCustomerPhone(customerPhone) == false) {
//                cont = true;
//                System.out.println("The customer Phone is invalid");
//            }
//        } while (cont);
//
//        Customers c = new Customers(customerId, customerName, customerAddress, customerPhone);
//        entityList.add(c);
//        // save file
//        return c;
//
//    }
    public void printAll() {
        for (Customers customers : entityList) {
            System.out.println(customers);
        }
    }

    public void updateCustomer() {
        boolean cont = false;
        String FindID = Util.inputString("Enter the ID to update: ", false);

        if (entityList.isEmpty()) {
            System.out.println("Empty list nothing to update");
        }

        if (isExist(FindID) == false) {
            System.out.println("Customer does not exist");
        }

        for (int i = 0; i < entityList.size(); i++) {
            Customers c = entityList.get(i);
            if (c.getCustomerID().equals(FindID)) {
                do {
                    String newName = Util.inputString("Input new name: ", true);
                    if (newName.equals("")) {
                    } else {
                        if (utils.CustomerValidation.checkCustomerName(newName) == false) {
                            cont = true;
                        }
                        c.setCustomerName(newName);
                    }
                } while (cont);

                do {
                    String newAddress = Util.inputString("Input new address: ", true);
                    if (newAddress.equals("")) {
                    } else {
                        if (utils.CustomerValidation.checkCustomerAddress(newAddress) == false) {
                            cont = true;
                        }
                        c.setCustomerAddress(newAddress);
                    }
                } while (cont);

                do {
                    String newPhone = Util.inputString("Input new Phone: ", true);
                    if (newPhone.equals("")) {
                    } else {
                        if (utils.CustomerValidation.checkCustomerPhone(newPhone) == false) {
                            cont = true;
                        }
                        c.setCustomerPhone(newPhone);
                    }

                } while (cont);

            }

        }
    }

//    public static void printDataFromFile() {
//        try {
//            File f = new File("customer.txt");
//            FileReader fr = new FileReader(f);
//            BufferedReader br = new BufferedReader(fr);
//            String line = br.readLine();
//
//            while (line != null) {
//                System.out.println(line);
//                line = br.readLine();
//            }
//
//            fr.close();
//            br.close();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//      public static void PrintDataFromFile(){
//          
//     }
    public void search() {

        String FindID = Util.inputString("Enter the customerID you want to find: ", true);
        if (getCustomersByID(FindID) != null) {
            for (Customers customers : entityList) {
                if (customers.getCustomerID().equalsIgnoreCase(FindID.toUpperCase())) {
                    System.out.println(customers);
                    System.out.println("Success");
                }
            }
        } else {
            System.out.println("Error");
        }

    }

//    public void SaveToFile() {
//        if (entityList.isEmpty()) {
//            System.out.println("Khong can ghi file");
//            return;
//        }
//
//        try {
//            File f = new File("customer.txt");
//            if (!f.exists()) {
//                return;
//            }
//
//            FileWriter fw = new FileWriter(f);
//            PrintWriter pw = new PrintWriter(fw);
//
//            for (Customers customers : entityList) {
//                pw.println(customers.getCustomerID() + "|   |" + customers.getCustomerName() + "|       |" + customers.getCustomerPhone());
//            }
//
//            fw.close();
//            pw.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("Success");
//    }
    private Customers checkDuplicateCustomer(String customerId) {
        for (Customers c : entityList) {
            if (c.getCustomerID().equals(customerId)) {
                return c;
            }
        }
        return null;
    }

    private boolean isExist(String id) {
        for (Customers customers : entityList) {
            if (customers.getCustomerID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void PrintFromFle() {
        for (Customers customers : entityList) {
            System.out.println(customers);
        }
    }

    @Override
    protected Customers parseEntity(String stringEntity) {
        try {
            Customers obj = new Customers();
            obj.parseCustomer(stringEntity);
            return obj;
        } catch (Exception ex) {
            Logger.getLogger(CustomersManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
