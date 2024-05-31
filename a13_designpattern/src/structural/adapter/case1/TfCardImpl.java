package structural.adapter.case1;

/**
 * @author zt
 * @date 2024/5/21
 **/
public class TfCardImpl implements TfCard {
    @Override
    public void readTf() {
        System.out.println("read TF card");
    }

    @Override
    public void writeTf() {
        System.out.println("write TF card");
    }
}
