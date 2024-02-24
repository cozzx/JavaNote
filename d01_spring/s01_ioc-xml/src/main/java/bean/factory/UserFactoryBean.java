package bean.factory;

import org.springframework.beans.factory.FactoryBean;

/**
 * userBean 工厂，通过 getObject() 方法的返回值获取 bean
 * 用来隐藏复杂组件创建过程
 *
 * @author zt
 * @date 2023/8/20
 **/
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
