package usage;

import org.junit.Test;

/**
 * 线程类的一些方法
 *
 * @author zt
 * @date 2023/7/26 22:40
 **/
public class MethodTest {

    @Test
    public void test1() {
        Thread evenNumberThread = new Thread(() -> {
            for (int i = 2; i <= 20; i += 2) {
                System.out.println(Thread.currentThread().getName() + "---" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        evenNumberThread.setName("偶数线程");
        evenNumberThread.setPriority(Thread.MIN_PRIORITY);
        evenNumberThread.start();

        Thread oddNumberThread = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i += 2) {
                    System.out.println(Thread.currentThread().getName() + "---" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        oddNumberThread.setName("奇数线程");
        oddNumberThread.setPriority(Thread.MAX_PRIORITY);
        oddNumberThread.start();

        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "正在运行。。。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemonThread.setName("守护线程");
        daemonThread.start();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        boolean status = true;
//        do {
//            if (evenNumberThread.isAlive()) {
//                System.out.println("et is alive");
//            }
//            if (oddNumberThread.isAlive()) {
//                System.out.println("ot is alive");
//            }
//            if (!evenNumberThread.isAlive() && !oddNumberThread.isAlive()) {
//                status = false;
//            } else {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        } while (status);


        try {
            // 等待线程结束
            evenNumberThread.join();
            oddNumberThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
