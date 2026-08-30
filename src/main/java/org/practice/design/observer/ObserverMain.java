package org.practice.design.observer;

/**
 * The Observer Pattern is a behavioral design pattern that establishes a one-to-many relationship between a subject and multiple observers.
 * When the subject's state changes, all registered observers are notified automatically. The pattern consists of a Subject,
 * which maintains a list of observers and sends notifications, and Observer implementations that react to updates.
 * A common example is a stock market application where users subscribe to stock price changes.
 */
public class ObserverMain {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        PaytmUser paytmUser = new PaytmUser("Paytm");
        PhonepeUser phonepeUser = new PhonepeUser("Phonepe");
        stockMarket.addObserver(phonepeUser);
        stockMarket.addObserver(paytmUser);
        stockMarket.updatePrice(100);
        stockMarket.updatePrice(120);
    }
}
