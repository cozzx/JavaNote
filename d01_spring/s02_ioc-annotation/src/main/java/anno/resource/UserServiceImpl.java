package anno.resource;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 注解Service 用于将业务层的类标识为 Spring 中的 Bean
 *
 * @author zt
 * @date 2023/8/21
 **/
@Service("myUserService")
public class UserServiceImpl implements UserService {

    /**
     * 特殊情况
     * 通过 name 找不到的时候，自然会启动 byType 进行注入，因为 UserDao 接口下有两个实现类导致报错。
     */
    @Resource(name = "userDAOImpl")
    private UserDao userDao;

    @Override
    public void add() {
        userDao.add();
    }
}
