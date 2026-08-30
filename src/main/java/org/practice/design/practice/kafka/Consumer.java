package org.practice.design.practice.kafka;


public class Consumer {
    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    private String consumerId;

    public boolean consumeMessage(Message message) {
        try {
            System.out.println("Consuming Message: " + message.getMessageId());
            return true;
        } catch (Exception ex) {
            System.out.println("Error while consuming Message: " + ex.getMessage());
            return false;
        }
    }
}
