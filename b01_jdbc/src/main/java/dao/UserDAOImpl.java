package main.java.dao;

import main.java.common.User;

import java.sql.Connection;

/**
 * @author zt
 * @date 2023/8/4
 **/
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(Connection conn, User user) {
        User bean;
        String sql = "select * from users where username = ? and password = ?";
        bean = getBean(conn, sql, user.getUsername(), user.getPassword());
        return bean;
    }

    @Override
    public boolean checkUsername(Connection conn, User user) {
        User bean;
        String sql = "select * from users where username = ?";
        bean = getBean(conn, sql, user.getUsername());
        return bean != null;
    }

    @Override
    public void saveUser(Connection conn, User user) {
        String sql = "insert into users(user, age, username, password) values(?, ?, ?, ?)";
        update(conn, sql, user.getName(), user.getAge(), user.getUsername(), user.getPassword());
    }
}
