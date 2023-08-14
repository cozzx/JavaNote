package main.java.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 用于被继承的，对数据库进行基本操作的DAO
 *
 * @author zt
 * @since 2023/8/3 22:34
 **/
public abstract class BaseDAO<T> {

    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> type;

    /**
     * 获取 T 的 Class 对象，获取泛型的类型，泛型是在被子类继承时才确定
     */
    public BaseDAO() {
        // 获取子类的类型
        Class clazz = this.getClass();

        // 获取父类的类型
        // getGenericSuperclass() 用来获取当前类的父类的类型
        // ParameterizedType 表示的是带泛型的类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();

        // 具体的泛型类型 getActualTypeArguments 获取具体的泛型的类型
        // 这个方法会返回一个 Type 的数组
        Type[] types = parameterizedType.getActualTypeArguments();

        // 获取具体的泛型的类型
        this.type = (Class<T>) types[0];
    }

    /**
     * 通用的增删改操作
     */
    public int update(Connection conn, String sql, Object... params) {
        int count = 0;
        try {
            count = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 获取一个对象
     */
    public T getBean(Connection conn, String sql, Object... params) {
        T t = null;
        try {
            t = queryRunner.query(conn, sql, new BeanHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 获取所有对象
     */
    public List<T> getBeanList(Connection conn, String sql, Object... params) {
        List<T> list = null;
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取单一值的方法，专门用来执行像 select count(*)...这样的sql语句
     */
    public Object getValue(Connection conn, String sql, Object... params) {
        Object count = null;
        try {
            // 调用 queryRunner 的 query 方法获取一个单一的值
            count = queryRunner.query(conn, sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
