package validator.anno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Bean Validation 注解实现
 * 配置类
 *
 * @author zt
 * @date 2023/8/22
 **/
@Configuration
@ComponentScan("validator.anno")
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
