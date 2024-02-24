package com.cozz.feat;

import com.cozz.feat.bean.Cat;
import com.cozz.feat.bean.Dog;
import com.cozz.feat.bean.Pig;
import com.cozz.feat.bean.Sheep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zt
 * @date 2023/8/30
 **/
@SpringBootApplication
@Slf4j
public class Sb06MainApplication {
    public static void main(String[] args) {
        // springboot 应用的核心api入口
//        SpringApplication.run(Sb06MainApplication.class, args);


        // 自定义写法
        // 1. 创建 SpringApplication
        SpringApplication springApplication = new SpringApplication(Sb06MainApplication.class);

        // 2. 调整 SpringApplication 参数，配置文件的优先级高
//        springApplication.setDefaultProperties();


        // 3. 运行
        ConfigurableApplicationContext context = springApplication.run(args);
        try {
            Sheep sheep = context.getBean(Sheep.class);
            log.info("sheep: {}", sheep);
            Cat cat = context.getBean(Cat.class);
            log.info("cat: {}", cat);
            Dog dog = context.getBean(Dog.class);
            log.info("dog: {}", dog);
            Pig pig = context.getBean(Pig.class);
            log.info("pig: {}", pig);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Profiles
         * 标识环境，默认 default 环境
         *  1. 区分 dev 开发环境 test 测试环境 prod 生产环境
         *  2. 指定组件在那个环境生效
         *  3. 默认只有激活指定环境，这些组件才生效
         * 激活环境
         *  配置文件激活
         *      spring.profiles.active=dev,test
         *  jar命令激活
         *      java -jar xxx.jar --spring.profiles.active=prod
         * 配置文件
         *  application.properties 主配置文件，任何情况下都生效
         *  其他Profile环境下命名规范：application-{profile标识}.properties, 比如：application-dev.properties
         *  激活指定环境即可：配置文件激活、命令行激活
         *  效果：
         *    项目的所有生效配置项 = 激活环境配置文件的所有项 + 主配置文件和激活文件不冲突的所有项
         *    如果发生了配置冲突，以激活的环境配置文件为准。
         *    application-{profile标识}.properties 优先级高于 application.properties
         *    主配置和激活的配置都生效，优先以激活的配置为准
         */
    }
}
