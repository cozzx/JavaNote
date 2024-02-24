package resource.resourceloader;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 通过 ApplicationContext 访问资源
 * ApplicationContext 实例获取 Resource 实例时，默认采用与 ApplicationContext 相同的资源访问策略
 *
 * @author zt
 * @date 2023/8/22
 **/
public class ResourceLoaderTest {

    @Test
    public void test1() {
        // ApplicationContext 是 ClassPathXmlApplicationContext，resource 就是 ClassPathResource 实例
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        Resource resource = applicationContext.getResource("test2.txt");
        System.out.println(resource.getFilename());
    }

    @Test
    public void test2() {
        // ApplicationContext 是 FileSystemXmlApplicationContext，resource 就是 FileSystemResource 实例
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext();
        Resource resource = applicationContext.getResource("test2.txt");
        System.out.println(resource.getFilename());
    }
}
