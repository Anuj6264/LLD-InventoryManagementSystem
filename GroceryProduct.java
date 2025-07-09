import java.time.LocalDate;

public class GroceryProduct extends Product {
    private String expiryDate;
    private boolean refrigerated;

public GroceryProduct(String sku, String name, double price, int quantity, int threshold, ProductCategory productCategory, String expiryDate, boolean refrigerated) {
        super(sku, name, price,quantity, threshold, productCategory);
        this.expiryDate = expiryDate;
        this.refrigerated = refrigerated;
    }
}