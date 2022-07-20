/**
* 描述：leetcode 583. 两个字符串的删除操作
* 创建日期：2022年07月19 19:20:
* @author gong zhao 
**/
package 动态规划;

public class MinDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // dp[0][0] = 0;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }
        // 边界情况处理好了
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
