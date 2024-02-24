package com.cozz.redis.controller;

import com.cozz.redis.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zt
 * @date 2023/8/31
 **/
@RestController
public class RedisTestController {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @GetMapping("/person/save")
    public String savePerson() {
        Person person = new Person(1L, "张三", 18, new Date());
        // 序列化： 对象转为字符串方式
        redisTemplate.opsForValue().set("person", person);
        return "ok";
    }

    @GetMapping("/person/get")
    public Person getPerson() {
        Person person = (Person) redisTemplate.opsForValue().get("person");
        return person;
    }
}
