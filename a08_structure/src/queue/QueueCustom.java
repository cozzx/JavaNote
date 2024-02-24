package queue;

import java.util.Arrays;

/**
 * 使用数组实现队列
 *
 * @author zt
 * @date 2023/7/4 07:48
 **/
public class QueueCustom {

    Object[] elements;

    int size;

    public QueueCustom(int cap) {
        elements = new Object[cap];
    }

    /**
     * 入队列
     */
    public void add(Object e) {
        if (size >= elements.length) {
            throw new RuntimeException("队列已满，添加失败");
        }
        elements[size++] = e;
    }

    /**
     * 出队列
     */
    public Object poll() {
        if (size <= 0) {
            throw new RuntimeException("队列已空，获取失败");
        }
        Object obj = elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return obj;
    }

    @Override
    public String toString() {
        return "QueueCustom{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }
}
