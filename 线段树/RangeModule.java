/**
* 描述：
* 创建日期：2022年06月20 11:47:
* @author gong zhao 
**/
package 线段树;

/**
 * 思考一下线段树有哪些要点（把两种线段树都写一写，一种是动态开点，一种是常规的）
 */

public class RangeModule {
    Node root;
    int LEFT = 1;
    int RIGHT = (int) 1e9 + 10;

    public RangeModule() {
        root = new Node();
    }

    // [left, right - 1]
    public void addRange(int left, int right) {
        update(root, LEFT, RIGHT, left, right - 1, true);
    }

    public void update(Node node, int treeLeft, int treeRight, int left, int right, boolean contain) {
        if (left <= treeLeft && right >= treeRight) {
            node.contain = contain;
            node.flag = contain ? 1 : -1;
            // System.out.println( "treeLeft：" + treeLeft + ", treeRight:" + treeRight + ",
            // contain:" + node.contain + ", flag:" + node.flag);
            return;
        }
        pushDown(node);
        int mid = treeLeft + ((treeRight - treeLeft) >> 1);
        if (right <= mid) {
            update(node.left, treeLeft, mid, left, right, contain);
        } else if (left > mid) {
            update(node.right, mid + 1, treeRight, left, right, contain);
        } else {
            update(node.left, treeLeft, mid, left, mid, contain);
            update(node.right, mid + 1, treeRight, mid + 1, right, contain);
        }
        pushUp(node);
        // System.out.println("treeLeft：" + treeLeft + ", treeRight:" + treeRight + ",
        // contain:" + node.contain + ", flag:" + node.flag);
    }

    public void pushUp(Node node) {
        if (node.left.contain && node.right.contain) {
            node.contain = true;
        } else {
            node.contain = false;
        }
    }

    public void pushDown(Node node) {
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        if (node.flag != 0) {
            node.left.contain = node.contain;
            node.left.flag = node.flag;
            node.right.contain = node.contain;
            node.right.flag = node.flag;
        }
        node.flag = 0;
    }

    public boolean queryRange(int left, int right) {
        return query(root, LEFT, RIGHT, left, right - 1);
    }

    public boolean query(Node node, int treeLeft, int treeRight, int left, int right) {
        if (left == treeLeft && right == treeRight) {
            return node.contain;
        }
        pushDown(node);
        int mid = treeLeft + ((treeRight - treeLeft) >> 1);
        if (right <= mid) {
            return query(node.left, treeLeft, mid, left, right);
        } else if (left > mid) {
            return query(node.right, mid + 1, treeRight, left, right);
        } else {
            return query(node.left, treeLeft, mid, left, mid) &&
                    query(node.right, mid + 1, treeRight, mid + 1, right);
        }
    }

    public void removeRange(int left, int right) {
        update(root, LEFT, RIGHT, left, right - 1, false);
    }

    class Node {
        Node left;
        Node right;
        // 左右端点都包含则为true
        boolean contain;
        // 懒加载标志， 0 ：无懒标记，1：add懒加载，-1：remove懒加载
        int flag;
    }

    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        System.out.println(rangeModule.queryRange(21, 34));
        System.out.println(rangeModule.queryRange(27, 87));
        rangeModule.addRange(44, 53);

        rangeModule.addRange(69, 89);
        System.out.println(rangeModule.queryRange(23, 26));
        System.out.println(rangeModule.queryRange(80, 84));

        System.out.println(rangeModule.queryRange(11, 12));
        rangeModule.removeRange(86, 9);
        rangeModule.addRange(87, 94);

        rangeModule.removeRange(34, 52);
        rangeModule.addRange(1, 59);
        rangeModule.removeRange(62, 96);

        rangeModule.removeRange(34, 83);
        System.out.println(rangeModule.queryRange(11, 59));
        System.out.println(rangeModule.queryRange(59, 79));

        System.out.println(rangeModule.queryRange(1, 13));
        System.out.println(rangeModule.queryRange(21, 23));
        rangeModule.removeRange(9, 61);

        rangeModule.addRange(17, 21);
        rangeModule.removeRange(4, 8);
        System.out.println(rangeModule.queryRange(19, 25));

        rangeModule.addRange(91, 89);
        rangeModule.addRange(23, 94);
        rangeModule.removeRange(58, 95);
    }

}
