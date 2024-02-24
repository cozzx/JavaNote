package com.cozz.openapi.entity;

import lombok.Data;

/**
 * @author zt
 * @date 2023/9/4
 **/
@Data
public class Employee {

    private Long id;
    private String empName;
    private Integer age;
    private String email;
}
