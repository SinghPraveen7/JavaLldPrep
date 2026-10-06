package org.practice.design.practice.multi_outlet_food_order;

import java.util.Map;

public class Stock {
    private Map<Ingredient, Integer> stockItem;

    public Map<Ingredient, Integer> getStockItem() {
        return stockItem;
    }

    public void setStockItem(Map<Ingredient, Integer> stockItem) {
        this.stockItem = stockItem;
    }
}
