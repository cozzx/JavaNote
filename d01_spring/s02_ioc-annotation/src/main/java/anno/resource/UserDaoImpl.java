package anno.resource;

import org.springframework.stereotype.Repository;

/**
 * 注解 Repository 用于将数据访问层（Dao 层）的类标识为 Spring 中的 Bean
 *
 * @author zt
 * @date 2023/8/21
 **/
@Repository("userDAOImpl")
public class UserDaoImpl implements UserDao {

    @Override
    public void add() {
        System.out.println("[resource]保存成功");
    }
}
