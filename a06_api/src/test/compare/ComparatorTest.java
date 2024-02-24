package test.compare;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zt
 * @date 2023/7/25
 **/
public class ComparatorTest {

    @Test
    public void test1() {

        Student[] studentArr = new Student[5];
        studentArr[0] = new Student(3, "张三", 90, 23);
        studentArr[1] = new Student(1, "熊大", 100, 22);
        studentArr[2] = new Student(5, "王五", 75, 25);
        studentArr[3] = new Student(4, "李四", 85, 24);
        studentArr[4] = new Student(2, "熊二", 85, 18);

        System.out.println("按照成绩排序");
        StudentScoreComparator studentScoreComparator = new StudentScoreComparator();
        Arrays.sort(studentArr, studentScoreComparator);
        for (Student student : studentArr) {
            System.out.println(student);
        }

        Arrays.sort(studentArr, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println("按照年龄排序: " + Arrays.toString(studentArr));
    }
}
