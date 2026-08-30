package org.practice.design.practice.kafka;

import java.util.HashMap;
import java.util.Map;

public final class Message {

    private final String messageId;
    private final Map<String, Object> data;
    private final long timestamp;

    public Message(String messageId, Map<String, Object> data, long timestamp) {
        this.messageId = messageId;
        this.data = new HashMap<>(data);
        this.timestamp = timestamp;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public Map<String, Object> getData() {
        return new HashMap<>(this.data);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

}
