package main.java.transaction;

import main.java.pool.DruidTest;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务操作
 *
 * @author zt
 * @since 2023/8/3 21:00
 **/
public class TransactionTest {

    @Test
    public void test1() {

        // 获取连接池
        Connection connection = DruidTest.getConnection2();

        try {
            // 设置为不自动提交数据
            connection.setAutoCommit(false);

            // 数据库操作
            String sql = "update users set balance = balance - 100 where name = ?;";
            update(connection, sql, "老大");

            // 模拟网络异常
            System.out.println(10 / 0);

            String sql2 = "update users set balance = balance + 100 where name = ?;";
            update(connection, sql2, "老二");
            // 提交
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 回滚
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    // 设置回自动提交数据
                    connection.setAutoCommit(true);
                    // 关闭连接
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void update(Connection connection, String sql, Object... args) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
