package realize.test;

import realize.core.bean.AnnoAppContext;
import org.junit.jupiter.api.Test;

/**
 * @author zt
 * @date 2023/8/21
 **/
public class RealizeTest {

    @Test
    public void test1() {
        AnnoAppContext appContext = new AnnoAppContext("realize.test");
        UserController userController = (UserController) appContext.getBean(UserController.class);
        userController.add();
    }
}
