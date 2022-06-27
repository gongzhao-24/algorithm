/**
* 描述：leetcode 377
* 创建日期：2022年06月24 14:56:
* @author gong zhao 
**/
package 动态规划.背包问题;

public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
