package test.compare;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author zt
 * @date 2023/7/25
 **/
public class ComparableTest {

    @Test
    public void test1() {
        Student[] studentArr = new Student[5];
        studentArr[0] = new Student(3, "张三", 90, 23);
        studentArr[1] = new Student(1, "熊大", 100, 22);
        studentArr[2] = new Student(5, "王五", 75, 25);
        studentArr[3] = new Student(4, "李四", 85, 24);
        studentArr[4] = new Student(2, "熊二", 85, 18);

        // 单独比较两个对象
        System.out.println(studentArr[0].compareTo(studentArr[1]));
        System.out.println(studentArr[1].compareTo(studentArr[2]));
        System.out.println(studentArr[2].compareTo(studentArr[2]));

        System.out.println("所有学生：");
        for (int i = 0; i < studentArr.length; i++) {
            System.out.println(studentArr[i]);
        }

        System.out.println("按照学号排序：");
        Arrays.sort(studentArr);
        for (Student student : studentArr) {
            System.out.println(student);
        }
    }
}
