package test.component;

import org.junit.Test;

import java.util.Date;

/**
 * @author zt
 * @since 2023/6/27 18:21
 **/
public class ComponentTest {

    @Test
    public void test1() {
        People p1 = new People();
        p1.eat();
        System.out.println(People.limbs);
    }

    @Test
    public void test2() {
        People p2 = new People("tom", 0, '男', new Date());
        p2.sleep();
        People.getLimbs();
    }
}
