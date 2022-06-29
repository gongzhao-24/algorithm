/**
* 描述：leetcode 516
* 创建日期：2022年06月29 16:13:
* @author gong zhao 
**/
package 动态规划.区间DP;

public class LongestPalindromeSubseq {
    public static int longestPalindromeSubseq(String s) {
        /**
         * dp[i][j] 表示从i到j的最长回文子序列
         */
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i + 1 < n) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = 2;
                } else {
                    dp[i][i + 1] = 1;
                }
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int left = i;
                int right = i + len - 1;
                System.out.println("left: " + (left) + ", right:" + (right));
                if (s.charAt(left) == s.charAt(right)) {
                    dp[left][right] = dp[left + 1][right - 1] + 2;
                } else {
                    dp[left][right] = Math.max(dp[left + 1][right], dp[left][right - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String str = "abcdef";
        System.out.println(longestPalindromeSubseq(str));
    }
}
