/**
* 描述：
* 创建日期：2022年06月27 20:39:
* @author gong zhao 
**/
package 动态规划.区间DP;

public class MaxCoins {
    public static int maxCoins(int[] nums) {
        int[] array = new int[nums.length + 2];
        System.arraycopy(nums, 0, array, 1, nums.length);
        array[0] = 1;
        array[nums.length + 1] = 1;
        // 假如nums.len = 5, array.length = 7
        // 1 3 1 5 8 2 1
        int[][] dp = new int[array.length][array.length];
        // dp[i][j]，表示闭区间 i 到 j 的硬币最大数量
        for (int i = 0; i <= nums.length; i++) {
            for (int j = 1; i + j <= nums.length; j++) {
                int left = j;
                int right = i + j;
                if (left == right) {
                    dp[left][right] = array[left - 1] * array[left] * array[left + 1];
                } else {
                    for (int k = left; k <= right; k++) {
                        int leftCoin = (k == left ? 0 : dp[left][k - 1]);
                        int midCoin = array[left - 1] * array[k] * array[right + 1];
                        int rightCoin = (k == right ? 0 : dp[k + 1][right]);
                        dp[left][right] = Math.max(dp[left][right], leftCoin + midCoin + rightCoin);
                    }
                }

            }
        }
        return dp[1][nums.length];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,1,5,8};
        System.out.println(maxCoins(nums));
    }
}
