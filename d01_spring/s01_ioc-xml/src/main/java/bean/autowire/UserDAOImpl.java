package bean.autowire;

/**
 * @author zt
 * @date 2023/8/20
 **/
public class UserDAOImpl implements UserDAO {
    @Override
    public void saveUser() {
        System.out.println("保存成功");
    }
}
