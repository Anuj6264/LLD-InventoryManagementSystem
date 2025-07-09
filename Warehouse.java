import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Warehouse {
    private int id;
    private String name;
    private String location;
    private Map<String, Product> products;

    public Warehouse(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        String sku = product.getSku();
        if(products.containsKey(sku)) {
            Product existingProduct = products.get(sku);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        } else {
            product.setQuantity(quantity);
        }
        products.put(sku, product);
        System.out.println(quantity + " units of " + product.getName()
                + " (SKU: " + sku + ") added to " + name
                + ". New quantity: " + product.getQuantity());
    }

    public boolean removeProduct(Product product, int quantity) {
        String sku = product.getSku();
        if(products.containsKey(sku)) {
            Product existingProduct = products.get(sku);
            int existingQuantity = existingProduct.getQuantity();
            if(existingQuantity >= quantity) {
                existingProduct.setQuantity(existingQuantity - quantity);
                System.out.println(quantity + " units of " + product.getName()
                        + " (SKU: " + sku + ") removed from " + name
                        + ". Remaining quantity: " + existingProduct.getQuantity());
                
                if (existingProduct.getQuantity() == 0) {
                    // Remove products with zero quantity
                    products.remove(sku);
                    System.out.println("Product " + product.getName()
                            + " removed from inventory as quantity is now zero.");
                }
                return true;
            } else {
                System.out.println("Error: Insufficient inventory. Requested: "
                        + quantity + ", Available: " + existingQuantity);
                return false;
            }
        } else {
            System.out.println("Error: Product not found in " + name);
            return false;
        }
    }

    public int getQuantitybySku(String sku) {
        if(products.containsKey(sku))
            return products.get(sku).getQuantity();
        return 0;
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public String getName() {
        return this.name;
    }

}
