package common;

/**
 * 公共测试类
 *
 * @author zt
 * @since 2023/7/5 20:52
 **/
public class Circle {
    private double radius;

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle [radius=" + radius + "]";
    }

}
