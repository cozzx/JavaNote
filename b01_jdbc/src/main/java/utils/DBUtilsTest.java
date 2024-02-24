package main.java.utils;

import main.java.common.User;
import main.java.pool.DruidTest;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Apache JDBC 工具类
 *
 * @author zt
 * @date 2023/8/4
 **/
public class DBUtilsTest {

    /**
     * 查询单条数据
     * 使用 ResultSetHandler 的实现类：BeanHandler
     */
    @Test
    public void test1() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DruidTest.getConnection2();
        String sql = "select * from users where id = ?;";
        BeanHandler<User> userBeanHandler = new BeanHandler<>(User.class);
        try {
            User user = queryRunner.query(connection, sql, userBeanHandler, 1);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询多条数据
     * 使用 ResultSetHandler 的实现类：BeanListHandler
     */
    @Test
    public void test2() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DruidTest.getConnection2();
        String sql = "select * from users where name = ? or name = ?;";
        BeanListHandler<User> userBeanListHandler = new BeanListHandler<>(User.class);
        try {
            List<User> userList = queryRunner.query(connection, sql, userBeanListHandler, "老大", "老二");
            System.out.println(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 自定义ResultSetHandler的实现类
     */
    @Test
    public void test3() {
        try {
            QueryRunner queryRunner = new QueryRunner();
            Connection connection = DruidTest.getConnection2();
            String sql = "select * from users where name = ? or name = ?;";
            ResultSetHandler<List<User>> handler = resultSet -> {
                List<User> userList = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    User user = new User(id, name, age, username, password);
                    userList.add(user);
                }
                return userList;
            };
            List<User> userList = queryRunner.query(connection, sql, handler, "老大", "老二");
            System.out.println(userList);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 如何查询类似于最大的，最小的，平均的，总和，个数相关的数据，
     * 使用ScalarHandler
     */
    @Test
    public void test4() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DruidTest.getConnection2();

        try (connection) {
            // 测试一：
            String sql1 = "select count(*) from users where age < ?";
            ScalarHandler scalarHandler1 = new ScalarHandler();
            long count = (long) queryRunner.query(connection, sql1, scalarHandler1, 30);
            System.out.println(count);

            // 测试二：
            String sql2 = "select max(age) from users";
            ScalarHandler scalarHandler2 = new ScalarHandler();
            int age = (int) queryRunner.query(connection, sql2, scalarHandler2);
            System.out.println(age);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 插入数据
     */
    @Test
    public void test5() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DruidTest.getConnection2();

        try (connection) {
            String sql = "insert into users(name, age, username, password)values(?, ?, ?, ?)";
            int count = queryRunner.update(connection, sql, "老三", 11, "zt3", "123123");
            System.out.println("添加了" + count + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 更新数据
     */
    @Test
    public void test6() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DruidTest.getConnection2();

        try (connection) {
            String sql = "update users set username = ? where name = ?";
            int count = queryRunner.update(connection, sql, "ls", "老三");
            System.out.println("修改了" + count + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 更新数据
     */
    @Test
    public void test7() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DruidTest.getConnection2();

        try (connection) {
            String sql = "delete from users where name = ?";
            int count = queryRunner.update(connection, sql, "老三");
            System.out.println("删除了" + count + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
