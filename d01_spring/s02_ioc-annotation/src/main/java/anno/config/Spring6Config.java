package anno.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 全注解开发就是不再使用spring配置文件了，写一个配置类来代替配置文件
 *
 * @author zt
 * @date 2023/8/21
 **/
@Configuration
@ComponentScan("anno.autowired")
public class Spring6Config {

}
