/**
* 描述：leetcoce 剑指 Offer II 091. 粉刷房子
* 创建日期：2022年06月25 15:50:
* @author gong zhao 
**/
package 动态规划.其他;

public class MinCost {
    public int minCost(int[][] costs) {
        int len = costs.length;
        int[][] dp = new int[len][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            minCost = Math.min(minCost, dp[len - 1][i]);
        }
        return minCost;
    }
}
