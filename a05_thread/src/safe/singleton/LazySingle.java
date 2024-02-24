package safe.singleton;

/**
 * 单例模式懒汉式
 *
 * @author zt
 * @date 2023/7/27 20:45
 **/
public class LazySingle {

    private static LazySingle instance = null;

    private LazySingle() {
    }

    // 线程不安全
    public static LazySingle getInstance() {
        if (instance == null) {
            try {
                // 睡眠等待更明显暴露问题
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazySingle();
        }
        return instance;
    }

    // 线程安全
    public static LazySingle getInstanceSafe1() {
        synchronized (LazySingle.class) {
            if (instance == null) {
                try {
                    // 睡眠一会也没问题
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new LazySingle();
            }
            return instance;
        }
    }

    // 线程安全，有指令重拍问题
    public static LazySingle getInstanceSafe2() {
        // 如果不是null就不用等了
        if (instance == null) {
            synchronized (LazySingle.class) {
                if (instance == null) {
                    try {
                        // 睡眠一会也没问题
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new LazySingle();
                }
            }
        }
        return instance;
    }

    // 线程安全，使用内部类
    // 内部类只有在外部类被调用才加载，产生INSTANCE实例；又不用加锁。
    // 此模式具有之前两个模式的优点，同时屏蔽了它们的缺点，是最好的单例模式。
    // 此时的内部类，使用enum进行定义，也是可以的。
    public static LazySingle getInstanceSafe3() {
        return Inner.INSTANCE;
    }

    private static class Inner {
        static final LazySingle INSTANCE = new LazySingle();
    }
}
