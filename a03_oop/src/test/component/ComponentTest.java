package test.component;

import org.junit.Test;

import java.util.Date;

/**
 * 测试类的成员
 *
 * @author zt
 * @date 2023/6/27
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

    @Test
    public void test3() {
        MethodExt methodExt = new MethodExt();
        methodExt.test("a", "吧", "c");
        methodExt.test("a", 1, 2);
    }
}
