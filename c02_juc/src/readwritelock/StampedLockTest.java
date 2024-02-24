package readwritelock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.StampedLock;

/**
 * @author zt
 * @date 2024/1/21
 **/
public class StampedLockTest {
    int number = 42;
    StampedLock stampedLock = new StampedLock();

    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(STR."\{Thread.currentThread().getName()}: 写线程准备修改");
        try {
            number = number + 13;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(STR."\{Thread.currentThread().getName()}: 写线程结束修改, number 为 \{number}");
    }

    // 悲观读
    public void readPessimistic() {
        long stamp = stampedLock.readLock();
        System.out.println(STR."\{Thread.currentThread().getName()}: 进入读线程");
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(STR."\{Thread.currentThread().getName()}: 正在读取中");
        }
        try {
            System.out.println(STR."\{Thread.currentThread().getName()}: 获得成员变量值 number 为 \{number}");
            System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥");
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    /**
     * 乐观读
     * stampedLock.validate 方法值（true 无修改 false有修改）
     * 乐观锁实际上不需要显式释放。乐观锁通过锁升级和降级来管理锁定状态，而不是通过获取和释放锁。
     * 在示例中，如果数据满足条件，乐观读锁状态会一直保持；如果数据不满足条件，则会升级到悲观读锁，并在操作完成后释放该锁。
     * 这样，锁管理是自动进行的，无需手动释放乐观锁。
     */
    public void readOptimistic() {
        long stamp = stampedLock.tryOptimisticRead();
        System.out.println(STR."\{Thread.currentThread().getName()} 开始 stampedLock.validate 方法值为: \{stampedLock.validate(stamp)}");
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(STR."\{Thread.currentThread().getName()}\t 正在读取 \{i}秒后 stampedLock.validate 方法值为: \{stampedLock.validate(stamp)}");
        }
        if (!stampedLock.validate(stamp)) {
            System.out.println("有人修改----------有写操作");
            stamp = stampedLock.readLock();
            try {
                System.out.println(STR."从乐观读升级为悲观读, 重新悲观读后 number 为: \{number}");
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(STR."\{Thread.currentThread().getName()} 最终读取 number 为: \{number}");

    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(2);
        new Thread(() -> {
            try {
//                readPessimistic();
                readOptimistic();
            } finally {
                count.countDown();
            }
        }, "readThread").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                write();
            } finally {
                count.countDown();
            }
        }, "writeThread").start();

        count.await();
    }
}
