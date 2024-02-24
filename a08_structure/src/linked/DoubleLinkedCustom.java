package linked;

/**
 * 自定义双链表
 *
 * @author zt
 * @date 2023/7/3 21:23
 **/
public class DoubleLinkedCustom<E> {

    // 节点类
    class Node {

        E data;

        Node preNode;

        Node nextNode;

        public Node(E e, Node pre, Node next) {
            data = e;
            preNode = pre;
            nextNode = next;
        }
    }

    // 头结点
    private Node header;

    private int size;

    /**
     * 添加元素
     */
    public void add(E e) {

        if (header == null) {
            header =  new Node(e, null, null);
        } else {
            Node endNode = findEnd(header);
            endNode.nextNode = new Node(e, endNode, null);
        }
        size++;
    }

    /**
     * 查找链表最后一个元素
     */
    private Node findEnd(Node node) {
        if (node.nextNode == null) {
            return node;
        }
        return findEnd(node.nextNode);
    }

    /**
     * 查找元素是否存在
     */
    public boolean contains(E e) {
        if (header == null) {
            return false;
        }
        if (header.data.equals(e)) {
            return true;
        }
        Node curNode = header.nextNode;
        while (curNode != null) {
            if (curNode.data.equals(e)) {
                return true;
            }
            curNode = curNode.nextNode;
        }
        return false;
    }

    /**
     * 修改链表中某个数据
     */
    public boolean modify(E o, E n) {
        if (header == null) {
            return false;
        }
        if (header.data.equals(o)) {
            header.data = n;
            return true;
        }
        Node curNode = header.nextNode;
        while (curNode != null) {
            if (curNode.data.equals(o)) {
                curNode.data = n;
                return true;
            }
            curNode = curNode.nextNode;
        }
        return false;
    }

    /**
     * 删除链表中某个元素的方法
     */
    public boolean remove(E e) {
        if (header == null) {
            return false;
        }
        if (header.data.equals(e)) {
            header = header.nextNode;
            header.preNode = null;
            size--;
            return true;
        }
        Node preNode = header;
        Node curNode = header.nextNode;
        while (curNode != null) {
            if (curNode.data.equals(e)) {
                curNode.nextNode.preNode = preNode;
                preNode.nextNode = curNode.nextNode;
                size--;
                return true;
            }
            preNode = curNode;
            curNode = curNode.nextNode;
        }
        return false;
    }

    /**
     * 输出链表中所有数据
     */
    public void print() {
        StringBuilder stringBuilder = new StringBuilder("{");
        if (header != null) {
            stringBuilder.append(header.data);
        }
        Node curNode = header.nextNode;
        while (curNode != null) {
            stringBuilder.append(",").append(curNode.data);
            curNode = curNode.nextNode;
        }
        stringBuilder.append("}");
        System.out.println(stringBuilder);
    }
}
