package com.zt.web.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zt
 * @since 2023/8/29 18:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String userName;
    private String email;
    private Integer age;
    private String role;
}
