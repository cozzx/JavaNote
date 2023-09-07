package com.zt.openapi.entity;

import lombok.Data;

/**
 * @author zt
 * @since 2023/9/4 19:56
 **/
@Data
public class Employee {

    private Long id;
    private String empName;
    private Integer age;
    private String email;
}
