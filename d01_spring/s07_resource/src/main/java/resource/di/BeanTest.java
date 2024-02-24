package resource.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试依赖注入
 *
 * @author zt
 * @date 2023/8/22
 **/
public class BeanTest {
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-di.xml");
        ResourceBean resourceBean = applicationContext.getBean("resourceBean", ResourceBean.class);
        resourceBean.parse();
    }
}
