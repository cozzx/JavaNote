package com.zt.spring6.realize.test;

import com.zt.spring6.realize.core.anno.MyBean;

/**
 * @author zt
 * @since 2023/8/21 19:46
 **/
@MyBean
public class UserDaoImpl implements UserDao {

    @Override
    public void add() {
        System.out.println("[realize]保存成功");
    }
}
