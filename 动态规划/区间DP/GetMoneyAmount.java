/**
* 描述：leetcode 375
* 创建日期：2022年07月06 22:40:
* @author gong zhao 
**/
package 动态规划.区间DP;

public class GetMoneyAmount {
    public static int getMoneyAmount(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        // dp[i][j]表示范围为 [i, j]的最小现金数
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][i] = 0;
            if (i < n) {
                dp[i][i + 1] = i;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int left = i;
                int right = i + len - 1;
                dp[left][right] = Integer.MAX_VALUE;
                for (int j = left + 1; j < right; j++) {
                    int cost = Math.max(dp[left][j-1], dp[j + 1][right]) + j;
                    dp[left][right] = Math.min(cost, dp[left][right]);
                }

            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(getMoneyAmount(10));
    }
}
