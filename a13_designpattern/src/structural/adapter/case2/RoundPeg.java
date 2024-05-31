package structural.adapter.case2;

/**
 * 结构型设计模式 - 适配器模式测试
 * 圆钉配圆孔 - 圆钉类
 *
 * @author zt
 * @date 2024/5/16
 **/
public class RoundPeg {
    private double radius;

    public RoundPeg() {
    }

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
