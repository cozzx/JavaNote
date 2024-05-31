package structural.adapter.case2;

import org.junit.Test;

/**
 * 结构型设计模式 - 适配器模式测试
 * 圆钉配圆孔 - 测试类
 *
 * @author zt
 * @date 2024/5/16
 **/
public class AdapterTest {

    /**
     * test class adapter
     */
    @Test
    public void testClass() {
        RoundHole roundHole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);
        if (roundHole.fits(roundPeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        SquarePeg squarePegSmall = new SquarePeg(2);
        SquarePeg squarePegLarge = new SquarePeg(10);

        SquarePegClassAdapter smallSquarePegAdapter = new SquarePegClassAdapter(squarePegSmall);
        SquarePegClassAdapter largeSquarePegAdapter = new SquarePegClassAdapter(squarePegLarge);

        if (roundHole.fits(smallSquarePegAdapter)) {
            System.out.println("Square peg w5 fits round hole r5.");
        }

        if (roundHole.fits(largeSquarePegAdapter)) {
            System.out.println("Square peg w10 fits round hole r5.");
        }
    }

    /**
     * test object adapter
     */
    @Test
    public void testObject() {
        RoundHole roundHole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);
        if (roundHole.fits(roundPeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        SquarePeg squarePegSmall = new SquarePeg(2);
        SquarePeg squarePegLarge = new SquarePeg(10);

        SquarePegObjectAdapter smallSquarePegAdapter = new SquarePegObjectAdapter(squarePegSmall);
        SquarePegObjectAdapter largeSquarePegAdapter = new SquarePegObjectAdapter(squarePegLarge);

        if (roundHole.fits(smallSquarePegAdapter.getRoundPeg())) {
            System.out.println("Square peg w5 fits round hole r5.");
        }

        if (roundHole.fits(largeSquarePegAdapter.getRoundPeg())) {
            System.out.println("Square peg w10 fits round hole r5.");
        }
    }
}
