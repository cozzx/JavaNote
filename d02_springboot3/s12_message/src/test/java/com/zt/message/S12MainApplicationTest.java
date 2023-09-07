package com.zt.message;

import com.zt.message.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

/**
 * @author zt
 * @since 2023/9/5 07:26
 **/
@SpringBootTest
public class S12MainApplicationTest {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Test
    public void sendStr() {
        StopWatch stopWatch = new StopWatch();
        CompletableFuture[] futures = new CompletableFuture[10000];
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            CompletableFuture send = kafkaTemplate.send("news", "test" + i, "测试" + i);
            futures[i] = send;
        }
        CompletableFuture.allOf(futures).join();
        stopWatch.stop();

        long millis = stopWatch.getTotalTimeMillis();
        System.out.println("10000消息发送完成：ms时间：" + millis);
    }

    @Test
    void sendObj() {
        CompletableFuture future = kafkaTemplate.send("news", "person", new Person(1L, "张三", "hjaha@qq.com"));

        future.join();
        System.out.println("消息发送成功...");
    }
}
