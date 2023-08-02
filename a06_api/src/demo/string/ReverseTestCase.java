package demo.string;

import org.junit.Test;

/**
 * 字符串反转
 *
 * @author zt
 * @since 2023/7/21 19:24
 **/
public class ReverseTestCase {

    public String reverse1(String str, int start, int end) {
        if (str == null || "".equals(str) || start >= end) {
            return str;
        }
        if (end >= str.length()) {
            end = str.length() - 1;
        }
        char[] charArray = str.toCharArray();
        while (start < end) {
            char tmpC = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = tmpC;
            start++;
            end--;
        }
        return new String(charArray);
    }

    public String reverse2(String str, int start, int end) {
        if (str == null || "".equals(str) || start >= end) {
            return str;
        }
        if (end >= str.length()) {
            end = str.length() - 1;
        }

        StringBuilder resStr = new StringBuilder(str.length());
        if (start > 0) {
            resStr.append(str, 0, start);
        }
        for (int i = end; i >= start; i--) {
            resStr.append(str.charAt(i));
        }
        if (end < str.length() - 1) {
            resStr.append(str.substring(end + 1));
        }
        return resStr.toString();
    }

    @Test
    public void test() {
        System.out.println(reverse1("java", 0, 12));
        System.out.println(reverse2("java", 0, 12));
    }
}
