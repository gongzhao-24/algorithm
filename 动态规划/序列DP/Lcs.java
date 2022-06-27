/**
* 描述：最长公共子序列 leetcode 1143
* 创建日期：2022年06月27 16:51:
* @author gong zhao 
**/
package 动态规划.序列DP;

/**
 * 这是模板题，需要好好研究一下
 */

public class Lcs {
    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // dp[i][j] 表示 text1中前i个字符与text2中前j个字符 的最长公共子序列的长度
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abc", "abc"));
    }
}
