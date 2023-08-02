package apply;

import common.ColumnTest;
import common.Employee;
import common.TableTest;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author zt
 * @since 2023/6/29 18:30
 **/
public class ApiApplyTest {

    /**
     * 获取注解信息及应用
     */
    @Test
    public void test1() {

        // 获取类注解信息
        Class<Employee> employeeClass = Employee.class;
        TableTest annotation = employeeClass.getAnnotation(TableTest.class);
        String tableName = "";
        if (annotation != null) {
            tableName = annotation.value();
        }
        System.out.println(tableName);

        Field[] declaredFields = employeeClass.getDeclaredFields();
        String[] columns = new String[declaredFields.length];
        int index = 0;
        for (Field declaredField : declaredFields) {
            ColumnTest columnTest = declaredField.getAnnotation(ColumnTest.class);
            if (columnTest != null) {
                columns[index++] = columnTest.columnName();
            }
        }

        String sql = "select ";
        for (int i = 0; i < index; i++) {
            sql += columns[i];
            if (i < index - 1) {
                sql += ",";
            }
        }
        sql += " from " + tableName;
        System.out.println("sql = " + sql);
    }
}
