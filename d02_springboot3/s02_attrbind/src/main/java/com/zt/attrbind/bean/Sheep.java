package com.zt.attrbind.bean;

/**
 * 属性绑定方式二
 * 注解 @ConfigurationProperties 配置在配置文件中
 *
 * @author zt
 * @since 2023/8/28 19:58
 **/
public class Sheep {

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
