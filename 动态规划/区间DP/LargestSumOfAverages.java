/**
* 描述：leetcode 813
* 创建日期：2022年06月28 23:11:
* @author gong zhao 
**/
package 动态规划.区间DP;

public class LargestSumOfAverages {
    public static double largestSumOfAverages(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        double[][][] dp = new double[n + 1][n + 1][k + 1];
        // 一维表示划分数组的长度
        for (int len = 1; len <= n; len++) {
            // 二维表示七点位置
            for (int i = 1; i + len - 1 <= n; i++) {
                int left = i;
                int right = i + len - 1;
                if (left == right) {
                    dp[left][right][1] = nums[left - 1];
                } else {
                    // 第三位表示划分的数组堆数，同样的，左边划分为1堆，右边m-1堆
                    for (int m = 2; m <= k; m++) {
                        for (int p = left; right - p >= m - 1; p++) {
                            double cost = dp[left][p][1] + dp[p + 1][right][m - 1];
                            dp[left][right][m] = Math.max(dp[left][right][m], cost);
                        }
                    }
                    dp[left][right][1] = (double)(sum[right] - sum[left - 1]) / (double)(right - left + 1);
                }
            }
        }
        return dp[1][n][k];

        /**
         * dp[i][j][k],从i到j 划分为k个相邻的非空子数组的平均值总和
         */
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int k = 4;

        System.out.println(largestSumOfAverages(nums, k));

    }
}
