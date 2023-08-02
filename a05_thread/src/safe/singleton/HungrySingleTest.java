package safe.singleton;

import org.junit.Test;

/**
 * 单例模式饿汉式没有线程安全问题
 *
 * @author zt
 * @since 2023/7/27 20:45
 **/
public class HungrySingleTest {
    HungrySingle hs1 = null;
    HungrySingle hs2 = null;

    @Test
    public void test1() {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                hs1 = HungrySingle.getInstance();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                hs2 = HungrySingle.getInstance();
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

        System.out.println(hs1);
        System.out.println(hs2);
        System.out.println(hs1 == hs2); //true
    }
}
