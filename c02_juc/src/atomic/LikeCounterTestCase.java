package atomic;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zt
 * @date 2024/1/18
 **/
public class LikeCounterTestCase {

    int counter = 0;

    public synchronized void clickBySynchronized() {
        counter++;
    }

    AtomicLong atomicLong = new AtomicLong(0);

    public void clickByAtomicLong() {
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();

    public void clickByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);

    public void clickByLongAccumulator() {
        longAccumulator.accumulate(1);
    }

    public static final int CYCLE_NUM = 100000;
    public static final int THREAD_NUM = 50;
    CountDownLatch countDownLatch;
    long startTime;
    long endTime;

    @Test
    public void test1() throws InterruptedException {
        startTime = System.currentTimeMillis();
        countDownLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < CYCLE_NUM; j++) {
                        clickBySynchronized();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, "t").start();
        }
        countDownLatch.await();
        endTime = System.currentTimeMillis();
        System.out.println(STR."点赞次数为: \{counter}; 使用 synchronized 花费时间: \{endTime - startTime}ms");

        startTime = System.currentTimeMillis();
        countDownLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < CYCLE_NUM; j++) {
                        clickByAtomicLong();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, "t").start();
        }
        countDownLatch.await();
        endTime = System.currentTimeMillis();
        System.out.println(STR."点赞次数为: \{counter}; 使用 AtomicLong 花费时间: \{endTime - startTime}ms");

        startTime = System.currentTimeMillis();
        countDownLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < CYCLE_NUM; j++) {
                        clickByLongAdder();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, "t").start();
        }
        countDownLatch.await();
        endTime = System.currentTimeMillis();
        System.out.println(STR."点赞次数为: \{counter}; 使用 LongAdder 花费时间: \{endTime - startTime}ms");

        startTime = System.currentTimeMillis();
        countDownLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < CYCLE_NUM; j++) {
                        clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, "t").start();
        }
        countDownLatch.await();
        endTime = System.currentTimeMillis();
        System.out.println(STR."点赞次数为: \{counter}; 使用 LongAccumulator 花费时间: \{endTime - startTime}ms");
    }
}
