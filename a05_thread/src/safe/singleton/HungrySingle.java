package safe.singleton;

/**
 * 单例模式饿汉式
 *
 * @author zt
 * @date 2023/7/27 20:45
 **/
public class HungrySingle {

    private static final HungrySingle INSTANCE = new HungrySingle();

    private HungrySingle() {
    }

    public static HungrySingle getInstance() {
        return INSTANCE;
    }
}
