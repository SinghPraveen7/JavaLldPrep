package org.practice.design.practice.kafka;

import java.util.Map;

public class Producer {

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    private String producerId;

    public void publishMessage(Topic topic, Message message) {

    }

}
