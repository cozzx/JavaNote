package array;

import org.junit.Test;

/**
 * 自定义数组测试
 *
 * @author zt
 * @date 2023/7/3 21:04
 **/
public class ArrayTest {

    @Test
    public void test1() {

        ArrayCustom arrayCustom = new ArrayCustom(10);
        arrayCustom.add(123);
        arrayCustom.add("123");
        arrayCustom.add("123");
        arrayCustom.add("hello");
        arrayCustom.add("java");

        arrayCustom.print();
        System.out.println(arrayCustom.find("123"));
        System.out.println(arrayCustom.update("java", "rust"));
        System.out.println(arrayCustom.delete("123"));
        arrayCustom.print();
    }
}
