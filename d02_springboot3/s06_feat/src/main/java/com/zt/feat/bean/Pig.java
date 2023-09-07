package com.zt.feat.bean;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @since 2023/8/22 22:34
 **/
@Component
@Profile({"prod"})
public class Pig {

    private Long id;

    private String name;

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

    @Override
    public String toString() {
        return "Pig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
