package realize.test;

import realize.core.anno.MyBean;

/**
 * @author zt
 * @date 2023/8/21
 **/
@MyBean
public class UserDaoImpl implements UserDao {

    @Override
    public void add() {
        System.out.println("[realize]保存成功");
    }
}
