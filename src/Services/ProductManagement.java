/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Products;

/**
 *
 * @author Quang Phat
 */
public class ProductManagement extends DataManagement<Products> {

    public static final ProductManagement instance = new ProductManagement();

    public static ProductManagement getInstance() {
        return instance;
    }

//    private List<Products> productList;
//    public List<Products> getOrderList() {
//        return productList;
//    }
//
//    private ProductManagement() {
//        this.productList = new ArrayList();
//    }
//    public static void printDataFromFile() {
//        try {
//            File f = new File("product.txt");
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
    
    public Products getProductById(String proID) {
        if (proID != null && !proID.isBlank()) {
            for (Products pro : entityList) {
                if (proID.equalsIgnoreCase(pro.getProductID())) {
                    return pro;
                }
            }
        }
        return null;
    }

    public void PrintAll() {
        for (Products products : entityList) {
            System.out.println(products);
        }
    }

    @Override
    protected Products parseEntity(String stringEntity) {
        try {
            Products p = new Products();
            p.parseProduct(stringEntity);
            return p;
        } catch (Exception e) {
            Logger.getLogger(CustomersManagement.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
