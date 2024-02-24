package bean.autowire;

/**
 * @author zt
 * @date 2023/8/20
 **/
public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void saveUser() {
        userService.saveUser();
    }
}
