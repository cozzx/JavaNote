package com.zt.attrbind.beanyml;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zt
 * @since 2023/8/28 20:46
 **/
@Data
public class Child {
    private String name;
    private Integer age;
    private Date birthDay;
    private List<String> text;
}
