/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Quang Phat
 */
public enum MenuItem {
    BACK("Back", UserRole.USER),
    EXIT("Exit", UserRole.USER),
    
    Product("Product", UserRole.USER),
    Product_PrintAll("Print all Product", UserRole.USER),

    
    //orders
    Order("Order", UserRole.USER),
    Order_CreateNewOrder("Create new Order", UserRole.ADMIN),
    Order_Update("Update the Order", UserRole.ADMIN),
    Order_Delete("Delete the Order", UserRole.ADMIN),
    Order_ListAllPendingOrder("List all Product that status are pending(false)", UserRole.USER),
    Order_ListAllOrderAsc("List all Orders in ascending order of Customer name", UserRole.USER),
    Order_SaveToFile("Save Orders to file(named orders.txt)", UserRole.ADMIN),
    Order_PrintAll("Print all Order from orders.txt", UserRole.USER),
    
    // customers
    Customer("Customer", UserRole.USER),
    Customer_CreateNewCustomer("Create new Customer", UserRole.ADMIN),
    Customer_UpdateCustomer("Update the new Customer", UserRole.ADMIN),
    Customer_search("Search customer base on ID", UserRole.USER ),
    Customer_SaveToFile("Save to file(named customer.txt)", UserRole.ADMIN),
    Customer_PrintAll("Print from file", UserRole.USER);

    private final UserRole role;
    private final String label;

    public UserRole getRole() {
        return role;
    }

    public String getLabel() {
        return label;
    }

    private MenuItem(String label, UserRole role) {
        this.role = role;
        this.label = label;
    }

}
