package main.java.statement;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;

/**
 * 简单的流程
 *
 * @author zt
 * @date 2023/7/5
 **/
public class SimpleTest {

    @Test
    public void test1() throws SQLException {

        // 1. 注册驱动 DriverManager
        DriverManager.registerDriver(new Driver());

        // 2. 获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "zt", "admin123");

        // 3. 创建 statement
        Statement statement = connection.createStatement();

        // 4. 执行 SQL，获取返回结果
        ResultSet resultSet = statement.executeQuery("select * from users");

        // 5. 进行结果解析
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("id:" + id + ";name:" + name);
        }

        // 6. 关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
