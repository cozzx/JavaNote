package demo.string;

import org.junit.Test;

/**
 * 去除字符串两端的字符
 *
 * @author zt
 * @date 2023/7/21
 **/
public class TrimTestCase {

    /**
     * 去除字符串两边的空白字符
     */
    public String myTrim(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        // 用于记录从前往后首次索引位置不是空格的位置的索引
        int start = 0;
        // 用于记录从后往前首次索引位置不是空格的位置的索引
        int end = str.length() - 1;

        while (start < end && isBlankChar(str.charAt(start))) {
            start++;
        }
        while (start < end && isBlankChar(str.charAt(end))) {
            end--;
        }
        return str.substring(start, end + 1);
    }

    /**
     * 去除字符串两边的子字符串，包括字符串两侧的空白
     */
    public String myTrim(String str, String subStr) {
        if (str == null || "".equals(str) || subStr == null) {
            return str;
        }
        str = myTrim(str);
        if (!"".equals(subStr)) {
            int si = str.indexOf(subStr);
            if (si == 0) {
                str = str.substring(subStr.length());
            }
            int ei = str.lastIndexOf(subStr);
            if (ei != -1 && ei == str.length() - subStr.length()) {
                str = str.substring(0, ei);
            }
        }
        return str;
    }

    public boolean isBlankChar(char c) {
        char[] blankChar = new char[]{' ', '\t', '\n'};
        for (char bc : blankChar) {
            if (c == bc) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(myTrim("\t  11211  \n", ""));
        System.out.println(myTrim("\t  11211  \n", "1"));
        System.out.println(myTrim("\t  11211  \n", "11"));
        System.out.println(myTrim("\t  11211  \n", "112"));
    }
}
