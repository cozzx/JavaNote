package com.zt.spring6.resource;

import org.springframework.stereotype.Repository;

/**
 * 新增一个实现类，程序错误提示不能装配，UserDao这个Bean的数量等于2
 *
 * @author zt
 * @since 2023/8/21 19:46
 **/
@Repository
public class UserDaoRedisImpl implements UserDao {

    @Override
    public void add() {
        System.out.println("Redis Dao层执行结束");
    }
}
