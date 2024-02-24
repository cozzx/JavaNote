package com.cozz.attrbind.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性绑定方式三: 通过配置文件标注 @EnableConfigurationProperties(Pig.class) 注解
 * 配置文件: config.AppConfig
 *
 * @author zt
 * @date 2023/8/28
 **/
@ConfigurationProperties(prefix = "pig")
public class Pig {
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
        return "Pig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
