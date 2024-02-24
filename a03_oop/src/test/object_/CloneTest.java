package test.object_;

/**
 * Object 类中的 clone()
 *
 * @author zt
 * @date 2024/1/11
 **/
public class CloneTest {
    public static void main(String[] args) {
        Animal a1 = new Animal("花花");
        try {
            Animal a2 = (Animal) a1.clone();
            System.out.println("原始对象：" + a1);
            a2.setName("毛毛");
            System.out.println("clone之后的对象：" + a2);
            System.out.println("原始对象：" + a1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

class Animal implements Cloneable {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}