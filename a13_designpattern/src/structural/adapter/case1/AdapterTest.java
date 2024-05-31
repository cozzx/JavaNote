package structural.adapter.case1;

import org.junit.Test;

/**
 * @author zt
 * @date 2024/5/21
 **/
public class AdapterTest {
    @Test
    public void test() {
        Computer computer = new Computer();
        SdCard sdCard = new SdCardImpl();
        computer.readDisk(sdCard);
    }

    // 类适配器模式
    @Test
    public void test2() {
        Computer computer = new Computer();
        SdClassAdapter sdAdapter = new SdClassAdapter();
        computer.readDisk(sdAdapter);
    }

    // 对象适配器模式
    @Test
    public void test3() {
        Computer computer = new Computer();
        SdObjectAdapter sdAdapter = new SdObjectAdapter(new TfCardImpl());
        computer.readDisk(sdAdapter);
    }
}
