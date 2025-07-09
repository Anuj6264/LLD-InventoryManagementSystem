import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private static InventoryManager instance;
    private List<Warehouse> warehouses;
    private ReplenishmentStrategy replenishmentStrategy;

    private InventoryManager(ReplenishmentStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
        warehouses = new ArrayList<>();
    }

    public static synchronized InventoryManager getInstance(ReplenishmentStrategy replenishmentStrategy) {
        if(instance == null)
            instance = new InventoryManager(replenishmentStrategy);
        return instance;
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }

    public void getInventoryCheck() {
        for(Warehouse warehouse: warehouses) {
            for(Product product: warehouse.getAllProducts()) {
                if(product.getQuantity() < product.getThreshold()) {
                    System.out.println("Product " + product.getName() + " --> Current Quantity = " + product.getQuantity() + ". Threshold = " + product.getThreshold() + ". Warehouse No = " + warehouse.getName());
                    if(replenishmentStrategy != null)
                        replenishmentStrategy.replenish(product);
                }   
            }
        }
    }

}
