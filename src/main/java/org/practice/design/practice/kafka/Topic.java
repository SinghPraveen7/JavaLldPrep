package org.practice.design.practice.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Topic {

    public Topic(String topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.messageLogs = new ArrayBlockingQueue<>(10);
        this.consumerGroupMap = new HashMap<>();
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public BlockingQueue<Message> getMessageLogs() {
        return messageLogs;
    }

    public void setMessageLogs(BlockingQueue<Message> messageLogs) {
        this.messageLogs = messageLogs;
    }

    public Map<String, ConsumerGroup> getConsumerGroupMap() {
        return consumerGroupMap;
    }

    public void setConsumerGroupMap(Map<String, ConsumerGroup> consumerGroupMap) {
        this.consumerGroupMap = consumerGroupMap;
    }

    private String topicId;
    private String topicName;
    private BlockingQueue<Message> messageLogs;
    private Map<String, ConsumerGroup> consumerGroupMap;

    public void publish(Message message) {
        messageLogs.offer(message);
    }

    public Message read() {
        try {
            return messageLogs.take();
        } catch (Exception ex) {
            System.out.println("Exception while reading from topic: " + ex.getMessage());
            return null;
        }
    }

    public void addConsumer(ConsumerGroup consumerGroup) {
        //this.consumerGroupMap.put();
    }

}
