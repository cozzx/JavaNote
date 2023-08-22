package com.zt.spring6.realize.test;

import com.zt.spring6.realize.core.bean.AnnoAppContext;
import org.junit.jupiter.api.Test;

/**
 * @author zt
 * @since 2023/8/21 21:51
 **/
public class RealizeTest {

    @Test
    public void test1() {
        AnnoAppContext appContext = new AnnoAppContext("com.zt.spring6.realize.test");
        UserController userController = (UserController) appContext.getBean(UserController.class);
        userController.add();
    }
}
