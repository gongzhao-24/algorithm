/**
* 描述：leetcode 410
* 创建日期：2022年07月07 15:09:
* @author gong zhao 
**/
package 动态规划;

import java.util.Arrays;

public class SplitArray {
    public int splitArray(int[] nums, int m) {

        /**
         * dp[i][j] 表示将前i个数字，划分为m个子数组中，和的最大值的最小值
         */
        int len = nums.length;
        int[] sum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        if (m == 1) {
            return sum[len];
        }

        int[][] dp = new int[len + 1][m + 1];
        for (int[] array : dp) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        dp[1][1] = nums[0];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= m && j <= i; j++) {
                // 将数组划分为两部分 [1,k],[k + 1,i]
                // 需要保证，1，k 之间，能构成 m- 1个数组
                // k >= m - 1
                if (j == 1) {
                    dp[i][j] = sum[i];
                    continue;
                }

                /**
                 * 因为需要将数组分为两部分，左边的长度为j-1，右边的长度为1，
                 */
                for (int k = j - 1; k <= i - 1; k++) {
                    if (k < 1) {
                        continue;
                    }
                    int left = dp[k][j - 1];
                    int right = (sum[i] - sum[k]);
                    int cur = Math.max(left, right);
                    dp[i][j] = Math.min(cur, dp[i][j]);
                }
            }
        }
        return dp[len][m];
    }

    public static void main(String[] args) {
        SplitArray splitArray = new SplitArray();
        int[] nums = new int[] { 1,4,4 };
        int m = 3;
        System.out.println(splitArray.splitArray(nums, m));
    }
}
