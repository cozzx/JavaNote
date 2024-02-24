package readwritelock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * ReentrantLock、ReentrantReadWriteLock、StampedLock 性能比较
 *
 * @author zt
 * @date 2024/1/21
 **/
public class PerformanceComparisonTest {
    Lock lock = new ReentrantLock();
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    StampedLock stampedLock = new StampedLock();
    int read = 1000;
    int write = 3;
    long mills = 10;

    @Test
    public void test() {
        testReentrantLock();
        testReentrantReadWriteLock();
        testStampedLock();
    }

    public void testReentrantLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        ExecutorService executorServiceWrite = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(read + write);
        long l = System.currentTimeMillis();
        for (int i = 0; i < read; i++) {
            executorService.execute(() -> {
                try {
                    read();
                } finally {
                    latch.countDown();
                }
            });
        }
        for (int i = 0; i < write; i++) {
            executorServiceWrite.execute(() -> {
                try {
                    write();
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        executorServiceWrite.shutdown();
        System.out.println(STR."testReentrantLock 执行耗时：\{System.currentTimeMillis() - l}");
    }

    public void read() {
        lock.lock();
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void write() {
        lock.lock();
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void testReentrantReadWriteLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        ExecutorService executorServiceWrite = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(read + write);
        long l = System.currentTimeMillis();
        for (int i = 0; i < read; i++) {
            executorService.execute(() -> {
                try {
                    readLock();
                } finally {
                    latch.countDown();
                }
            });
        }
        for (int i = 0; i < write; i++) {
            executorServiceWrite.execute(() -> {
                try {
                    writeLock();
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        executorServiceWrite.shutdown();
        System.out.println(STR."testReentrantReadWriteLock 执行耗时：\{System.currentTimeMillis() - l}");
    }

    public void readLock() {
        readWriteLock.readLock().lock();
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void writeLock() {
        readWriteLock.writeLock().lock();
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void testStampedLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        ExecutorService executorServiceWrite = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(read + write);
        long l = System.currentTimeMillis();
        for (int i = 0; i < read; i++) {
            executorService.execute(() -> {
                try {
                    tryOptimisticRead();
//                    readStampedLock();
                } finally {
                    latch.countDown();
                }
            });
        }
        for (int i = 0; i < write; i++) {
            executorServiceWrite.execute(() -> {
                try {
                    writeStampedLock();
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        executorServiceWrite.shutdown();
        System.out.println(STR."testStampedLock 执行耗时：\{System.currentTimeMillis() - l}");
    }

    public void tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        try {
            Thread.sleep(mills);
            if (!stampedLock.validate(stamp)) {
                long readLock = stampedLock.readLock();
                try {
                    Thread.sleep(mills);
                } finally {
                    stampedLock.unlock(readLock);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void readStampedLock() {
        long stamp = stampedLock.readLock();
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    public void writeStampedLock() {
        long stamp = stampedLock.writeLock();
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlock(stamp);
        }
    }
}

