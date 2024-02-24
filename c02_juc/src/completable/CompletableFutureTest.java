package completable;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author zt
 * @date 2024/1/13
 **/
public class CompletableFutureTest {

    /**
     * runAsync 无返回值
     */
    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            // 默认使用 ForkJoinPool.commonPool(), 传入线程池参数用于指定执行线程池
            String threadName = Thread.currentThread().getName();
            System.out.println("支线线程名称: " + threadName);

            int i = 10;
            while (i > 0) {
                System.out.println("支线任务执行中...");
                i--;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, executorService);

        System.out.println("主线程工作1...");

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("主线程工作2...");
        executorService.shutdown();
    }

    /**
     * supplyAsync 有返回值
     */
    @Test
    public void test2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            // 默认使用 ForkJoinPool.commonPool(), 传入线程池参数用于指定执行线程池
            String threadName = Thread.currentThread().getName();
            System.out.println("支线线程名称: " + threadName);

            int i = 10;
            while (i > 0) {
                i--;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "42";
        }, executorService);

        System.out.println("主线程工作中...");
        String result;
        while (true) {
            if (completableFuture.isDone()) {
                result = completableFuture.get();
                System.out.println("支线任务结果 result = " + result);
                break;
            } else {
                System.out.println("支线任务未完成, 正在等待结果...");
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }
        System.out.println(STR."主线程使用支线任务结果'\{result}'继续工作...");
        executorService.shutdown();
    }

    /**
     * 异步方法
     */
    @Test
    public void test3() {
        System.out.println("主线程工作1...");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("支线线程名称: " + threadName);

            int i = 10;
            while (i > 0) {
                i--;
                try {
                    System.out.println("支线任务执行中...");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 模拟异常
                // int m = 10 / i;
            }
            System.out.println("支线任务返回");
            return "42";
        }, executorService).whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("throwable = " + throwable);
                System.out.println("支线任务完成, 任务结果: " + s);
            }
        }).exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                System.out.println("出现异常: ");
                throwable.printStackTrace();
                return null;
            }
        });
        System.out.println("主线程工作2...");
        for (int i = 0; i < 20; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
        executorService.shutdown();
    }
}
