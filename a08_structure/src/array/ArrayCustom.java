package array;

/**
 * 自定义数据基础功能
 *
 * @author zt
 * @date 2023/7/3 19:09
 **/
public class ArrayCustom {

    private Object[] elements;

    private int size;

    public ArrayCustom(int capacity) {
        elements = new Object[capacity];
    }

    /**
     * 添加元素
     */
    public void add(Object e) {
        if (size >= elements.length) {
            throw new RuntimeException("The array is full and cannot be added");
        }
        elements[size++] = e;
    }

    /**
     * 查询元素在数组中的索引位置
     */
    public int find(Object e) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从当前数组中移除首次出现的元素
     */
    public boolean delete(Object e) {
        int index = find(e);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size--] = null;
        return true;
    }

    /**
     * 将数组中首次出现的oldValue替换为newValue
     */
    public boolean update(Object o, Object n) {
        int index = find(o);
        if (index == -1) {
            return false;
        }
        elements[index] = n;
        return true;
    }

    /**
     * 输出数组中所有数据
     */
    public void print() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]);
            if (i == size - 1) {
                stringBuilder.append("}");
            } else {
                stringBuilder.append(",");
            }
        }
        System.out.println(stringBuilder);
    }
}
