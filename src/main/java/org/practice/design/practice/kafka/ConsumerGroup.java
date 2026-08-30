package org.practice.design.practice.kafka;

import java.util.List;
import java.util.concurrent.BlockingQueue;

//ConsumerGroupId
//Topic
//List<Consumer>
//BlockingQueue<DeadQueueEntry>DeadQueue
//dispatcherThread
//startDispatching()
//dispatchMessage(Message) // strategy
//maximumRetry
//offset
//handleRetry(message)
//addConsumers(Consumer)
//pushToGroup(Message)
//pushToDeadQueue(Message)
public class ConsumerGroup {

    private String consumerGroupId;
    private Topic topic;
    private List<Consumer> consumers;
    private BlockingQueue<DeadQueueEntry> deadLetterQueue;
    private Integer maxRetry;
    private Integer offset;



}
