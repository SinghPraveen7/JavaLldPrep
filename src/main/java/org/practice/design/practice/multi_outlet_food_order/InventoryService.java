package org.practice.design.practice.multi_outlet_food_order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryService {

    Map<String, Outlet> outletMap;
    Map<String, Map<Ingredient, Integer>> reservseStockMap;
    private final ReentrantLock lock;

    public InventoryService() {
        outletMap = new ConcurrentHashMap<>();
        reservseStockMap = new ConcurrentHashMap<>();
        lock = new ReentrantLock();
    }

    boolean addItemInStock(Map<Ingredient, Integer> itemsToAddInStock, String outletId) {
        Outlet outlet = outletMap.get(outletId);
        if (outlet == null) return false;
        lock.lock();
        try {
            Stock stock = outlet.getStock();
            for (Map.Entry<Ingredient, Integer> entry: itemsToAddInStock.entrySet()) {
                stock.getStockItem().merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
            outlet.setStock(stock);
        } finally {
            lock.unlock();
        }
        return true;
    }

    MenuItem getOutletMenu(String outletId) {
        return outletMap.get(outletId).getMenu();
    }

    boolean reserveItemsStock(List<Item> items, String orderId, String outletId) {
        lock.lock();
        try {
            Map<Ingredient, Integer> stockRequired = new HashMap<>();
            for (Item item: items) {
                stockRequired.putAll(item.getRecipe());
            }
            Outlet outlet = outletMap.get(outletId);
            Stock stock = outlet.getStock();
            for (Map.Entry<Ingredient, Integer> entry: stockRequired.entrySet()) {
                if (stock.getStockItem().getOrDefault(entry.getKey(), 0) < entry.getValue()) return false;
                stock.getStockItem().merge(entry.getKey(), -entry.getValue(), Integer::sum);
            }
            reservseStockMap.put(orderId + "_" + outletId, stockRequired);
            outlet.setStock(stock);
        } finally {
            lock.unlock();
        }
        return true;
    }

    void releaseStock(String orderId, String outletId) {
        Map<Ingredient, Integer> stockToAddBack = reservseStockMap.get(orderId + "_" + outletId);
        addItemInStock(stockToAddBack, outletId);
    }

}
