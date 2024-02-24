package com.cozz.attrbind.bean;

/**
 * 属性绑定方式二: 注解 @ConfigurationProperties 配置在配置文件中
 * 配置文件: config.AppConfig
 *
 * @author zt
 * @date 2023/8/28
 **/
public class Dog {
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
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
