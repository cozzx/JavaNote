package structural.adapter.case1;

/**
 * @author zt
 * @date 2024/5/21
 **/
public class Computer {
    public void readDisk(SdCard sdCard) {
        sdCard.readSd();
    }

    public void writeDisk(SdCard sdCard) {
        sdCard.writeSd();
    }
}
