package test.inner;

import org.junit.Test;

/**
 * @author zt
 * @since 2023/8/17 18:23
 **/
public class InnerTest {

    @Test
    public void test() {
        // 1. 创建Person的静态的成员内部类的实例
        People.Dog dog = new People.Dog();
        dog.eat();
        // 2. 创建Person的非静态的成员内部类的实例
//        Person.Bird bird = new Person.Bird(); // 报错

        People p1 = new People();
        People.Bird bird = p1.new Bird(); // 正确的
        bird.eat();
        bird.show("黄鹂");
        bird.show1();
    }
}
