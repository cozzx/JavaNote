package classload.common;

/**
 * @author zt
 * @date 2023/12/30
 **/
public class User {
    public String username;
    public String password = "123456";
    public String avatar = new String("default.png");
    public static int num = 0;
    public static double rNum = Math.random();

    {
        System.out.println("代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public User() {
        System.out.println("空参构造器");
    }

    public User(String username) {
        this.username = username;
        System.out.println("有参构造器");
    }

    public void sayHello() {
        System.out.println("Hello");
    }
}
