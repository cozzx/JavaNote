package bytecode;

import bytecode.common.BasicInterface;
import bytecode.common.BasicSuper;

/**
 * @author zt
 * @date 2023/12/26
 **/
public class Basic extends BasicSuper implements BasicInterface {

    public int a;

    public int b;

    public static int c;

    public static final String d = "ddd";

    public Basic(int m, int n) {
        this.a = m;
        this.b = n;
        var e = m - n;
        System.out.println(e);
        System.out.println(add(this.a, this.b));
    }

    @Override
    public Double add(int m, int n) {
        var f = m - n;
        System.out.println(f);
        return (double) (m + n);
    }
}
