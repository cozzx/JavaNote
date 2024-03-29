package common;

import java.lang.annotation.*;

/**
 * @author zt
 * @date 2023/6/29 18:15
 **/
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnTest {

    String columnName();
    String columnType();

}
