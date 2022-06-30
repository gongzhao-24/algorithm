/**
* 描述：leetcode 115
* 创建日期：2022年06月30 16:17:
* @author gong zhao 
**/
package 动态规划;

public class NumDistinct {
    public int numDistinct(String s, String t) {
        // s的子序列中包含t的个数，就是说s中有多少种不同的子序列 = t
        // dp[i][j] 表示从s中0-i与t中从0-j 的字符串匹配中，s这部分字符串中有多少种不同的子序列包含t。
        int lenS = s.length();
        int lenT = t.length();
        if (lenS < lenT) {
            return 0;
        }
        int[][] dp = new int[lenS + 1][lenT + 1];
        for (int i = 0; i <= lenS; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenT; j++) {
                if (j <= i) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[lenS][lenT];
    }
}
