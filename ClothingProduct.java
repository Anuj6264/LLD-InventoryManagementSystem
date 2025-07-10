public class ClothingProduct extends Product {
    private String size;
    private String color;
    
    public ClothingProduct(String sku, String name, double price, int quantity, int threshold, ProductCategory productCategory, String size, String color) {
        super(sku, name, price,quantity, threshold, productCategory);
        this.size = size;
        this.color = color;
    }
}
