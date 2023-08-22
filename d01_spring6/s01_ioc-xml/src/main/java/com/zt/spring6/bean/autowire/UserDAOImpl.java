package com.zt.spring6.bean.autowire;

/**
 * @author zt
 * @since 2023/8/20 13:37
 **/
public class UserDAOImpl implements UserDAO {
    @Override
    public void saveUser() {
        System.out.println("保存成功");
    }
}
