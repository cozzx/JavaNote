package com.zt.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zt
 * @since 2023/8/31 20:58
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private Date birthDay;
}
