package test.interface_;

/**
 * @author zt
 * @since 2023/8/17 18:18
 **/
public class InterfaceImpl implements Usb {
    @Override
    public void in() {
        System.out.println("插入");
    }

    @Override
    public void out() {
        System.out.println("拔出");
    }
}
