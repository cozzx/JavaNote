package bean.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试依赖注入，对象属性赋值交由 spring 处理
 *
 * @author zt
 * @date 2023/8/18
 **/
public class DITest {

    /**
     * setter 注入
     */
    @Test
    public void testDIBySet() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Student studentOne = applicationContext.getBean("studentOne", Student.class);
        System.out.println(studentOne);
    }

    /**
     * constructor 注入
     */
    @Test
    public void testDIByConstructor() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Student studentTwo = applicationContext.getBean("studentTwo", Student.class);
        System.out.println(studentTwo);
    }

    /**
     * 注入时特殊情况的处理，详见 spring-di.xml
     */
    @Test
    public void testDIBySetSpecial() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Student studentThree = applicationContext.getBean("studentThree", Student.class);
        System.out.println(studentThree);
    }

    /**
     * 为对象类型的属性赋值，方式一：引用外部bean，详见 spring-di.xml
     */
    @Test
    public void testDIBySetObj1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Student studentFour = applicationContext.getBean("studentFour", Student.class);
        System.out.println(studentFour);
    }

    /**
     * 为对象类型的属性赋值，方式二：内部bean，详见 spring-di.xml
     */
    @Test
    public void testDIBySetObj2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Student studentFive = applicationContext.getBean("studentFive", Student.class);
        System.out.println(studentFive);
    }

    /**
     * 为对象类型的属性赋值，方式三：级联属性，详见 spring-di.xml
     */
    @Test
    public void testDIBySetObj3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Student studentSix = applicationContext.getBean("studentSix", Student.class);
        System.out.println(studentSix);
    }

    /**
     * 为数组类型的属性赋值，详见 spring-di.xml
     */
    @Test
    public void testDIBySetArr() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Student studentSeven = applicationContext.getBean("studentSeven", Student.class);
        System.out.println(studentSeven);
    }

    /**
     * 为集合类型的属性赋值，详见 spring-di.xml
     */
    @Test
    public void testDIBySetCol() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Clazz clazzTwo = applicationContext.getBean("clazzTwo", Clazz.class);
        System.out.println(clazzTwo);
    }

    /**
     * 为Map类型的属性赋值，详见 spring-di.xml
     */
    @Test
    public void testDIBySetMap() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di.xml");
        Student studentEight = applicationContext.getBean("studentEight", Student.class);
        System.out.println(studentEight);
    }

    /**
     * 引用方式赋值，详见 spring-di-ref.xml
     */
    @Test
    public void testDIBySetRef() {
        // 引用集合
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di-ref.xml");
        Clazz clazzOne = applicationContext.getBean("clazzOne", Clazz.class);
        System.out.println(clazzOne);
        // 引用Map
        Student studentFour = applicationContext.getBean("studentFour", Student.class);
        System.out.println(studentFour);
        // 命名空间
        Student studentFive = applicationContext.getBean("studentFive", Student.class);
        System.out.println(studentFive);
        // 引入文件
        try {
            DataSource druidDataSource = applicationContext.getBean("druidDataSource", DataSource.class);
            Connection connection = druidDataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
