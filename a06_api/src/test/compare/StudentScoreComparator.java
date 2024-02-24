package test.compare;

import java.util.Comparator;

/**
 * 自定义比较器
 *
 * @author zt
 * @date 2023/7/25
 **/
public class StudentScoreComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o2.getScore() - o1.getScore();
    }
}
