package org.practice.design.factory.simple;

public class PaymentFactory {

    public static Payment getPayment(String type) {
        switch (type) {
            case "UPI":
                return new UPIPayment();
            case "CARD":
                return new CardPayment();
        }
        return null;
    }

}
