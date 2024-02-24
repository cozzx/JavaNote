package com.cozz.message.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @date 2023/9/5
 **/
@Component
public class MyNewsTopicListener {

    /**
     * 默认的监听是从消息队列最后一个消息开始拿。新消息才能拿到
     */
    @KafkaListener(topics = "news", groupId = "n1")
    public void news(ConsumerRecord record) {
        // 获取消息的各种详细信息
        Object key = record.key();
        Object value = record.value();
        System.out.println(STR."收到消息：key【\{key}】 value【\{value}】");
    }

    /**
     * 拿到以前的完整消息
     */
    @KafkaListener(groupId = "n2", topicPartitions = {
            @TopicPartition(topic = "news", partitionOffsets = {
                    @PartitionOffset(partition = "0", initialOffset = "0")
            })
    })
    public void allNews(ConsumerRecord record) {
        Object key = record.key();
        Object value = record.value();
        System.out.println(STR."全部消息：key【\{key}】 value【\{value}】");
    }
}
