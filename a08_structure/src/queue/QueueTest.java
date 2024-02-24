package queue;

import org.junit.Test;

/**
 * 队列测试
 *
 * @author zt
 * @date 2023/7/4 07:55
 **/
public class QueueTest {

    /**
     * 自定义队列测试
     */
    @Test
    public void test1() {
        QueueCustom queueCustom = new QueueCustom(10);
        queueCustom.add(1);
        queueCustom.add(2);
        queueCustom.add(3);

        System.out.println(queueCustom);
        queueCustom.poll();
        System.out.println(queueCustom);
    }
}
