package example;

import common.Circle;
import org.junit.Test;

import java.util.Comparator;

/**
 * 泛型的使用举例
 *
 * @author zt
 * @date 2023/7/5 20:16
 **/
public class GenericTest {

    /**
     * 不使用泛型
     */
    class CircleComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {

            // 需要强制类型转换
            Circle c1 = (Circle) o1;
            Circle c2 = (Circle) o2;
            return Double.compare(c1.getRadius(), c2.getRadius());
        }
    }

    /**
     * 使用泛型
     */
    class CircleComparator1 implements Comparator<Circle> {

        @Override
        public int compare(Circle o1, Circle o2) {

            // 不再需要强制类型转换，代码更简洁
            return Double.compare(o1.getRadius(), o2.getRadius());
        }
    }

    @Test
    public void testNoGeneric() {

        CircleComparator com = new CircleComparator();
        System.out.println(com.compare(new Circle(1), new Circle(2)));

        // 运行时异常：ClassCastException
        System.out.println(com.compare("圆1", "圆2"));
    }

    @Test
    public void testHasGeneric() {

        CircleComparator1 com = new CircleComparator1();
        System.out.println(com.compare(new Circle(1), new Circle(2)));

        // 编译错误，因为"圆1", "圆2"不是Circle类型，是String类型，编译器提前报错，而不是冒着风险在运行时再报错。
        // System.out.println(com.compare("圆1", "圆2"));
    }
}
