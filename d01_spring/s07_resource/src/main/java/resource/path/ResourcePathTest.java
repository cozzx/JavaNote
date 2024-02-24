package resource.path;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过指定 classpath:前缀 搜索类加载路径
 *
 * @author zt
 * @date 2023/8/22
 **/
public class ResourcePathTest {

    /**
     * 前缀使用
     * 一次性加载多个配置文件的方式
     */
    @Test
    public void test1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:beans*.xml");
        System.out.println(ctx);
    }

    /**
     * 通配符使用
     * 系统将搜索类加载路径，找到所有与文件名匹配的文件，分别加载文件中的配置定义，最后合并成一个 ApplicationContext
     */
    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:beans.xml");
        System.out.println(ctx);
    }

    /**
     * 前缀和通配符结合使用
     */
    @Test
    public void test3() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:bean*.xml");
        System.out.println(ctx);
    }
}
