package utils;

public class OrderValidation{
    private static final String Date_format = "mm/dd/yyyy";
    
    public static boolean checkOrderID(String Id) {
        return Id.matches("[D]+\\d{3}");
    }

    public static boolean checkOrderName(String Name) {
        return Name.length() > 5 && Name.length() < 30;
    }

    public static boolean checOrdersPrice(double price) {
        return price > 0;
    }   

    public static boolean checOrdersQuantity(int quantity) {
        return quantity > 0;
    }

    public static boolean checkOrdersStatus(String Status) {
        return Status.equals("Available") || Status.equals("Not Available");
    }
    
    public static boolean checkOrderDate(String date){
        return date.matches(Date_format);
    }
    
}