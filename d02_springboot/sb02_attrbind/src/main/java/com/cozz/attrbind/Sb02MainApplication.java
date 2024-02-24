package com.cozz.attrbind;

import com.cozz.attrbind.bean.Cat;
import com.cozz.attrbind.bean.Dog;
import com.cozz.attrbind.bean.Person;
import com.cozz.attrbind.bean.Pig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zt
 * @date 2023/8/28
 **/
@SpringBootApplication
public class Sb02MainApplication {
    public static void main(String[] args) {
        var ioc = SpringApplication.run(Sb02MainApplication.class, args);

        // 测试属性绑定
        Cat cat = ioc.getBean(Cat.class);
        System.out.println("cat = " + cat);
        Dog dog = ioc.getBean(Dog.class);
        System.out.println("dog = " + dog);
        Pig pig = ioc.getBean(Pig.class);
        System.out.println("pig = " + pig);

        // 测试properties和yml配置文件，properties优先级高
        Person person = ioc.getBean(Person.class);
        System.out.println("person：" + person);
        System.out.println("==== 用|表示大文本，会保留格式");
        String s1 = person.getText().get(2);
        System.out.println(s1);
        System.out.println("==== 用>表示大文本，会压缩换行变成 空格");
        var s2 = person.getText().get(3);
        System.out.println(s2);
    }
}
