package org.practice.design.practice.multi_outlet_food_order;

import java.util.Map;

public class OrderItem {
    String orderItemId;
    Map<Item, Integer> itemQuantityDetails;
    boolean isAvailable;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Map<Item, Integer> getItemQuantityDetails() {
        return itemQuantityDetails;
    }

    public void setItemQuantityDetails(Map<Item, Integer> itemQuantityDetails) {
        this.itemQuantityDetails = itemQuantityDetails;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
