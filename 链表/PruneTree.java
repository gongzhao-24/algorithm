/**
* 描述：
* 创建日期：2022年07月21 09:52:
* @author gong zhao 
**/
package 链表;

/**
 * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
 * 
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * 
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 * 
 */

public class PruneTree extends AbstractBaseClass {
    public TreeNode pruneTree(TreeNode root) {
        // 如果两个子树都不包含1，而且根节点本身也不包含1，那么就返回null
        if (root == null) {
            return null;
        }
        TreeNode left = pruneTree(root.left);
        TreeNode right = pruneTree(root.right);
        if (left == null && right == null) {
            if (root.val == 0) {
                root = null;
            } else {
                root.left = null;
                right.right = null;

            }
        } else {
            root.left = left;
            right.right = right;
        }
        return root;

    }
}
