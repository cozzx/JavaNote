package test.interface_;

/**
 * @author zt
 * @date 2023/8/17
 **/
public interface Usb {

    // 静态常量, `public static final` 可以省略
    long MAX_SPEED = 500 * 1024 * 1024;

    // 抽象方法, `public abstract` 可以省略
    void in();

    void out();

    // 默认方法, public 可以省略
    default void start() {
        System.out.println("开始");
    }

    default void stop() {
        System.out.println("结束");
    }

    // 静态方法, public 可以省略
    static void show() {
        System.out.println("USB 3.0可以同步全速地进行读写操作");
    }
}
