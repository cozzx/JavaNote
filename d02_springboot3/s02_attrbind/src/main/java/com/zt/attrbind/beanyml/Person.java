package com.zt.attrbind.beanyml;

import com.zt.attrbind.bean.Dog;
import com.zt.attrbind.bean.Pig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zt
 * @since 2023/8/28 20:45
 **/
@Component
@ConfigurationProperties(prefix = "person") // 和配置文件person前缀的所有配置进行绑定
@Data // 自动生成JavaBean属性的getter/setter
//@NoArgsConstructor //自动生成无参构造器
//@AllArgsConstructor //自动生成全参构造器
public class Person {
    private String name;
    private Integer age;
    private Date birthDay;
    private Boolean like;
    private Child child;
    private List<Dog> dogs;
    private Map<String, Pig> pigs;
}
