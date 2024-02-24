package conversion;

import org.junit.Test;

/**
 * 字符串与基本数据类型运算
 *
 * @author zt
 * @date 2023/7/12
 **/
public class StringTest {

    /**
     * 连接运算
     */
    @Test
    public void test1() {
        System.out.println(3.5f + "");
        System.out.println(3 + 4 + "Hello!");
        System.out.println("Hello!" + 3 + 4);
        System.out.println('a' + 1 + "Hello!");
        System.out.println("Hello" + 'a' + 1);
    }

    /**
     * 强制类型转换
     */
    @Test
    public void test2() {
        // 基本数据类型和字符串之间不存在自动类型提升
//        String i = 1;

        String str = "123";
        // String类型不能通过强制类型()转换，转为其他的类型
//        int num = (int) str;
        // 需要借助借助包装类的方法才能转
        int num = Integer.parseInt(str);
    }
}
