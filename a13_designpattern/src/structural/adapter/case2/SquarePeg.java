package structural.adapter.case2;

/**
 * 结构型设计模式 - 适配器模式测试
 * 圆钉配圆孔 - 方钉类
 *
 * @author zt
 * @date 2024/5/16
 **/
public class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getSquare() {
        return Math.pow(this.width, 2);
    }
}
