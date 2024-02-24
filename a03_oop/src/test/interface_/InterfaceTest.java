package test.interface_;

import org.junit.Test;

/**
 * @author zt
 * @date 2023/8/17
 **/
public class InterfaceTest {

    @Test
    public void test1() {
        Usb usb = new InterfaceImpl();
        System.out.println("我有一个U盘，速度" + Usb.MAX_SPEED);
        usb.start();
        usb.in();
        usb.out();
        usb.stop();
    }
}
