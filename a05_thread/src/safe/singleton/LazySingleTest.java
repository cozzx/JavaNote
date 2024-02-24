package safe.singleton;

import org.junit.Test;

/**
 * 单例模式懒汉式线程安全问题
 *
 * @author zt
 * @date 2023/7/27 20:45
 **/
public class LazySingleTest {
    LazySingle ls1 = null;
    LazySingle ls2 = null;

    @Test
    public void test1() {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                ls1 = LazySingle.getInstance();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                ls2 = LazySingle.getInstance();
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(ls1);
        System.out.println(ls2);
        System.out.println(ls1 == ls2); //不一定
    }
}
