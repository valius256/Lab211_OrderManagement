/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Services.CustomersManagement;
import Services.ProductManagement;
import java.util.Comparator;
import utils.Util;

/**
 *
 * @author Quang Phat
 */
public class Orders implements Comparable<Orders> {

    private static final String ID_Format = "DXXX";
    private static final String ID_Pattern = "D[\\d]{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 6;

    private String orderID;
    private String customerID;
    private String productID;
    private int orderQuantity;
    private String orderDate;
    private String status;

    public static String getAttributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("orderID");
        sb.append(",").append("customerID");
        sb.append(",").append("productID");
        sb.append(",").append("orderQuantity");
        sb.append(",").append("orderDate");
        sb.append(",").append("status");
        return sb.toString();
    }

    public Orders() {
    }

    public Orders(String orderID, String customerID, String productID, int orderQuantity, String orderDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        if (orderID.matches(ID_Format)) {
            this.orderID = orderID;
        }
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        if (CustomersManagement.getInstance().getCustomersByID(customerID) != null) {
            this.customerID = customerID;
        }
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        if (ProductManagement.getInstance().getProductById(productID) != null) {
            this.productID = productID;
        }
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        if (orderQuantity >= 0) {
            this.orderQuantity = orderQuantity;
        }
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        if(orderDate.isEmpty()){
            
        }
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false"))
            this.status = status;
    }

    public void input() {
//        while (true) {
//            try {
//                setOrderID(Util.inputString("Input id with patern (" + Orders.ID_Format + ")", false));
//                if (orderID == null) {
//                    throw new Exception();
//                }
//                break;
//            } catch (Exception ex) {
//                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        this.customerID = Util.inputString("Enter the CustomerID: ", false);
        if (CustomersManagement.getInstance().getCustomersByID(this.customerID) == null) {
            System.out.println("CustomerID not found, add one.");

        }

        this.productID = Util.inputString("Enter the productID: ", false);
        if (ProductManagement.getInstance().getProductById(this.customerID) == null) {
            System.out.println("V not found, add one.");
        }

        while (true) {
            try {
                setOrderQuantity(Util.inputInt("Enter orders quantity"));
                break;
            } catch (Exception ex) {
                System.out.println("Name has length from 5 to 30 characters");
            }
        }

        while (true) {
            try {
                setOrderDate(Util.inputString("Enter orders date(mm/dd/yyyy): ", false));
                break;
            } catch (Exception ex) {
                System.out.println("the format must be mm/dd/yyyy");
            }
        }

        while (true) {
            try {
                setOrderDate(Util.inputString("Enter orders status (success/pending) ", false));
                break;
            } catch (Exception ex) {
                System.out.println("the status must be (success or pending)");
            }
        }

    }

    public static String inputId() {
        String id = null;
        do {
            try {
                id = Util.inputString("Input id with patern (" + Orders.ID_Format + ")", false);
                throw new Exception();
            } catch (Exception e) {
                e.getMessage();
            }
        } while (!Util.validateStringPattern(id, Orders.ID_Pattern, true));
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderID).append(",");
        sb.append(customerID).append(",");
        sb.append(productID).append(",");
        sb.append(orderQuantity).append(",");
        sb.append(orderDate).append(",");
        sb.append(status).append(",");

//        sb.append("Order{");
//        sb.append("orderID=").append(orderID);
//        sb.append(", customerID=").append(customerID);
//        sb.append(", productID=").append(productID);
//        sb.append(", orderQuantity=").append(orderQuantity);
//        sb.append(", orderDate=").append(orderDate);
//        sb.append(", status=").append(status);
//        sb.append('}');
        return sb.toString();
    }

    public void parseOrders(String entityString) throws Exception {
        // can check so luong attribute  (id, name, price, quantity, publisherId, status)
        if (entityString != null) {
            String[] attributes = entityString.split(",", -1);
            if (attributes.length >= Orders.ENTITY_ATTRIBUTE_COUNT) {

                setOrderID(attributes[0]);
                setCustomerID(attributes[1]);
                setProductID(attributes[2]);
                try {
                    setOrderQuantity(Integer.parseInt(attributes[3]));
                } catch (NumberFormatException ex) {
                    System.out.println(">>>>> Err: " + ex.getMessage());
                }
                setOrderDate(attributes[4]);
                setStatus(attributes[5]);
            }
        }
    }

    public void inputUpdate() {
        boolean cont = false;
        do {
            String newCustomerID = Util.inputString("Input new CustomerID: ", true);
            if (newCustomerID.equals("")) {
            } else {
                if (CustomersManagement.getInstance().getCustomersByID(newCustomerID) == null) {
                    cont = true;
                }
                setCustomerID(newCustomerID);
            }
        } while (cont);

        do {
            String newProductID = Util.inputString("Input new ProductID:", true);
            if (newProductID.equals("")) {
            } else {
                if (ProductManagement.getInstance().getProductById(newProductID) == null) {
                    cont = true;
                }
                setProductID(newProductID);
            }
        } while (cont);

        do {
            String newOrdersQuantity = Util.inputString("Input new ordersQuantity:", true);
            if (newOrdersQuantity.equals("")) {
            } else {
                if (utils.OrderValidation.checOrdersQuantity(Integer.parseInt(newOrdersQuantity)) == false) {
                    cont = true;
                }
                setOrderQuantity(Integer.parseInt(newOrdersQuantity));
            }
        } while (cont);

        do {
            String newOrderDate = Util.inputString("Input new newOrderDate(mm/dd/yyyy):", false);
            if (newOrderDate.equals("")) {

            } else {
                if (utils.OrderValidation.checkOrderDate(newOrderDate) == false) {
                    cont = true;
                }
                setOrderDate(newOrderDate);
            }
        } while (cont);

        do {
            String newOrdersStatus = Util.inputString("Input new ordersQuantity:", true);
            if (newOrdersStatus.equals("")) {
            } else {
                if (utils.OrderValidation.checkOrdersStatus(newOrdersStatus) == false) {
                    cont = true;
                }
                setStatus(newOrdersStatus);
            }
        } while (cont);
    }

//     public void input() {
//        while (true) {
//            try {
//                setCustomerID(Util.inputString("Input id with patern (" + Orders.ID_Format + ")", false));
//                break;
//            } catch (Exception ex) {
//                Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        while (true) {
//            try {
//                this.customerID = Util.inputString("Enter customers Id", false);
//                if(CustomersManagement.getInstance().getCustomerById(this.customerID) == null){
//                    System.out.println("Customer not found in db");
//                    break;
//                }
//                break;
//            } catch (Exception ex) {
//                System.out.println("Name has length from 5 to 30 characters");
//            }
//        }
//        while (true) {
//            try {
//                setPrice(Util.inputDouble("Input price", 0));
//                break;
//            } catch (BException ex) {
//                System.out.println("Price must greater than 0");
//            }
//        }
//
//        while (true) {
//            try {
//                setQuantity(Util.inputInt("Input quantity", 0));
//                break;
//            } catch (BException ex) {
//                System.out.println("Quantity must greater than 0");
//            }
//        }
//
//        while (true) {
//            try {
//                setStatus(Util.inputString("Enter status (Available or Not AVailable)", true));
//                break;
//            } catch (BException e) {
//                System.out.println("Status must be \"Available\" or \"Not AVailable\"");
//            }
//        }
//
//        this.publisherId = Util.inputString("Input publisher's id", false);
//        if (PublisherManagement.getInstance().getPublisherById(this.publisherId) == null) {
//            System.out.println("Publisher not found, add one.");
//        }
//
//    }
    public static Comparator compNameAsc = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Orders b1 = (Orders) o1;
            Orders b2 = (Orders) o2;
            return b1.getCustomerID().compareTo(b2.getCustomerID());

        }
    };

    @Override
    public int compareTo(Orders o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
