/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import utils.Util;

/**
 *
 * @author Quang Phat
 */
public class Customers implements Comparable<Customers> {

    private static final String ID_Format = "CXXX";
    private static final String ID_Pattern = "C[\\d]{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 4;

    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    public static String getAttributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID");
        sb.append(",").append("NAME");
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

    public void setCustomerID(String customerID) throws Exception{
        if ((customerID.matches("C[+\\d]{3}"))) {
            this.customerID = customerID;
        }
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
        if (customerPhone.length() >= 10 && customerPhone.length() <= 12) {
            this.customerPhone = customerPhone;
        }
    }

    public void input() {
   
        while (true) {
            try { 
                setCustomerName(Util.inputString("Enter name", false).toUpperCase().trim());
                break;
            } catch (Exception ex) {
                System.out.println("Name must not be null ");
            }
        }

        while (true) {
            try {
                setCustomerAddress(Util.inputString("Enter Address: ", false).toUpperCase().trim());
                break;
            } catch (Exception e) {
                System.out.println("Address must not be null ");
            }
        }

        while (true) {
            try {
                setCustomerPhone(Util.inputString("Enter Phone: ", false).toUpperCase().trim());
                break;
            } catch (Exception e) {
                System.out.println("Phone must be in 10 to 12");
            }
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
        sb.append(customerID).append(",");
        sb.append(customerName).append(",");
        sb.append(customerAddress).append(",");
        sb.append(customerPhone).append(",");
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
