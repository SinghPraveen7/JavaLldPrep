package org.practice.design.practice.multi_outlet_food_order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OrderService {

    Map<String, Order> orderData;
    Map<String, String> idempotencyMap;
    InventoryService inventoryService;
    PaymentService paymentService;

    public OrderService() {
        orderData = new ConcurrentHashMap<>();
        idempotencyMap = new ConcurrentHashMap<>();
        inventoryService = new InventoryService();
    }

    public boolean placeOrder(OrderItem orderItem, String userId, String outletId, String idempotencyKey) {
        Order order = null;
        if (idempotencyMap.containsKey(idempotencyKey)) {
            order = orderData.get(idempotencyMap.get(idempotencyKey));
        } else {
            order = createOrder(orderItem, userId, outletId, idempotencyKey);
        }
        List<Item> items = prepareItemList(orderItem.getItemQuantityDetails());
        inventoryService.reserveItemsStock(items, order.getOrderId(), outletId);
        boolean isSuccess = paymentService.processPayment(order.getOrderId(), order.getTotalAmount());
        if (isSuccess) {
            order.setOrderStatus(OrderStatus.CONFIRM);
            return true;
        } else {
            cancelOrder(order.getOrderId(), false);
            return false;
        }
    }

    private List<Item> prepareItemList(Map<Item, Integer> itemQuantityDetails) {
        List<Item> items = new ArrayList<>();
        for (Map.Entry<Item, Integer> entry: itemQuantityDetails.entrySet()) {
            items.add(entry.getKey());
        }
        return items;
    }

    private Order createOrder(OrderItem orderItem, String userId, String outletId, String idempotencyKey) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderPlaceBy(userId);
        order.setOutletId(outletId);
        order.setOrderStatus(OrderStatus.CREATED);
        double amount = calculateTotalAmount(orderItem.getItemQuantityDetails());
        order.setTotalAmount(amount);
        return order;
    }

    private double calculateTotalAmount(Map<Item, Integer> itemQuantityDetails) {
        // Logic for total price
        return 0;
    }

    boolean cancelOrder(String orderId, boolean isUserTiggered) {
        Order order = orderData.get(orderId);
        if (isUserTiggered) order.setOrderStatus(OrderStatus.CANCELLED);
        else order.setOrderStatus(OrderStatus.FAILED);
        return true;
    }
}
