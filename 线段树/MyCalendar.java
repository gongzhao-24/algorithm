/**
* 描述：leetcode 729. 我的日程安排表 I
* 创建日期：2022年06月20 21:25:
* @author gong zhao 
**/
package 线段树;

public class MyCalendar {
    private static int LEFT = 0;
    private static int RIGHT = (int) 1e9 + 10;

    Node root;

    public MyCalendar() {
        root = new Node();
    }

    public boolean book(int start, int end) {
        if (!canBook(root, LEFT, RIGHT, start, end - 1)) {
            return false;
        }
        update(root, LEFT, RIGHT, start, end - 1);
        return true;
    }

    class Node {
        Node left;
        Node right;
        // 0 没有预订，1：已经预订，2：区间部分预订
        int isBook;
        // true:预订，false：没有预订
        boolean flag;
    }

    /**
     * 线段树一般需要实现下面几个方法
     * ①：区间更新 update
     * ②：区间查询
     * ③：往下扩展
     * ④：往上扩展
     */

    public void update(Node node, int treeLeft, int treeRight, int left, int right) {
        if (treeLeft == left && treeRight == right) {
            node.isBook = 1;
            node.flag = true;
            return;
        }
        pushDown(node);
        int mid = treeLeft + ((treeRight - treeLeft) >> 1);
        if (mid >= right) {
            update(node.left, treeLeft, mid, left, right);
        } else if (left > mid) {
            update(node.right, mid + 1, treeRight, left, right);
        } else {
            // 只要有一边不能预订，那就是不能预订
            update(node.left, treeLeft, mid, left, mid);
            update(node.right, mid + 1, treeRight, mid + 1, right);
        }
        pushUp(node);
    }

    public void pushUp(Node node) {
        if (node.left.isBook == 0 && node.right.isBook == 0) {
            node.isBook = 0;
        } else if (node.left.isBook == 1 && node.right.isBook == 1) {
            node.isBook = 1;
        } else {
            node.isBook = 2;
        }
    }

    public boolean canBook(Node node, int treeLeft, int treeRight, int left, int right) {
        if (treeLeft == left && treeRight == right) {
            // 没有被预订，所以才可以预订
            return node.isBook == 0;
        }
        pushDown(node);
        int mid = treeLeft + ((treeRight - treeLeft) >> 1);
        if (mid >= right) {
            return canBook(node.left, treeLeft, mid, left, right);
        } else if (left > mid) {
            return canBook(node.right, mid + 1, treeRight, left, right);
        } else {
            // 只要有一边不能预订，那就是不能预订
            return canBook(node.left, treeLeft, mid, left, mid)
                    && canBook(node.right, mid + 1, treeRight, mid + 1, right);
        }
    }

    public void pushDown(Node node) {
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        if (node.flag) {
            node.left.isBook = 1;
            node.left.flag = true;
            node.right.isBook = 1;
            node.right.flag = true;
        }
        node.flag = false;
    }
}
