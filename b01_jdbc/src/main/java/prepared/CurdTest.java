package main.java.prepared;

import main.java.common.User;
import main.java.connection.ConnectionTest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用 PreparedState 的增删改查测试
 *
 * @author zt
 * @since 2023/7/6 19:50
 **/
public class CurdTest {

    private final Connection connection = ConnectionTest.getConnection();

    /**
     * 插入数据
     */
    @Test
    public void testInsert() {
        try {
            // 创建 PreparedStatement 对象
            String sql = "insert into users (name, age, username, password) values (?, ?, ?, ?)";

            // 设置参数
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, "老大");
            preparedStatement.setObject(2, 33);
            preparedStatement.setObject(3, "ld");
            preparedStatement.setObject(4, "123456");

            // 执行 SQL，获取结果
            int row = preparedStatement.executeUpdate();

            // 结果判断
            if (row > 0) {
                System.out.println("添加成功");
                // 获取装主键的结果集对象
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                int id = generatedKeys.getInt(1);
                System.out.println("插入的自增长ID为：" + id);
            } else {
                System.out.println("添加失败");
            }

            // 关闭资源
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据
     */
    @Test
    public void testUpdate() {
        try {
            // 创建 PreparedStatement 对象
            String sql = "update users  set name = ? where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "老二");
            preparedStatement.setInt(2, 2);

            // 执行 SQL，获取结果
            int row = preparedStatement.executeUpdate();

            // 结果判断
            if (row > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }

            // 关闭资源
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     */
    @Test
    public void testQuery() {
        try {
            // 创建 PreparedStatement 对象
            String sql = "select * from users where name = ? or name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "老大");
            preparedStatement.setString(2, "老二");

            // 执行 SQL，获取结果
            ResultSet resultSet = preparedStatement.executeQuery();

            // 解析结果集
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("name:" + name + ";age:" + age + ";username:" + username + ";password:" + password);
            }

            // 关闭资源
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据
     */
    @Test
    public void testDelete() {
        try {
            // 创建 PreparedStatement 对象
            String sql = "delete from users where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 3);

            // 执行 SQL，获取结果
            int row = preparedStatement.executeUpdate();

            // 结果判断
            if (row > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

            // 关闭资源
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置表
     */
    @Test
    public void testTruncate() {
        try {
            // 创建 Statement 对象
            String sql = "truncate table users;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 执行 SQL
            preparedStatement.execute(sql);

            // 关闭资源
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量插入
     */
    @Test
    public void testInsertBatch() {

        try {
            // 设置为不自动提交数据
            connection.setAutoCommit(false);

            // 创建 PreparedStatement 对象
            String sql = "insert into users (name, age, username, password) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < 10000; i++) {
                preparedStatement.setObject(1, "zt" + i);
                preparedStatement.setObject(2, i);
                preparedStatement.setObject(3, "ld" + i);
                preparedStatement.setObject(4, "123456" + i);
                preparedStatement.addBatch();

                // 每1000次执行一次
                if (i % 1000 == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }

            // 提交数据
            connection.commit();

            // 设置回自动提交
            connection.setAutoCommit(true);

            // 关闭资源
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据查询，输出字典列表
     */
    @Test
    public void testQueryToMap() {

        try {
            //  创建 PreparedStatement 对象
            String sql = "select * from users;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 执行 SQL，获取结果
            ResultSet resultSet = preparedStatement.executeQuery();

            // 解析结果集，通过 map 存储，写入 list 中
            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();

                // 获取列的信息
                ResultSetMetaData metaData = resultSet.getMetaData();

                // 获取一共多少列
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {

                    // 获取指定列下角标的值
                    Object object = resultSet.getObject(i);

                    // 获取字段名
                    String columnLabel = metaData.getColumnLabel(i);

                    map.put(columnLabel, object);
                }
                list.add(map);
            }

            System.out.println(list);

            // 关闭资源
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据查询，输出对象列表
     */
    @Test
    public void testQueryToObject() {

        try {
            //  创建 PreparedStatement 对象
            String sql = "select * from users;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 执行 SQL，获取结果
            ResultSet resultSet = preparedStatement.executeQuery();

            // 解析结果集，创建 users 对象，写入 list 中
            ArrayList<User> list = putResult(resultSet, User.class);

            System.out.println(list);

            // 关闭资源
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> ArrayList<T> putResult(ResultSet resultSet, Class<T> obj) {
        try {
            ArrayList<T> arrayList = new ArrayList<>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取总列数
            int count = metaData.getColumnCount();
            while (resultSet.next()) {
                // 创建对象实例
                T newInstance = obj.newInstance();
                for (int i = 1; i <= count; i++) {
                    // 给对象的某个属性赋值
                    String name = metaData.getColumnName(i).toLowerCase();
                    // 改变列名格式成java命名格式
                    name = toJavaField(name);
                    // 首字母大写
                    String substring = name.substring(0, 1);
                    String replace = name.replaceFirst(substring, substring.toUpperCase());
                    // 获取字段类型
                    Class<?> type;
                    try {
                        type = obj.getDeclaredField(name).getType();
                    } catch (NoSuchFieldException e) {
                        // Class对象未定义该字段时,跳过
                        continue;
                    }
                    Method method = obj.getMethod("set" + replace, type);

                    // 判断读取数据的类型
                    if (type.isAssignableFrom(String.class)) {
                        method.invoke(newInstance, resultSet.getString(i));
                    } else if (type.isAssignableFrom(byte.class) || type.isAssignableFrom(Byte.class)) {
                        method.invoke(newInstance, resultSet.getByte(i));// byte 数据类型是8位、有符号的，以二进制补码表示的整数
                    } else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
                        method.invoke(newInstance, resultSet.getShort(i));// short 数据类型是 16 位、有符号的以二进制补码表示的整数
                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                        method.invoke(newInstance, resultSet.getInt(i));// int 数据类型是32位、有符号的以二进制补码表示的整数
                    } else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
                        method.invoke(newInstance, resultSet.getLong(i));// long 数据类型是 64 位、有符号的以二进制补码表示的整数
                    } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
                        method.invoke(newInstance, resultSet.getFloat(i));// float 数据类型是单精度、32位、符合IEEE 754标准的浮点数
                    } else if (type.isAssignableFrom(double.class) || type.isAssignableFrom(Double.class)) {
                        method.invoke(newInstance, resultSet.getDouble(i));// double 数据类型是双精度、64 位、符合IEEE 754标准的浮点数
                    } else if (type.isAssignableFrom(BigDecimal.class)) {
                        method.invoke(newInstance, resultSet.getBigDecimal(i));
                    } else if (type.isAssignableFrom(boolean.class) || type.isAssignableFrom(Boolean.class)) {
                        method.invoke(newInstance, resultSet.getBoolean(i));// boolean数据类型表示一位的信息
                    } else if (type.isAssignableFrom(Date.class)) {
                        method.invoke(newInstance, resultSet.getDate(i));
                    }
                }
                arrayList.add(newInstance);
            }
            return arrayList;

        } catch (InstantiationException | IllegalAccessException | SQLException | SecurityException |
                 NoSuchMethodException | IllegalArgumentException |
                 InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 数据库命名格式转java命名格式
     */
    public String toJavaField(String str) {
        String[] split = str.split("_");
        StringBuilder builder = new StringBuilder();
        builder.append(split[0]);

        if (split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                String string = split[i];
                String substring = string.substring(0, 1);
                split[i] = string.replaceFirst(substring, substring.toUpperCase());
                builder.append(split[i]);
            }
        }

        return builder.toString();
    }
}
