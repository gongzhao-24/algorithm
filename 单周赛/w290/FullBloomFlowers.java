/**
* 描述：
* 创建日期：2022年07月16 11:33:
* @author gong zhao 
**/
package 单周赛.w290;

public class FullBloomFlowers {
    int RIGHT = (int) 1e9;
    int LEFT = 1;
    TreeNode root = new TreeNode();

    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        for (int[] flower : flowers) {
            root.update(root, LEFT, RIGHT, flower[0], flower[1], 1);
        }
        int[] ans = new int[persons.length];
        for (int i = 0; i < persons.length; i++) {
            ans[i] = root.query(root, LEFT, RIGHT, persons[i]);
        }
        return ans;
    }

    /**
     * 动态开点线段树
     */
    class TreeNode {
        TreeNode leftChild;
        TreeNode rightChild;
        int val;
        int add;

        public void update(TreeNode node, int treeLeft, int treeRight, int left, int right, int val) {
            pushDown(node);
            if (treeLeft >= left && treeRight <= right) {
                node.val += val;
                node.add += val;
                System.out.println("left:" + left + ", right:" + right + ", val:" + node.val);
                return;
            }
            int mid = treeLeft + ((treeRight - treeLeft) >> 1);
            if (mid >= right) {
                update(node.leftChild, treeLeft, mid, left, right, val);
            } else if (mid < left) {
                update(node.rightChild, mid + 1, treeRight, left, right, val);
            } else {
                update(node.leftChild, treeLeft, mid, left, mid, val);
                update(node.rightChild, mid + 1, treeRight, mid + 1, right, val);
            }
        }

        public int query(TreeNode node, int treeLeft, int treeRight, int index) {
            pushDown(node);
            if (treeLeft == index && treeRight == index) {
                return node.val;
            }
            int mid = treeLeft + ((treeRight - treeLeft) >> 1);
            if (mid >= index) {
                return query(node.leftChild, treeLeft, mid, index);
            } else {
                return query(node.rightChild, mid + 1, treeRight, index);
            }
        }

        public void pushDown(TreeNode node) {
            if (node.leftChild == null) {
                node.leftChild = new TreeNode();
            }
            if (node.rightChild == null) {
                node.rightChild = new TreeNode();
            }
            if (node.add != 0) {
                node.leftChild.val += node.add;
                node.leftChild.add += node.add;
                node.rightChild.val += node.add;
                node.rightChild.add += node.add;
            }
            node.add = 0;
        }
    }

    public static void main(String[] args) {
        FullBloomFlowers fullBloomFlowers = new FullBloomFlowers();
        int[][] flowers = new int[][] {
                { 1, 6 }, { 3, 7 }, { 9, 12 }, { 4, 13 }
        };
        int[] persons = new int[] { 2, 3, 7, 11 };
        System.out.println(fullBloomFlowers.fullBloomFlowers(flowers, persons));
    }
}
