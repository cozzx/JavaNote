package bean.autowire;

/**
 * @author zt
 * @date 2023/8/20
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
