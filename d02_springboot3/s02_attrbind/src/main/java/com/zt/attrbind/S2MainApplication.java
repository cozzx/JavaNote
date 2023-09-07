package com.zt.attrbind;

import com.zt.attrbind.bean.Dog;
import com.zt.attrbind.bean.Pig;
import com.zt.attrbind.bean.Sheep;
import com.zt.attrbind.beanyml.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zt
 * @since 2023/8/28 19:56
 **/
@SpringBootApplication
public class S2MainApplication {
    public static void main(String[] args) {
        var ioc = SpringApplication.run(S2MainApplication.class, args);

        // 测试属性绑定
        Pig pig = ioc.getBean(Pig.class);
        System.out.println(pig);
        Sheep sheep = ioc.getBean(Sheep.class);
        System.out.println(sheep);
        Dog dog = ioc.getBean(Dog.class);
        System.out.println(dog);

        // 测试properties和yml配置文件，properties优先级高
        Person person = ioc.getBean(Person.class);
        System.out.println("person：" + person);
        System.out.println("==== 用|表示大文本，会保留格式");
        String s1 = person.getChild().getText().get(2);
        System.out.println(s1);
        System.out.println("==== 用>表示大文本，会压缩换行变成 空格");
        var s2 = person.getChild().getText().get(3);
        System.out.println(s2);
    }
}
