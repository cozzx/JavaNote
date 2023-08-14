package main.java.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用配置文件方式进行数据库连接
 *
 * @author zt
 * @since 2023/7/9 18:06
 **/
public class ConnectionTest {

    private static Connection instance = null;

    private ConnectionTest() {
    }

    public synchronized static Connection getConnection() {
        if (instance == null) {
            // 1.加载配置文件
            InputStream inputStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

            try (inputStream) {
                Properties properties = new Properties();
                properties.load(inputStream);

                // 2.读取配置信息
                String driverClass = properties.getProperty("driverClass");
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");

                // 3.加载驱动
                Class.forName(driverClass);

                // 4.获取连接
                instance = DriverManager.getConnection(url, user, password);

            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
