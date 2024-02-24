package com.cozz.feat.bean;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @date 2023/8/22
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
