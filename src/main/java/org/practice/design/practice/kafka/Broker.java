package org.practice.design.practice.kafka;

import java.util.Map;

public class Broker {

    private Map<String, Topic> topics;

    public void addTopic(Topic topic) {
        this.topics.put(topic.getTopicId(), topic);
    }

    public void subscribe(Topic topic, ConsumerGroup consumerGroup) {

    }

    public void unSubscribe(Topic topic, ConsumerGroup consumerGroup) {

    }

}
