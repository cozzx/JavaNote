package exception;

import org.junit.Test;

/**
 * 常见的编译时异常（受检异常）
 *
 * @author zt
 * @date 2023/7/19
 **/
public class CheckedExceptionTest {

    /**
     * java.lang.InterruptedException
     */
    @Test
    public void test1() {
//        Thread.sleep(1000);
    }

    /**
     * java.lang.ClassNotFoundException
     */
    @Test
    public void test2() {
//        Class c = Class.forName("java.lang.String");
    }

    /**
     * java.sql.SQLException
     */
    @Test
    public void test3() {
//        Connection conn = DriverManager.getConnection("....");
    }

    /**
     * java.io.FileNotFoundException
     */
    @Test
    public void test4() {
//        FileInputStream fis = new FileInputStream("java.txt");
    }

    /**
     * java.io.FileNotFoundException
     * java.io.IOException
     */
    @Test
    public void test5() {
//        File file = new File("java.txt");
//        FileInputStream fis = new FileInputStream(file);
//        int b = fis.read();
//        while (b != -1) {
//            System.out.print((char) b);
//            b = fis.read();
//        }
//
//        fis.close();
    }
}
