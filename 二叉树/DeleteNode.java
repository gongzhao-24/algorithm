/**
* 描述：
* 创建日期：2022年06月02 18:22:
* @author gong zhao 
**/
package 二叉树;

public class DeleteNode extends AbstractBaseClass {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            } else {
                TreeNode rifhtLeft = right;
                while (rifhtLeft.left != null) {
                    rifhtLeft = rifhtLeft.left;
                }
                rifhtLeft.left = left;
                return right;
            }

        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;

    }

}
