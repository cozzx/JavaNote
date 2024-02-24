package atomic;

import common.Student;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.*;

/**
 * 原子操作类 API 测试
 *
 * @author zt
 * @date 2024/1/18
 **/
public class AtomicApiTest {

    /**
     * 基本类型原子类 AtomicInteger AtomicBoolean AtomicLong
     */
    @Test
    public void test() {
        final int size = 50;
        AtomicInteger atomicInteger = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        atomicInteger.getAndIncrement();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, STR."t\{i}").start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(STR."atomicInteger = \{atomicInteger.get()}");
    }


    /**
     * 数组类型原子类 AtomicIntegerArray AtomicLongArray AtomicReferenceArray
     */
    @Test
    public void test2() {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);
        int tempInt;
        tempInt = atomicIntegerArray.getAndSet(0, 2023);
        System.out.println(STR."atomicIntegerArray.index[0].src: \{tempInt}; atomicIntegerArray.index[0].dst: \{atomicIntegerArray.get(0)}");
        tempInt = atomicIntegerArray.getAndIncrement(0);
        System.out.println(STR."atomicIntegerArray.index[0].src: \{tempInt}; atomicIntegerArray.index[0].dst: \{atomicIntegerArray.get(0)}");
    }

    /**
     * 引用类型原子类 AtomicReference AtomicStampedReference AtomicMarkableReference
     * AtomicStampedReference 在 cas.AbaTest 中演示, 这里演示 AtomicMarkableReference
     */
    @Test
    public void test3() {
        Student lucy = new Student(1, "lucy", "c1");
        Student lily = new Student(2, "lily", "c4");
        Student tom = new Student(3, "tom", "c6");
        AtomicMarkableReference<Student> atomicMarkableReference = new AtomicMarkableReference<>(lucy, false);
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(STR."t1 first expect mark: \{marked}");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicMarkableReference.compareAndSet(lucy, lily, marked, !marked);
            System.out.println(STR."t1 modify 1 result: \{b}; ref: \{atomicMarkableReference.getReference()};");

//            marked = atomicMarkableReference.isMarked();
//            System.out.println(STR."t1 second expect mark: \{marked}");
//
//            b = atomicMarkableReference.compareAndSet(lily, lucy, marked, !marked);
//            System.out.println(STR."t1 modify 2 result: \{b}; ref: \{atomicMarkableReference.getReference()};");
//            latch.countDown();
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(STR."t2 first expect mark: \{marked}");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicMarkableReference.compareAndSet(lucy, tom, marked, !marked);
            System.out.println(STR."t2 modify result: \{b}; ref: \{atomicMarkableReference.getReference()};");
            latch.countDown();
        }, "t2");
        t2.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字段修改原子类 AtomicIntegerFieldUpdater AtomicLongFieldUpdater AtomicReferenceFieldUpdater
     * AtomicIntegerFieldUpdater 使用案例
     */
    class BankAccount {
        public volatile int money = 0;
        AtomicIntegerFieldUpdater<BankAccount> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

        public void transferMoney(BankAccount bankAccount) {
            atomicIntegerFieldUpdater.getAndIncrement(bankAccount);
        }
    }

    @Test
    public void test4() {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        bankAccount.transferMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, STR."t\{i}").start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(STR."result: \{bankAccount.money}");
    }

    /**
     * 字段修改原子类 AtomicIntegerFieldUpdater AtomicLongFieldUpdater AtomicReferenceFieldUpdater
     * AtomicReferenceFieldUpdater 使用案例
     */
    class MyStudent {
        public volatile String student = "lucy";
        AtomicReferenceFieldUpdater<MyStudent, String> updater = AtomicReferenceFieldUpdater.newUpdater(MyStudent.class, String.class, "student");

        public void modify(MyStudent myStudent) {
            if (updater.compareAndSet(myStudent, "lucy", "lily")) {
                System.out.println(STR."\{Thread.currentThread().getName()} thread start modify, need 2 secondes");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(STR."\{Thread.currentThread().getName()} thread over, modify sucess");
            } else {
                System.out.println(STR."\{Thread.currentThread().getName()} thread over, modify failed");
            }
        }
    }

    @Test
    public void test5() {
        MyStudent myStudent = new MyStudent();
        System.out.println(STR."my student is = \{myStudent.student}");
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    myStudent.modify(myStudent);
                } finally {
                    countDownLatch.countDown();
                }
            }, STR."t\{i}").start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(STR."modify finish, my student is = \{myStudent.student}");
    }
}
