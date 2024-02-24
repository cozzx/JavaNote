package test.inner;

/**
 * @author zt
 * @date 2023/8/17
 **/
public class People {

    String name = "Tom";
    int age = 1;

    public void eat() {
        System.out.println("人吃饭");
    }

    // 静态的成员内部类
    static class Dog {
        public void eat() {
            System.out.println("狗吃骨头");
        }
    }

    // 非静态的成员内部类
    class Bird {
        String name = "啄木鸟";

        public void eat() {
            System.out.println("鸟吃虫子");
        }

        public void show(String name) {
            System.out.println("age = " + age);
            System.out.println("name = " + name);
            System.out.println("name = " + this.name);
            System.out.println("name = " + People.this.name);
        }

        public void show1() {
            eat();
            this.eat();
            People.this.eat();
        }
    }


    public void method() {
        // 局部内部类
        class InnerClass1 {

        }
    }

    public People() {
        // 局部内部类
        class InnerClass1 {

        }
    }

    {
        // 局部内部类
        class InnerClass1 {

        }
    }
}
