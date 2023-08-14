package main.java.statement;

import org.junit.Test;

import java.sql.*;
import java.util.Scanner;

/**
 * 通过模拟登录，进行 JDBC 使用步骤的详细解析
 *
 * @author zt
 * @since 2023/7/6 07:18
 **/
public class Login1Test {

    @Test
    public void test1() {

        // 获取登录用户名密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号：");
        String username = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();

        try {

            // 1、注册驱动，使用反射机制，执行 Driver 类静态代码块中注册驱动的方法，这样在不改变代码的情况下，可修改配置文件切换数据库类型
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2、获取连接，URL 使用了简写方式
            Connection connection = DriverManager.getConnection("jdbc:mysql:///test", "root", "admin123./");

            // 3、创建 statement 对象
            Statement statement = connection.createStatement();

            // 4. 执行 SQL，获取返回结果，拼接SQL有被注入的风险
            String sql = "select * from users where username = '" + username + "' and password='" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            // 5. 进行结果解析
            if (resultSet.next()) {
                // 只要有数据代表登录成功
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }

            // 6. 关闭资源
            resultSet.close();
            statement.close();
            connection.close();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
