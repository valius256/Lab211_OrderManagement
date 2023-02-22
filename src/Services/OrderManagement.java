/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Services;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;
import static model.Orders.compNameAsc;
import utils.Util;

/**
 *
 * @author Quang Phat
 */
public class OrderManagement extends DataManagement<Orders> {

    private static OrderManagement instance = new OrderManagement();

    public static OrderManagement getInstace() {
        return instance;
    }

    public Orders createNewOrder() {
        Orders order = new Orders();
        order.input();

        if (getOrdersByID(order.getOrderID()) == null) {
            if (this.entityList.add(order)) {
                return order;
            }
        } else {
            System.out.println("This order already exists.");
        }
        return null;
    }

    public Orders addOrders() {
        
        String orderID = Orders.inputId();
        Orders orders = getOrdersByID(orderID);
        if (orders == null) {
            try {
                orders = new Orders();
                orders.setOrderID(orderID);
                orders.input();
                if (entityList.add(orders)) {
                    insertData(orders);
                }
            } catch (Exception ex) {
                Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("This orders [" + orderID + "] already exists.");
        }
        return orders;
    }
   
    public void listAllPendingOrders() {
        for (Orders orders : entityList) {
            if (orders.getStatus().equalsIgnoreCase("false")) {
                System.out.println(orders);
            }
        }
    }
    
    private boolean checkDuplicate(String Id) {
        for (Orders orders : entityList) {
            if (orders.getOrderID().equals(Id)) {
                return true;
            }
        }
        return false;
    }
    
    public void update(){
        String ID = Util.inputString("Input ID of Orders to update: ", true);

        if (!checkDuplicate(ID)) {
            System.out.println("OrdersID  does not exist");
        }

        for (int i = 0; i < entityList.size(); i++) {
            Orders o = entityList.get(i);
            if (o.getOrderID().equals(ID)) {
                o.inputUpdate();
            }
        }
    }
    
    public void delete() {
        String ID = Util.inputString("Input OrdersID: ", true);
        for (Orders orders : entityList) {
            if (orders.getOrderID().equals(ID)) {
                entityList.remove(orders);
                System.out.println("Success");
                return;
            } else {
                System.out.println("Failed");
            }
        }
        System.out.println("Order’s ID does not exist");
    }


    public Orders getOrdersByID(String oID) {
        if (oID != null && !oID.isBlank()) {
            for (Orders orders : entityList) {
                if (oID.equalsIgnoreCase(orders.getOrderID())) {
                    return orders;
                }
            }
        }
        return null;
    }

    public void printAllAsc() {
        Collections.sort(entityList, compNameAsc);
        for (Orders orders : entityList) {
            System.out.println(orders);
        }
    }

    @Override
    protected Orders parseEntity(String stringEntity) {
        try {
            Orders obj = new Orders();
            obj.parseOrders(stringEntity);
            return obj;
        } catch (Exception ex) {
            Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
