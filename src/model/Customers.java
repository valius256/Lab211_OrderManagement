/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Services.CustomersManagement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Util;

/**
 *
 * @author Quang Phat
 */
public class Customers implements Comparable<Customers> {

    private static final String ID_Format = "CXXX";
    private static final String ID_Pattern = "C\\d{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 4;

    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    public static String getAttributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID");
        sb.append(",").append("NAME");
        sb.append(",").append("QUANTITY");
        sb.append(",").append("Address");
        sb.append(",").append("Phone");
        return sb.toString();
    }

    public Customers() {
    }

    public Customers(String customerID, String customerName, String customerAddress, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        if ((customerID.matches("[C]+\\d{3}"))) {
            this.customerID = customerID;
        }
        return;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void input() {
        while (true) {
            try {
                setCustomerID(Util.inputString("Input id with patern (" + Customers.ID_Format + ")", false));
                if (customerID == null) {
                    throw new Exception();
                }
                break;
            } catch (Exception ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                setCustomerName(Util.inputString("Enter name", false));
                if (customerName == null) {
                    throw new Exception();
                }
                break;
            } catch (Exception ex) {
                System.out.println("Name must not be null ");
            }
        }

        while (true) {
            try {
                setCustomerAddress(Util.inputString("Enter Address: ", false));
                if (customerAddress == null) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Address must not be null ");
            }
        }

        while (true) {
            try {
                setCustomerPhone(Util.inputString("Enter Phone: ", false));
                break;
            } catch (Exception e) {
                System.out.println("Phone must be in 10 to 12");
            }
        }
        
        if (CustomersManagement.getInstance().getCustomersByID(this.getCustomerID()) == null) {
            System.out.println("Customer not found, add one.");
        }

    }
    
     public static String inputId() {
        String id = null;
        do {
            id = Util.inputString("Input id with patern (" + Customers.ID_Format + ")", false);
        } while (!Util.validateStringPattern(id, Customers.ID_Pattern, true));
        return id;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{");
        sb.append("id=").append(customerID);
        sb.append(", name=").append(customerName);
        sb.append(", address=").append(customerAddress);
        sb.append(", phone=").append(customerPhone);
        sb.append('}');
        return sb.toString();
    }

    public void parseCustomer(String entityString) throws Exception {
        // can check so luong attribute  (id, name, price, quantity, publisherId, status)
        if (entityString != null) {
            String[] attributes = entityString.split(",", -1);
            if (attributes.length >= Customers.ENTITY_ATTRIBUTE_COUNT) {
                setCustomerID(attributes[0]);
                setCustomerName(attributes[1]);
                setCustomerAddress(attributes[2]);
                setCustomerPhone(attributes[3]);
            }
        }
    }

    @Override
    public int compareTo(Customers o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
