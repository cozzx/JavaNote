package structural.adapter.case2;

/**
 * 结构型设计模式 - 适配器模式测试
 * 圆钉配圆孔 - 方钉适配器类 - 对象适配器
 *
 * @author zt
 * @date 2024/5/16
 **/
public class SquarePegObjectAdapter {
    private SquarePeg squarePeg;

    public SquarePegObjectAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    public RoundPeg getRoundPeg() {
        return new RoundPeg(Math.sqrt(Math.pow((squarePeg.getWidth() / 2), 2) * 2));
    }
}
