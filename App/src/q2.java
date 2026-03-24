import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

 class FlashSaleInventoryManager {

    ConcurrentHashMap<String, AtomicInteger> stock =
            new ConcurrentHashMap<>();

    ConcurrentHashMap<String, Queue<Integer>> waitingList =
            new ConcurrentHashMap<>();

    public void addProduct(String productId, int quantity){

        stock.put(productId,new AtomicInteger(quantity));

        waitingList.put(productId,new LinkedList<>());
    }

    public int checkStock(String productId){

        if(!stock.containsKey(productId))
            return 0;

        return stock.get(productId).get();
    }

    public String purchaseItem(String productId,int userId){

        AtomicInteger count = stock.get(productId);

        if(count == null)
            return "Product not found";

        while(true){

            int currentStock = count.get();

            if(currentStock == 0){

                waitingList.get(productId).add(userId);

                return "Out of stock. Added to waiting list position "
                        + waitingList.get(productId).size();
            }

            if(count.compareAndSet(currentStock,currentStock-1)){

                return "Purchase successful. Remaining stock: "
                        + (currentStock-1);
            }
        }
    }

    public static void main(String args[]){

        FlashSaleInventoryManager system =
                new FlashSaleInventoryManager();

        system.addProduct("IPHONE15",5);

        System.out.println(system.checkStock("IPHONE15"));

        System.out.println(system.purchaseItem("IPHONE15",101));
        System.out.println(system.purchaseItem("IPHONE15",102));
        System.out.println(system.purchaseItem("IPHONE15",103));
        System.out.println(system.purchaseItem("IPHONE15",104));
        System.out.println(system.purchaseItem("IPHONE15",105));

        System.out.println(system.purchaseItem("IPHONE15",106));

    }
}