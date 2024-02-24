package completable;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zt
 * @date 2024/1/13
 **/
public class CompletableFutureApiTest {

    /**
     * 获取结果的接口
     * get()
     * get(long timeout, TimeUnit unit)
     * join()
     * getNow(T valueIfAbsent)
     */
    @Test
    public void test1() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("current thread name = " + Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                System.out.println("支线任务进行中");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return "42";
        }, executorService);

        TimeUnit.SECONDS.sleep(1);

        // 当前异步任务未完成时, getNow() 会返回参数的默认值
//        String result = completableFuture.getNow("00");
//        System.out.println("result = " + result);

        // 当前异步任务未完成时, 会打断任务, 返回 complete 中的参数值
        boolean complete = completableFuture.complete("00");
        System.out.println(STR."complete = \{complete}");
        String result = completableFuture.get();
        System.out.println("result = " + result);

        executorService.shutdown();
    }

    /**
     * thenApply() / handle() 计算结果存在依赖关系，这两个线程串行化
     * 区别是对异常的处理
     */
    @Test
    public void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("current thread name = " + Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                System.out.println("支线任务进行中");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "42";
        }, executorService).thenApply(f -> {
            System.out.println("thenApply");
//            int i = 10 / 0;
            return f + "-thenApply";
        }).handle((f, e) -> {
            System.out.println("handle");
//            int i = 10 / 0;
            return f + "-handle";
        }).whenComplete((v, e) -> {
            System.out.println("result = " + v);
            System.out.println("exception = " + e);
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getCause());
            return null;
        });

        System.out.println("主线程工作...");
        for (int i = 0; i < 20; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
        executorService.shutdown();
    }

    /**
     * applyToEither 哪个任务先返回用谁
     */
    @Test
    public void test3() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("A come in");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playA";
        }, threadPool);


        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("B come in");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playB";
        }, threadPool);

        CompletableFuture<String> result = playA.applyToEither(playB, f -> f + " is faster");

        System.out.println("result: " + result.join());
    }

    /**
     * thenCombine() 对计算结果进行合并
     */
    @Test
    public void test4() {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        CompletableFuture<Integer> finalResult = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println("对开始两个结果合并");
            return x + y;
        });
        System.out.println(finalResult.join());
    }
}
