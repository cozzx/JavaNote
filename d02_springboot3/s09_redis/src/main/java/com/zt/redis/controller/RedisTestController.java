package com.zt.redis.controller;

import com.zt.redis.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zt
 * @since 2023/8/31 20:57
 **/
@RestController
public class RedisTestController {

    /**
     * 如果给redis中保存数据会使用默认的序列化机制，导致redis中保存的对象不可视
     * 为了后来系统的兼容性，应该所有对象都是以json的方式进行保存
     * 在config配置中配置redis序列化工具
     */
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
