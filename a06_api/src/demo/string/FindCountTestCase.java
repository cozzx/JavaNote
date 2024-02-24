package demo.string;

import org.junit.Test;

/**
 * 获取一个字符串在另一个字符串中出现的次数
 *
 * @author zt
 * @date 2023/7/21
 **/
public class FindCountTestCase {

    public int findCount(String str, String subStr) {
        if (str == null || subStr == null || "".equals(str) || "".equals(subStr)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();
        }
        return count;
    }

    @Test
    public void test() {
        System.out.println(findCount("java", "a"));
    }
}
