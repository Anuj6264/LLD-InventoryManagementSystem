import java.util.HashMap;

public class ProductFactory {
    
    public Product createProduct(String sku, String name, double price, int quantity, int threshold, ProductCategory productCategory, HashMap<String, Object>hashMap) {
        switch (productCategory) {
            case CLOTHING:
                String size = (String) hashMap.get("size");
                String color = (String) hashMap.get("color");
                return new ClothingProduct(sku, name, price, quantity, threshold, productCategory, size, color);
            case GROCERY:
                String expiryDate = (String) hashMap.get("expiry_date");
                boolean refrigerated = (boolean) hashMap.get("refrigerated");
                return new GroceryProduct(sku, name, price, quantity, threshold, productCategory, expiryDate, refrigerated);
            case ELECTRONICS:
                String brand = (String) hashMap.get("brand");
                int warrantyPeroid = (int) hashMap.get("warranty_peroid");
                return new ElectronicsProduct(sku, name, price, quantity, threshold, productCategory, brand, warrantyPeroid);
            default:
                return null;
        }
    }
}
