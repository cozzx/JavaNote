package com.zt.spring6.autowired;

import org.springframework.stereotype.Repository;

/**
 * 注解 Repository 用于将数据访问层（Dao 层）的类标识为 Spring 中的 Bean
 *
 * @author zt
 * @since 2023/8/21 19:46
 **/
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void add() {
        System.out.println("[autowired]保存成功");
    }
}
