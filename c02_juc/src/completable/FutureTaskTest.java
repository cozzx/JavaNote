package completable;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask 测试
 *
 * @author zt
 * @date 2024/1/13
 **/
public class FutureTaskTest {

    /**
     * 测试 get() 方法, 有返回值, 但是会阻塞
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int i = 10;
                while (i > 0) {
                    System.out.println("支线任务执行中");
                    i--;
                    Thread.sleep(1000);
                }
                return "42";
            }
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("主线程工作1...");
        String result = futureTask.get();
        System.out.println(result);
        System.out.println("主线程工作2...");
    }

    /**
     * 测试 get() 方法, 有返回值, 但是会阻塞
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            int i = 10;
            while (i > 0) {
                i--;
                Thread.sleep(1000);
            }
            return "I'm 42";
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("主线程工作1...");
        while (true) {
            if (futureTask.isDone()) {
                String result = futureTask.get();
                System.out.println(STR."支线任务结果 result = \{result}");
                break;
            } else {
                System.out.println("支线任务未完成, 正在等待结果...");
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }
        System.out.println("主线程工作2...");
    }
}
