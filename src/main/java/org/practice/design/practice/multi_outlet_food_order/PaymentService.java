package org.practice.design.practice.multi_outlet_food_order;

public interface PaymentService {

    boolean processPayment(String orderId, double amount);

}
