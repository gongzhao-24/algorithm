/**
* 描述：
* 创建日期：2022年07月06 15:09:
* @author gong zhao 
**/
package 动态规划;

import java.util.TreeSet;

public class MaxSumSubmatrix {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            // 表示每列的值，每次重新枚举上边界的时候，会把这个数组清空
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界

                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                /**
                 * 就是找到一个区间，最接近 k，
                 */

                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    // s - k <= ceil
                    // k >= s - ceil;

                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 4, 5 };
        int[] sum = new int[] { 1, 3, 6, 10, 15 };
        TreeSet<Integer> sumSet = new TreeSet<Integer>();
        int k = 9;
        sumSet.add(0);
        int s = 0;
        int ans = 0;
        for (int v : sum) {
            s = v;
            Integer ceil = sumSet.ceiling(s - k);
            if (ceil != null) {
                ans = Math.max(ans, s - ceil);
            }
            sumSet.add(s);
        }
    }
}
