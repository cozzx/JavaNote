package structural.adapter.case1;

/**
 * @author zt
 * @date 2024/5/21
 **/
public class SdCardImpl implements SdCard {

    @Override
    public void readSd() {
        System.out.println("read SD card");
    }

    @Override
    public void writeSd() {
        System.out.println("write SD card");
    }
}
