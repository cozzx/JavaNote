package common;

import java.lang.annotation.*;

/**
 * @author zt
 * @since 2023/6/29 15:13
 **/
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableTest {

    String value();
}
