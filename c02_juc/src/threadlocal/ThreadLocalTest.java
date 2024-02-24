package threadlocal;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

/**
 * ThreadLocal 线程局部变量 测试
 *
 * @author zt
 * @date 2024/1/18
 **/
public class ThreadLocalTest {

    /**
     * 5个销售卖房子，按照总销售额进行统计
     */
    class House1 {
        LongAdder saleCount = new LongAdder();

        public void saleHouse() {
            saleCount.increment();
        }
    }

    @Test
    public void test1() {
        House1 house = new House1();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                System.out.println(size);
                for (int j = 1; j <= size; j++) {
                    house.saleHouse();
                }
            }, String.valueOf(i)).start();
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(STR."共计卖出 \{house.saleCount} 套");
    }

    /**
     * 5个销售卖房子，按照各个销售的销售额进行统计
     */
    class House2 {
        LongAdder saleCount = new LongAdder();

        public void saleHouse() {
            saleCount.increment();
        }

        ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);

        public void saleVolumeByThreadLocal() {
            saleVolume.set(saleVolume.get() + 1);
        }
    }

    @Test
    public void test2() {
        House2 house = new House2();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                try {
                    for (int j = 0; j < size; j++) {
                        house.saleHouse(); // 用于比较总数
                        house.saleVolumeByThreadLocal();
                    }
                    System.out.println(STR."\{Thread.currentThread().getName()}号销售卖出：\{house.saleVolume.get()}");
                } finally {
                    house.saleVolume.remove();
                }
            }, String.valueOf(i)).start();
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(STR."共计卖出 \{house.saleCount} 套");
    }
}
