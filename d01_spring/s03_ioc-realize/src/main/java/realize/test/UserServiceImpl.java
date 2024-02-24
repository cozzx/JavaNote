package realize.test;

import realize.core.anno.MyBean;
import realize.core.anno.MyDI;

/**
 * @author zt
 * @date 2023/8/21
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
