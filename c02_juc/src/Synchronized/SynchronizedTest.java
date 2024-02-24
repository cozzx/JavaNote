package Synchronized;

/**
 * @author zt
 * @date 2024/1/20
 **/
public class SynchronizedTest {

    public synchronized static void test1() {
    }

    public synchronized void test2() {
    }

    public void test3() {
        synchronized (SynchronizedTest.class) {
        }
    }

    public void test4() {
        synchronized (this) {
        }
    }

    public void test5() {
        Object o = new Object();
        synchronized (o) {
        }
    }
}
