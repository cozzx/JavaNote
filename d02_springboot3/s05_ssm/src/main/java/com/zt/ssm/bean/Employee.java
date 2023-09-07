package com.zt.ssm.bean;

import lombok.Data;

/**
 * @author zt
 * @since 2023/8/30 19:01
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
