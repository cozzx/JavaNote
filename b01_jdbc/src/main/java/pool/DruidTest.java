package main.java.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * druid 连接池
 *
 * @author zt
 * @since 2023/7/7 07:49
 **/
public class DruidTest {

    private static Connection connection;

    private DruidTest() {
    }

    public synchronized static Connection getConnection1() {
        if (connection == null) {
            try {
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                druidDataSource.setUrl("jdbc:mysql:///test");
                druidDataSource.setUsername("zt");
                druidDataSource.setPassword("admin123");
                connection = druidDataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public synchronized static Connection getConnection2() {
        if (connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(DruidTest.class.getClassLoader().getResourceAsStream("druid.properties"));
                DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
                connection = dataSource.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
