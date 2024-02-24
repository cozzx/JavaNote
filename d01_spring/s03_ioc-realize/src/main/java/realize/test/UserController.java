package realize.test;

import realize.core.anno.MyBean;
import realize.core.anno.MyDI;

/**
 * @author zt
 * @date 2023/8/21
 **/
@MyBean
public class UserController {

    @MyDI
    private UserService userService;

    public void add() {
        System.out.println("controller........");
        userService.add();
    }
}
