package i18n;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Java 中的国际化实现
 *
 * @author zt
 * @date 2023/8/22
 **/
public class JavaI18nTest {

    /**
     * Locale 用于指定当前用户所属的语言环境等信息，Locale 包含了 language 信息和 country 信息
     * ResourceBundle 用于查找绑定对应的资源文件
     */
    @Test
    public void test() {
        String s1 = ResourceBundle.getBundle("messages", new Locale("en", "GB")).getString("test");
        System.out.println(s1);

        String s2 = ResourceBundle.getBundle("messages", new Locale("zh", "CN")).getString("test");
        System.out.println(s2);
    }
}
