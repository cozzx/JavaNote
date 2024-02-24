package unittest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * 使用 junit 测试方法时，自动创建 Spring 容器
 * 依赖 org.springframework spring-test
 *
 * @author zt
 * @date 2023/8/21
 **/
// 方式一
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:beans.xml")
// junit4 @RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration("classpath:beans.xml")
// 方式二
@Component
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class SpringJUnit5Test {

    @Autowired
    private User user;

    @Test
    public void testUser() {
        System.out.println(user);
        user.run();
    }
}













































