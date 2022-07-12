/**
* 描述：
* 创建日期：2022年07月09 22:31:
* @author gong zhao 
**/
package 双周赛.w82;

public class EvaluateTree extends AbstractBaseClass {
    public boolean evaluateTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == 1;
        }
        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);
        if (root.val == 2) {
            return left || right;
        } else {
            return left && right;
        }
    }
}
