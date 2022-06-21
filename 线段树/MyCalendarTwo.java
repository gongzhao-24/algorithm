/**
* 描述：leetcode 731. 我的日程安排表 II
* 创建日期：2022年06月20 21:46:
* @author gong zhao 
**/
package 线段树;

public class MyCalendarTwo {
    private static int LEFT = 0;
    private static int RIGHT = (int) 1e9 + 10;

    Node root;

    public MyCalendarTwo() {
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
        // 记录区间最大值
        int max;
        // true:预订，false：没有预订
        int flag;
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
            node.max++;
            node.flag++;
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
        node.max = Math.max(node.left.max, node.right.max);
    }

    public boolean canBook(Node node, int treeLeft, int treeRight, int left, int right) {
        if (treeLeft == left && treeRight == right) {
            // 预订次数小于2才可以，否则，就要有三次预订
            return node.max < 2;
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
        if (node.flag > 0) {
            node.left.max += node.flag;
            node.left.flag += node.flag;
            node.right.max += node.flag;
            node.right.flag += node.flag;
        }
        node.flag = 0;
    }
}
