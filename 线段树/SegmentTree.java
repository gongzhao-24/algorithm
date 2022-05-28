package 线段树;

/**
 * 描述：动态开点线段树基本模型
 * 创建日期：2022年05月28 16:05:
 * 
 * @author gong zhao
 **/
public class SegmentTree {
    class Node {
        Node leftChild;
        Node rightChild;
        // 颜色 1：蓝色，2：红色, 0:子节点中红色蓝色都有
        int color;
        // 懒加载标志 0:没有懒加载，1：蓝色，2：红色

        int add;
    }

    public int query(Node node, int treeLeft, int treeRight, int left, int right, int color) {
        if (treeLeft == left && treeRight == right) {
            if (node.color == color) {
                return right - left + 1;
            } else if (node.color != 0) {
                return 0;
            }
        }
        int mid = (treeLeft + treeRight) >> 1;
        if (right <= mid) {
            return query(node.leftChild, treeLeft, mid, left, right, color);
        } else if (left > mid) {
            return query(node.rightChild, mid + 1, treeRight, left, right, color);
        } else {
            return query(node.leftChild, treeLeft, mid, left, mid, color) + query(node.rightChild, mid + 1, treeRight, mid + 1, right, color);
        }

    }

    public void pushDown(Node node) {
        if (node.leftChild == null) {
            node.leftChild = new Node();
        }
        if (node.rightChild == null) {
            node.rightChild = new Node();
        }
        if (node.add == 0) {
            return;
        }
        node.leftChild.color = node.add;
        node.leftChild.add = node.add;
        node.rightChild.color = node.add;
        node.rightChild.add = node.add;
        node.add = 0;
    }

    public void update(Node node, int treeLeft, int treeRight, int left, int right, int color) {
        if (treeLeft == left && treeRight == right) {
            node.color = color;
            node.add = color;
            return;
        }
        int mid = (treeLeft + treeRight) >> 1;
        if (mid >= right) {
            update(node.leftChild, treeLeft, mid, left, right, color);
        } else if (left > mid) {
            update(node.rightChild, mid + 1, treeRight, left, right, color);
        } else {
            update(node.leftChild, treeLeft, mid, left, mid, color);
            update(node.rightChild, mid + 1, treeRight, mid + 1, right, color);
        }

    }

    public void pushUp(Node node) {
        if (node.leftChild.color == 1 && node.rightChild.color == 1) {
            node.color = 1;
        } else if (node.leftChild.color == 2 && node.rightChild.color == 2) {
            node.color = 2;
        } else {
            node.color = 0;
        }
    }

}
