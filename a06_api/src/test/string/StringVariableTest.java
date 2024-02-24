package test.string;

import org.junit.Test;

/**
 * 可变字符序列测试
 *
 * @author zt
 * @date 2023/7/22
 **/
public class StringVariableTest {

    /**
     * 对比可变和不可变字符序列的哈希值
     */
    @Test
    public void test1() {
        // String 类重写了 hashCode 方法，使用 System 类的 identityHashCode() 获取原始的 hashCode
        // 以下 s1 s2 hashCode 值相等，identityHashCode 值不相等
        String s1 = "hello";
        System.out.println(s1.hashCode());
        System.out.println(System.identityHashCode(s1));
        String s2 = new String("hello");
        System.out.println(s2.hashCode());
        System.out.println(System.identityHashCode(s2));
        System.out.println();

        // 以下 s1 s2 重新赋值后，和之前的 identityHashCode 不一样了
        s1 = "helloworld";
        s2 = "helloworld";
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));
        System.out.println();

        // 可变字符序列的 identityHashCode 在修改后还是一样的
        StringBuffer sb = new StringBuffer("hello");
        System.out.println(System.identityHashCode(sb));
        sb.append("world");
        System.out.println(System.identityHashCode(sb));
    }

    /**
     * StringBuilder/StringBuffer 常用 API
     */
    @Test
    public void test2() {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("hello").append(true).append('a').append(12).append(12.5);
        System.out.println(sb1);
        System.out.println(sb1.length());

        StringBuilder sb2 = new StringBuilder("helloworld");
        sb2.insert(5, "java");
        System.out.println(sb2);

        StringBuilder sb3 = new StringBuilder("helloworld");
        sb3.delete(1, 3);
        System.out.println(sb3);
        sb3.deleteCharAt(4);
        System.out.println(sb3);

        StringBuilder sb4 = new StringBuilder("helloworld");
        sb4.reverse();
        System.out.println(sb4);

        StringBuilder sb5 = new StringBuilder("helloworld");
        sb5.setCharAt(2, 'a');
        System.out.println(sb5);

        StringBuilder sb6 = new StringBuilder("helloworld");
        sb6.setLength(30);
        System.out.println(sb6);
    }

    /**
     * 效率测试
     */
    @Test
    public void test3() {
        // 初始设置
        long startTime;
        long endTime;
        String text = "";
        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();

        // 开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String的执行时间：" + (endTime - startTime));
    }
}
