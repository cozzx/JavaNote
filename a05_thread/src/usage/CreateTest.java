package usage;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程创建
 *
 * @author zt
 * @date 2023/6/20
 **/
public class CreateTest {

    /**
     * 创建方式一：继承 Thread 类
     */
    class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("exThread--" + Thread.currentThread().getName() + "---" + i);
            }
        }
    }

    /**
     * 继承 Thread 类 测试
     */
    @Test
    public void test1() {
        MyThread myThread = new MyThread();
        myThread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    }

    /**
     * Thread 类匿名实现 测试
     */
    @Test
    public void test2() {
        new Thread("newThread") {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("anoExThread--" + Thread.currentThread().getName() + "---" + i);
                }
            }
        }.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    }

    /**
     * 创建方式二：实现 Runnable 接口
     */
    class MyThreadRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("runnable--" + Thread.currentThread().getName() + "---" + i);
            }
        }
    }

    /**
     * Runnable 接口实现 测试
     */
    @Test
    public void test3() {
        MyThreadRunnable myThreadRunnable = new MyThreadRunnable();
        Thread myThread = new Thread(myThreadRunnable);
        myThread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    }

    /**
     * Runnable 匿名实现 测试
     */
    @Test
    public void test4() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("anoRunnable--" + Thread.currentThread().getName() + "---" + i);
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    }

    /**
     * 创建方式三：实现 Callable 接口，jdk5 新增
     */
    class MyThreadCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                    sum += i;
                }
            }
            return sum;
        }
    }

    /**
     * 实现 Callable 接口 测试
     */
    @Test
    public void test5() {
        MyThreadCallable myThreadCallable = new MyThreadCallable();
        // 将此 Callable 接口实现类的对象作为参数传递到 FutureTask 构造器中，创建 FutureTask 的对象
        FutureTask<Integer> futureTask = new FutureTask<>(myThreadCallable);
        // 将 FutureTask 的对象作为参数传递到 Thread 类的构造器中，创建 Thread 对象，并调用 start()
        new Thread(futureTask).start();

        try {
            // 获取 Callable 中 call 方法的返回值
            // get() 返回值即为 FutureTask 构造器参数 Callable 实现类重写的 call() 的返回值。
            Integer sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建方式四：线程池，jdk5 新增
     */
    @Test
    public void test6() {
        // 1. 提供指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // ThreadPoolExecutor
        System.out.println(executorService.getClass());
        ThreadPoolExecutor service = (ThreadPoolExecutor) executorService;
        // 设置线程池的属性，设置线程池中线程数的上限
        service.setMaximumPoolSize(50);

        // 2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new MyThreadRunnable()); // 适合适用于Runnable

        try {
            Future<Integer> future = service.submit(new MyThreadCallable()); // 适合使用于Callable
            System.out.println("总和为：" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3. 关闭连接池
        service.shutdown();
    }

    /**
     * ExecutorService 中的 submit 既可以执行 Runnable 实例 又可以执行 Callable 示例
     */
    @Test
    public void test7() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> futureRunnable = executorService.submit(new MyThreadRunnable(), 123);// 适合适用于Runnable
        Future<Integer> futureCallable = executorService.submit(new MyThreadCallable()); // 适合使用于Callable
        try {
            System.out.println("futureRunnable.get() = " + futureRunnable.get());
            System.out.println("futureCallable.get() = " + futureCallable.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    /**
     * 虚拟线程
     */
    @Test
    public void test8() {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        Future<Integer> futureRunnable = executorService.submit(new MyThreadRunnable(), 123);
        Future<Integer> futureCallable = executorService.submit(new MyThreadCallable());
        try {
            System.out.println("futureRunnable.get() = " + futureRunnable.get());
            System.out.println("futureCallable.get() = " + futureCallable.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}




