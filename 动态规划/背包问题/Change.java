/**
* 描述：leeetcode 518
* 创建日期：2022年06月24 10:58:
* @author gong zhao 
**/
package 动态规划.背包问题;

import java.util.Arrays;

public class Change {
    /**
     * 首先，肯定可以使用回溯做，但是估计时间复杂度比较高
     * 完全背包问题，
     */

    public static int change(int amount, int[] coins) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[j] += (j - coins[i - 1] < 0 ? 0 : dp[j - coins[i - 1]]);
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{10};
        int amount = 10;
        System.out.println(change(amount, coins));
    }
}
