package handle;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 异常处理 try-catch-finally
 *
 * @author zt
 * @since 2023/7/19 22:35
 **/
public class CatchTest {

    @Test
    public void test1() {
        String[] friends = {"lisa", "lily", "tom"};
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(friends[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index err");
        }
        System.out.println("this is the end");
    }

    @Test
    public void test2() {
        try {
            String str1 = "java";
            str1 = null;
            System.out.println(str1.charAt(0));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    @Test
    public void test3() {
        FileInputStream fis = null;
        try {
            File file = new File("hello.txt");
            fis = new FileInputStream(file);
            int b = fis.read();
            while (b != -1) {
                System.out.print((char) b);
                b = fis.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test4() {
        System.out.println(test41());
    }

    public int test41() {
        int i = 100;
        try {
            return i;
        } finally {
            i++;
        }
    }
}
