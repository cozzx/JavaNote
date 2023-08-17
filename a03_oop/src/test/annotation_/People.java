package test.annotation_;

/**
 * @author zt
 * @since 2023/6/27 18:21
 **/
@MyAnno(value = "class")
class People {
    String name;
    int age;

    @MyAnno()
    public People() {
    }

    @Deprecated
    public People(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println("人吃饭");
    }

    public void walk() {
        System.out.println("人走路");
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
