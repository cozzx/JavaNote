package com.zt.attrbind.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性绑定方式三
 * 通过配置文件标注 @EnableConfigurationProperties(Dog.class) 注解
 *
 * @author zt
 * @since 2023/8/28 19:58
 **/
@ConfigurationProperties(prefix = "dog")
public class Dog {

    private Long id;

    private String name;

    private Integer age;

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
