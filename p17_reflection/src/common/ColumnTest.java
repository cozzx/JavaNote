package common;

import java.lang.annotation.*;

/**
 * @author zt
 * @since 2023/6/29 15:15
 **/
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnTest {

    String columnName();
    String columnType();

}
