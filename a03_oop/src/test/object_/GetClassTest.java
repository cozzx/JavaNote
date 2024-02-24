package test.object_;

import org.junit.Test;
import test.component.People;

/**
 * Object 类中的 getClass()
 *
 * @author zt
 * @date 2024/1/11
 **/
public class GetClassTest {

    @Test
    public void test() {
        Object o = new People();
        System.out.println(o.getClass());
    }
}
