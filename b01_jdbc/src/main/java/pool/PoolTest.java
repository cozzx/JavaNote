package main.java.pool;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接池测试
 *
 * @author zt
 * @date 2023/8/4
 **/
public class PoolTest {

    /**
     * C3P0 连接池测试
     */
    @Test
    public void test1() {

        try {
            Connection connection = C3P0Test.getConnection2();
            String sql = "select * from users where name = ? or name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "老大");
            preparedStatement.setString(2, "老二");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("name:" + name + ";age:" + age + ";username:" + username + ";password:" + password);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * druid 连接池测试
     */
    @Test
    public void test2() {

        try {
            Connection connection = DruidTest.getConnection2();
            String sql = "select * from users where name = ? or name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "老大");
            preparedStatement.setString(2, "老二");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("name:" + name + ";age:" + age + ";username:" + username + ";password:" + password);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
