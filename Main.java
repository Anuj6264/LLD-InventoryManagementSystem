import java.util.HashMap;

public class Main {
    
    public static void main(String[] args) {
        ProductFactory productFactory = new ProductFactory();

        HashMap<String, Object> hashMap1 = new HashMap<>();
        hashMap1.put("size", "45"); 
        hashMap1.put("color", "blue");

        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("expiry_date", "23/07/2024");
        hashMap2.put("refrigerated", false);

        HashMap<String, Object> hashMap3 = new HashMap<>();
        hashMap3.put("brand", "Samsung");
        hashMap3.put("warranty_peroid", 6);

        Product product1 = productFactory.createProduct("SKU123", "Laptop", 1000.0, 50, 25, ProductCategory.ELECTRONICS, hashMap3);
        Product product2 = productFactory.createProduct("SKU456", "T-Shirt", 20.0, 200, 100, ProductCategory.CLOTHING, hashMap1);
        Product product3 = productFactory.createProduct("SKU789", "Apple", 1.0, 100, 200, ProductCategory.GROCERY, hashMap2);

        ReplenishmentStrategy replenishmentStrategy = new BulkOrderStrategy();
        
        InventoryManager inventoryManager = InventoryManager.getInstance(replenishmentStrategy);

        Warehouse warehouse1 = new Warehouse(1, "Warehouse1", "Pune");
        Warehouse warehouse2 = new Warehouse(2, "Warehouse2", "Mumbai");
        Warehouse warehouse3 = new Warehouse(3, "Warehouse3", "Gwalior");

        inventoryManager.addWarehouse(warehouse1);
        inventoryManager.addWarehouse(warehouse2);
        inventoryManager.addWarehouse(warehouse3);

        warehouse1.addProduct(product1, 2);
        warehouse1.addProduct(product2, 4);
        warehouse2.addProduct(product3, 6);
        warehouse2.addProduct(product1, 10);
        warehouse3.addProduct(product3, 3);
        warehouse3.addProduct(product2, 7);
        warehouse3.addProduct(product1, 13);

        // Test removeProduct functionality
        System.out.println("\n--- Testing removeProduct functionality ---");
        System.out.println("Before removal - SKU789 quantity in warehouse3: " + warehouse3.getQuantitybySku("SKU789"));
        
        // Try to remove 2 units (should succeed)
        boolean success1 = warehouse3.removeProduct(product3, 2);
        System.out.println("Removal of 2 units successful: " + success1);
        System.out.println("After removal - SKU789 quantity in warehouse3: " + warehouse3.getQuantitybySku("SKU789"));
        
        // Try to remove 5 units (should fail - insufficient inventory)
        boolean success2 = warehouse3.removeProduct(product3, 5);
        System.out.println("Removal of 5 units successful: " + success2);
        
        // Remove remaining 1 unit (should succeed and remove product completely)
        boolean success3 = warehouse3.removeProduct(product3, 1);
        System.out.println("Removal of remaining 1 unit successful: " + success3);
        System.out.println("Final quantity: " + warehouse3.getQuantitybySku("SKU789"));

        inventoryManager.getInventoryCheck();
    }
}
