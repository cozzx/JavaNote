package completable;

import common.Student;

/**
 * @author zt
 * @date 2024/1/13
 **/
public class ChainTest {
    public static void main(String[] args) {
        Student student = new Student();
        // 需要类中开启链式调用, 使用 lombok.experimental.Accessors 注解, 设置 (chain = true)
        student.setId(1).setName("z3").setMajor("english");
    }
}
