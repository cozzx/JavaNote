package test.interface_;

/**
 * @author zt
 * @date 2023/8/17
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
