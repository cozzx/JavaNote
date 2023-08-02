package usage;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程创建
 *
 * @author zt
 * @since 2023/6/20 19:58
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
    class MyThreadCallable implements Callable {

        @Override
        public Object call() throws Exception {
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
        // 将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(myThreadCallable);
        // 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start();

        try {
            // 获取Callable中call方法的返回值
            // get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
            Object sum = futureTask.get();
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
            Future future = service.submit(new MyThreadCallable()); // 适合使用于Callable
            System.out.println("总和为：" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3. 关闭连接池
        service.shutdown();
    }
}




