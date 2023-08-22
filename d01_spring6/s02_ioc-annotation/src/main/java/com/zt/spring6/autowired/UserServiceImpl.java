package com.zt.spring6.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 注解Service 用于将业务层的类标识为 Spring 中的 Bean
 *
 * @author zt
 * @since 2023/8/21 19:46
 **/
@Service
public class UserServiceImpl implements UserService {

    /**
     * 方式一：属性注入
     * Autowired 注解默认根据类型装配 byType，如果想根据名称装配，需要配合 @Qualifier 注解一起用
     */
    @Autowired
    @Qualifier("userDaoImpl") // 当有两个实现类时，单使用 @Autowired 会报错，需要搭配 @Qualifier 使用，按名称进行装配
    private UserDao userDao;


    /**
     * 方式二：set注入
     */
//    private UserDao userDao;
//
//    @Autowired
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }


    /**
     * 方式三：构造方法注入
     */
//    private UserDao userDao;
//
//    // 当有参数的构造方法只有一个时，@Autowired 注解可以省略。
//    @Autowired
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }


    /**
     * 方式四：形参注入
     */
//    private UserDao userDao;
//
//    public UserServiceImpl(@Autowired UserDao userDao) {
//        this.userDao = userDao;
//    }
    @Override
    public void add() {
        userDao.add();
    }
}
