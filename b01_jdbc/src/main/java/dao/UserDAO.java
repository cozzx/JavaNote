package main.java.dao;

import main.java.common.User;

import java.sql.Connection;

/**
 * @author zt
 * @date 2023/8/4
 **/
public interface UserDAO {

    /**
     * 根据User对象中的用户名和密码从数据库中获取一条记录
     */
    User getUser(Connection conn, User user);

    /**
     * 根据User对象中的用户名从数据库中获取一条记录
     */
    boolean checkUsername(Connection conn, User user);

    /**
     * 向数据库中插入User对象
     */
    void saveUser(Connection conn, User user);
}
