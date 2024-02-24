package com.cozz.attrbind.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zt
 * @date 2023/8/28
 **/
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {
    private String name;
    private Integer age;
    private Date birthDay;
    private Boolean married;
    private Cat cat;
    private List<Dog> dogs;
    private Map<String, Pig> pigs;
    private List<String> text;
}
