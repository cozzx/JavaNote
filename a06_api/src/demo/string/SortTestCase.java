package demo.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * 对字符串中字符进行自然顺序排序
 *
 * @author zt
 * @since 2023/7/22 19:37
 **/
public class SortTestCase {

    public String mySort(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    @Test
    public void test() {
        System.out.println(mySort("qwertyuioplkjhgfdsazxcvbnm"));
    }
}
