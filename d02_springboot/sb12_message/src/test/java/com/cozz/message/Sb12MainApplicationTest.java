package com.cozz.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

/**
 * @author zt
 * @date 2023/9/5
 **/
@SpringBootTest
public class Sb12MainApplicationTest {

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
}
