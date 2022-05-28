package 线段树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：LCP 52. 二叉搜索树染色
 * 创建日期：2022年05月28 16:30:
 * 
 * @author gong zhao
 **/
public class GetNumber extends AbstractBaseClass {
    class Node {
        Node leftChild;
        Node rightChild;
        // 颜色 1：蓝色，2：红色, 0:子节点中红色蓝色都有
        int color = 0;
        // 懒加载标志 0:没有懒加载，1：蓝色，2：红色

        int add;
    }

    Node rootNode = new Node();

    public int query(Node node, int treeLeft, int treeRight, int left, int right, int color) {
        if (treeLeft == left && treeRight == right) {
            if (node.color == color) {
                return right - left + 1;
            } else if (left == right || node.color != 0) {
                return 0;
            }
        }
        pushDown(node);
        int mid = (treeLeft + treeRight) >> 1;
        if (right <= mid) {
            return query(node.leftChild, treeLeft, mid, left, right, color);
        } else if (left > mid) {
            return query(node.rightChild, mid + 1, treeRight, left, right, color);
        } else {
            return query(node.leftChild, treeLeft, mid, left, mid, color)
                    + query(node.rightChild, mid + 1, treeRight, mid + 1, right, color);
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
        pushDown(node);
        int mid = (treeLeft + treeRight) >> 1;
        if (mid >= right) {
            update(node.leftChild, treeLeft, mid, left, right, color);
        } else if (left > mid) {
            update(node.rightChild, mid + 1, treeRight, left, right, color);
        } else {
            update(node.leftChild, treeLeft, mid, left, mid, color);
            update(node.rightChild, mid + 1, treeRight, mid + 1, right, color);
        }
        pushUp(node);
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

    public int getNumber(TreeNode root, int[][] ops) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
            map.put(list.get(i), i);
        }

        int treeLeft = 0;
        int treeRight = list.size() - 1;
        for (int[] op : ops) {
            int left = map.get(op[1]);
            int right = map.get(op[2]);
            int color = op[0] + 1;
            update(rootNode, treeLeft, treeRight, left, right, color);
        }
        return query(rootNode, treeLeft, treeRight, 0, treeRight, 2);
    }

    public void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

    public static void main(String[] args) {
        TreeNode node76 = new TreeNode(76);
        TreeNode node64 = new TreeNode(64);
        TreeNode node7 = new TreeNode(7);
        TreeNode node66 = new TreeNode(66);
        TreeNode node59 = new TreeNode(59);
        TreeNode node27 = new TreeNode(27);
        TreeNode node31 = new TreeNode(31);

        node76.left = node64;
        node64.left = node7;
        node64.right = node66;
        node7.right = node59;
        node59.left = node27;
        node27.right = node31;

        int[][] ops = new int[][] {
                { 1, 27, 31 }, { 0, 59, 59 }, { 0, 64, 64 }
        };

        GetNumber getNumber = new GetNumber();
        int countr = getNumber.getNumber(node76, ops);
        System.out.println(countr);
        /**
         * [71,64,null,7,66,null,59,null,null,27,null,null,31]
         * [[1,27,31],[0,59,59],[0,64,64]]
         */
    }

}
