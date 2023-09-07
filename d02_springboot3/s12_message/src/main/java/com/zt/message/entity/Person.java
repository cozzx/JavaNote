package com.zt.message.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zt
 * @since 2023/9/5 07:37
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private Long id;
    private String name;
    private String email;
}
