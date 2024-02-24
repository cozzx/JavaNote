package jdbctx.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author zt
 * @date 2023/8/21
 **/
@Component
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class JDBCTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSelectObj() {
        String sql = "select * from employee where id=?";
        Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), 1);
        System.out.println(employee);
    }

    @Test
    public void testSelectList() {
        String sql = "select * from employee";
        List<Employee> employeeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
        System.out.println(employeeList);
    }

    @Test
    public void testSelectValue() {
        String sql = "select count(*) from employee";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

    @Test
    public void testInsert() {
        String sql = "INSERT INTO employee VALUES(NULL,?,?,?,?)";
        Object[] params = {"王炸", 20, 1500, 0};
//        int rows = jdbcTemplate.update(sql, params);
        int rows = jdbcTemplate.update(sql, "王炸", 20, 1500, 0);
        System.out.println(rows);
    }

    @Test
    public void testUpdate() {
        String sql = "update employee set age=? where id=?";
        int rows = jdbcTemplate.update(sql, "30", 3);
        System.out.println(rows);
    }

    @Test
    public void testDelete() {
        String sql = "delete from employee where id=?";
        int rows = jdbcTemplate.update(sql, 4);
        System.out.println(rows);
    }
}
