package com.zt.spring6.bean.autowire;

/**
 * @author zt
 * @since 2023/8/20 13:37
 **/
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void saveUser() {
        userDAO.saveUser();
    }
}
