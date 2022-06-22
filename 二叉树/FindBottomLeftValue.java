/**
* 描述：leetcode 513
* 创建日期：2022年06月22 11:19:
* @author gong zhao 
**/
package 二叉树;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class FindBottomLeftValue extends AbstractBaseClass {
    public int findBottomLeftValue(TreeNode root) {
        // 层序遍历
        int res = 0;
        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    res = node.val;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }
}
