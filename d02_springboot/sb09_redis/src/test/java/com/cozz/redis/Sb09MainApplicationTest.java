package com.cozz.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.UUID;

/**
 * @author zt
 * @date 2023/8/31
 **/
@SpringBootTest
public class Sb09MainApplicationTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 字符串
     */
    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("uuid", UUID.randomUUID().toString());
        String uuid = stringRedisTemplate.opsForValue().get("uuid");
        System.out.println("uuid=" + uuid);
    }

    /**
     * 列表
     */
    @Test
    public void testList() {
        String key = "testList";
        stringRedisTemplate.opsForList().leftPush(key, "a");
        stringRedisTemplate.opsForList().leftPush(key, "b");
        stringRedisTemplate.opsForList().leftPush(key, "c");

        String s = stringRedisTemplate.opsForList().rightPop(key);
        Assertions.assertEquals("a", s);
    }

    /**
     * 无序集合 set
     */
    @Test
    public void testSet() {
        String key = "testSet";
        stringRedisTemplate.opsForSet().add(key, "a", "b", "c", "c");
        Boolean member = stringRedisTemplate.opsForSet().isMember(key, "c");
        Assertions.assertTrue(member);
        Boolean member1 = stringRedisTemplate.opsForSet().isMember(key, "d");
        Assertions.assertTrue(member1);
    }

    /**
     * 有序集合 zset
     */
    @Test
    void testZSet() {
        String setName = "testZSet";
        stringRedisTemplate.opsForZSet().add(setName, "张三", 96.00);
        stringRedisTemplate.opsForZSet().add(setName, "李四", 99.00);
        stringRedisTemplate.opsForZSet().add(setName, "王五", 97.10);

        ZSetOperations.TypedTuple<String> popMax = stringRedisTemplate.opsForZSet().popMax(setName);
        String value = popMax.getValue();
        Double score = popMax.getScore();
        System.out.println(value + "==>" + score);
    }

    /**
     * hash 结构
     */
    @Test
    void testHash() {
        String mapName = "testHash";
        stringRedisTemplate.opsForHash().put(mapName, "name", "张三");
        stringRedisTemplate.opsForHash().put(mapName, "age", "18");
        System.out.println(stringRedisTemplate.opsForHash().get(mapName, "name"));
    }
}
