package test.abstract_;

import java.util.Date;

/**
 * 人类
 *
 * @author zt
 * @since 2023/6/27 18:21
 **/
public class People extends Creature {

    // 成员变量 - 实例变量
    public String name;
    public int age = 0;
    public char gender;
    public Date birthday;

    // 成员变量 - 类变量（静态变量）
    public static int limbs;

    // 非静态代码块，晚于静态代码块执行
    {
        System.out.println("People非静态代码块");
        name = "";
    }

    // 静态代码块
    static {
        System.out.println("People静态代码块");
        limbs = 4;
    }

    // 空参构造器
    public People() {
        System.out.println("People空参构造器");
    }

    // 带参构造器
    public People(String name, int age, char gender, Date birthday) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        System.out.println("People带参构造器");
    }

    // 实例方法
    public void eat() {
        // 局部变量
        String food = "奶";
        System.out.println("今天的饭是" + food);
        System.out.println("吃饭");
    }

    // 实例方法
    public void sleep() {
        System.out.println("今年" + age + "岁了，睡觉涨的快");
    }

    // 类方法（静态方法）
    public static void getLimbs() {
        System.out.println("我有" + limbs + "条胳膊腿");
    }

}
