package structural.adapter.case1;

/**
 * @author zt
 * @date 2024/5/21
 **/
public class SdClassAdapter extends TfCardImpl implements SdCard {
    @Override
    public void readSd() {
        readTf();
    }

    @Override
    public void writeSd() {
        writeTf();
    }
}
