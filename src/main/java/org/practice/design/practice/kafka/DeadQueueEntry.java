package org.practice.design.practice.kafka;

public class DeadQueueEntry {

    private Message message;
    private String topicId;
    private String consumerGroupId;
    private Integer attemptMade;
    private Long timestamp;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getConsumerGroupId() {
        return consumerGroupId;
    }

    public void setConsumerGroupId(String consumerGroupId) {
        this.consumerGroupId = consumerGroupId;
    }

    public Integer getAttemptMade() {
        return attemptMade;
    }

    public void setAttemptMade(Integer attemptMade) {
        this.attemptMade = attemptMade;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
