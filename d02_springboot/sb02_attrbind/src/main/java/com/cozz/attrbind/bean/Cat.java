package com.cozz.attrbind.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性绑定方式一: 注解 @ConfigurationProperties 配置在类中
 *
 * @author zt
 * @date 2023/8/28
 **/
@Component
@ConfigurationProperties(prefix = "cat")
public class Cat {
    Long id;
    String name;
    Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
