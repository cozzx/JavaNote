package resource.resourceloaderaware;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author zt
 * @date 2023/8/22
 **/
public class BeanTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-aware.xml");
        ResourceLoaderAwareTest resourceLoaderAwareTest = applicationContext.getBean("resourceLoaderAwareTest", ResourceLoaderAwareTest.class);
        ResourceLoader resourceLoader = resourceLoaderAwareTest.getResourceLoader();
        System.out.println("Spring 容器将自身注入到 ResourceLoaderAware Bean 中 ？" + (resourceLoader == applicationContext));

        // 加载其他资源
        Resource resource = resourceLoader.getResource("test2.txt");
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
    }
}
