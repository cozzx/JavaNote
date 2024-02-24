package com.cozz.ssm.bean;

import lombok.Data;

/**
 * @author zt
 * @date 2023/8/30
 **/
@Data
public class Employee {

    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private Integer married;
    private String depName;
}
