package org.practice.design.practice.multi_outlet_food_order;

public class Order {
    String orderId;
    String orderPlaceBy;
    OrderItem item;
    double totalAmount;
    String outletId;
    OrderStatus orderStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderPlaceBy() {
        return orderPlaceBy;
    }

    public void setOrderPlaceBy(String orderPlaceBy) {
        this.orderPlaceBy = orderPlaceBy;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
