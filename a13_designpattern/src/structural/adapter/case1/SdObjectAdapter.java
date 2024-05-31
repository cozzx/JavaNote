package structural.adapter.case1;

/**
 * @author zt
 * @date 2024/5/21
 **/
public class SdObjectAdapter implements SdCard {
    private TfCard tfCard;

    public SdObjectAdapter(TfCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public void readSd() {
        tfCard.readTf();
    }

    @Override
    public void writeSd() {
        tfCard.writeTf();
    }
}
