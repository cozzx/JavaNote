package demo.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * 找最大相同子字符串
 *
 * @author zt
 * @since 2023/7/21 19:57
 **/
public class FindMaxSameTestCase {

    /**
     * 只存在一个最大长度的相同子串
     */
    public String findMaxSame(String str1, String str2) {
        if (str1 == null || str2 == null || "".equals(str1) || "".equals(str2)) {
            return "";
        }

        String maxStr = (str1.length() > str2.length()) ? str1 : str2;
        String minStr = (str1.length() > str2.length()) ? str2 : str1;

        for (int i = 0; i < minStr.length(); i++) {
            for (int x = 0, y = minStr.length() - i; y <= minStr.length(); x++, y++) {
                if (maxStr.contains(minStr.substring(x, y))) {
                    return minStr.substring(x, y);
                }
            }
        }
        return "";
    }

    /**
     * 存在多个最大长度的相同子串
     */
    public String[] findMaxSameMulti(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        String maxString = (str1.length() > str2.length()) ? str1 : str2;
        String minString = (str1.length() > str2.length()) ? str2 : str1;

        for (int i = 0; i < minString.length(); i++) {
            for (int x = 0, y = minString.length() - i; y <= minString.length(); x++, y++) {
                String subString = minString.substring(x, y);
                if (maxString.contains(subString)) {
                    sb.append(subString).append(",");
                }
            }
            if (sb.length() != 0) {
                break;
            }
        }
        return sb.toString().replaceAll(",$", "").split(",");
    }

    @Test
    public void test() {
        System.out.println(findMaxSame("hello_java", "hello_world"));
        System.out.println(Arrays.toString(findMaxSameMulti("hello_hello", "hlelo")));
    }
}
