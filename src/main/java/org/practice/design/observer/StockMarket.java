package org.practice.design.observer;

import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Subject {

    List<Observer> observerList;
    Integer stockPrice;

    StockMarket() {
        this.observerList = new ArrayList<>();
        this.stockPrice = 0;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    public void updatePrice(int price) {
        this.stockPrice = price;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        String msg = "Stock price is changed to " + this.stockPrice;
        for (Observer observer: this.observerList) {
            observer.update(observer.getName() + " get update from stock market: " + msg);
        }
    }
}
