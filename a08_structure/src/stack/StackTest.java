package stack;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 栈测试
 *
 * @author zt
 * @since 2023/7/4 07:14
 **/
public class StackTest {

    /**
     * Stack 栈
     */
    @Test
    public void test1() {

        Stack<Integer> integerStack = new Stack<>();
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);

        System.out.println(integerStack);
        System.out.println(integerStack.peek());
        System.out.println(integerStack.pop());
        System.out.println(integerStack);
        System.out.println(integerStack.empty());
        System.out.println(integerStack.isEmpty());
        integerStack.clear();
        System.out.println(integerStack);
        System.out.println(integerStack.empty());
        System.out.println(integerStack.isEmpty());
    }

    /**
     * LinkedList 栈
     */
    @Test
    public void test2() {
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        integerLinkedList.push(1);
        integerLinkedList.push(2);
        integerLinkedList.push(3);

        System.out.println(integerLinkedList);
        System.out.println(integerLinkedList.peek());
        System.out.println(integerLinkedList.pop());
        System.out.println(integerLinkedList);
        System.out.println(integerLinkedList.isEmpty());
        integerLinkedList.clear();
        System.out.println(integerLinkedList);
        System.out.println(integerLinkedList.isEmpty());
    }

    /**
     * 自定义栈
     */
    @Test
    public void test3() throws Exception {
        StackCustom stackCustom = new StackCustom();
        stackCustom.push(1);
        stackCustom.push(2);
        stackCustom.push(3);
        System.out.println(stackCustom);
        System.out.println(stackCustom.pop());
        System.out.println(stackCustom);

    }
}
