package test.enum_;

import org.junit.Test;

/**
 * @author zt
 * @since 2023/8/17 20:07
 **/
public class EnumTest {

    @Test
    public void test() {
        SeasonEnum seasonEnum = SeasonEnum.SPRING;
        switch (seasonEnum) {
            case SPRING -> System.out.println("春");
            case SUMMER -> System.out.println("夏");
            case AUTUMN -> System.out.println("秋");
            case WINTER -> System.out.println("冬");
        }
    }
}
