/**
* 描述：leeetcode 剑指 Offer II 029. 排序的循环链表
* 创建日期：2022年06月18 10:16:
* @author gong zhao 
**/
package 每日一题;

public class Insert {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal, null);
            head.next = head;
            return head;
        }
        // 当前节点
        Node current = head;
        // 当前节点的下一个节点
        Node next = head.next;
        while (true) {
            if ((current.val <= insertVal && insertVal <= next.val && current.val < next.val)
                    || (current.val <= insertVal && insertVal >= next.val && current.val > next.val)
                    || (current.val >= insertVal && insertVal <= next.val && current.val > next.val)
                    || (next == head && current.val == next.val)) {
                // 这里判断，如果我已经找到队尾了，即又找到开头了，发现还是相等，
                Node inserNode = new Node(insertVal);
                inserNode.next = next;
                current.next = inserNode;
                break;
            }
            current = current.next;
            next = next.next;
        }
        return head;
        /**
         * 想一下有哪几种可能性 current next
         * ①：大于current，小于next，肯定可以
         * ②：大于current，大于next，current 大于next
         * ③：小于current 小于next，next 小于 current
         */
    }
}

class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};