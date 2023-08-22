package com.zt.spring6.realize.test;

import com.zt.spring6.realize.core.anno.MyBean;
import com.zt.spring6.realize.core.anno.MyDI;

/**
 * @author zt
 * @since 2023/8/21 19:46
 **/
@MyBean
public class UserServiceImpl implements UserService {

    @MyDI
    private UserDao userDao;

    @Override
    public void add() {
        userDao.add();
    }
}
