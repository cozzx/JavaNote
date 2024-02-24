package main.java.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * C3P0 数据库连接池
 *
 * @author zt
 * @date 2023/8/3
 **/
public class C3P0Test {

    private static Connection connection;

    private C3P0Test() {
    }

    public synchronized static Connection getConnection() {
        if (connection == null) {
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            try {
                comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
                comboPooledDataSource.setJdbcUrl("jdbc:mysql:///test");
                comboPooledDataSource.setUser("zt");
                comboPooledDataSource.setPassword("admin123");
                comboPooledDataSource.setMaxPoolSize(50);
                connection = comboPooledDataSource.getConnection();
            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public synchronized static Connection getConnection2() {
        if (connection == null) {
            DataSource dataSource = new ComboPooledDataSource("c3p0");
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
