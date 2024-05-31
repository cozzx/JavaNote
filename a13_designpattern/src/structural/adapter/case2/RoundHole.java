package structural.adapter.case2;

/**
 * 结构型设计模式 - 适配器模式测试
 * 圆钉配圆孔 - 圆孔类
 *
 * @author zt
 * @date 2024/5/16
 **/
public class RoundHole {
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg roundPeg) {
        return this.getRadius() >= roundPeg.getRadius();
    }
}
