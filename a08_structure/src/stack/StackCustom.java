package stack;

import java.util.Arrays;

/**
 * 自定义栈
 *
 * @author zt
 * @since 2023/7/4 07:23
 **/
public class StackCustom {

    private Object[] elements;

    private int index;

    public StackCustom() {
        this.elements = new Object[10];
        this.index = -1;
    }

    /**
     * 压栈
     */
    public void push(Object obj) throws Exception {
        if (index >= elements.length - 1) {
            throw new Exception("stack overflow");
        }
        index++;
        elements[index] = obj;
        System.out.println("压栈" + obj + "元素成功，栈帧指向" + index);
    }

    /**
     * 弹栈
     */
    public Object pop() throws Exception {
        if (index < 0) {
            throw new Exception("stack empty");
        }
        Object obj = elements[index];
        System.out.print("弹栈" + obj + "元素成功，");
        elements[index] = null;
        index--;
        return obj;
    }

    @Override
    public String toString() {
        return "StackCustom{" +
                "elements=" + Arrays.toString(elements) +
                ", index=" + index +
                '}';
    }
}
