public class ElectronicsProduct extends Product {
    private String brand;
    private int warrantyPeriod; // in months
    public ElectronicsProduct(String sku, String name, double price, int quantity, int threshold, ProductCategory productCategory, String brand, int warrantyPeriod) {
        super(sku, name, price,quantity, threshold, productCategory);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
}
