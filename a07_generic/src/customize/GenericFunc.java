package customize;

import common.Circle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 单独定义泛型方法
 *
 * @author zt
 * @date 2023/7/5 20:42
 **/
public class GenericFunc {

    public <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o);
        }
    }

    @Test
    public void test1() {
        Object[] objectArray = new Object[100];
        Collection<Object> objectCollection = new ArrayList<>();
        fromArrayToCollection(objectArray, objectCollection);

        String[] stringArray = new String[20];
        Collection<String> stringCollection = new ArrayList<>();
        fromArrayToCollection(stringArray, stringCollection);

        Collection<Double> doubleCollection = new ArrayList<>();
        // 下面代码中T是Double类，但sa是String类型，编译错误。
        // fromArrayToCollection(stringArray, doubleCollection);

        // 下面代码中T是Object类型，sa是String类型，可以赋值成功。
        fromArrayToCollection(stringArray, objectCollection);
    }

    public static <T> void sort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (((Comparable<T>) arr[j]).compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    @Test
    public void test2() {
        int[] arr = {3, 2, 5, 1, 4};
        // 错误，因为int[]不是对象数组
//        GenericFunc.sort(arr);

        String[] strings = {"hello", "java", "song"};
        GenericFunc.sort(strings);
        System.out.println(Arrays.toString(strings));

        Circle[] circles = {new Circle(2.0), new Circle(1.2), new Circle(3.0)};
        //编译通过，运行报错，因为Circle没有实现Comparable接口
//        GenericFunc.sort(circles);
    }
}
