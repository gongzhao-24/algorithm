/**
* 描述：leetcode 940
* 创建日期：2022年10月14 14:36:
* @author gong zhao 
**/
package 动态规划;

import java.util.Arrays;

import 每日一题.HeightChecker;

public class DistinctSubseqII {
    public int distinctSubseqII(String s) {
        /**
         * 分析：
         * 可以很明显的看到，若s[i] = x;那么以字符x结尾的子序列的个数为：
         * dp[0] + dp[1] + .... + dp[i - 1] + 1;
         * 如果字符串中有相同的字符，那么问题就需要重新考虑了。
         * 假如说字符串中有两个相同的字符位序分别为x、y，x < y
         * 那么以y结尾的字符串中，一定包含了以x结尾的字符串。
         */
        int mod = (int) 1e9 + 7;

        int len = s.length();
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        int[] lastAppear = new int[26];
        Arrays.fill(lastAppear, -1);

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 26; j++) {
                if (lastAppear[j] != -1) {
                    dp[i] = (dp[i] + dp[lastAppear[j]]) % mod;
                }
            }
            lastAppear[s.charAt(i) - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (lastAppear[i] != -1) {
                ans = (ans + dp[lastAppear[i]]) % mod;
            }
        }
        return ans;
    }

    /**
     * 优化
     * 
     * @param s
     * @return
     */
    public int distinctSubseqII2(String s) {
        final int MOD = (int) 1e9 + 7;
        //g[i] 表示以 i 为结尾的字符序列个数
        int[] g = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int total = 1;
            for (int j = 0; j < 26; ++j) {
                total = (total + g[j]) % MOD;
            }
            g[s.charAt(i) - 'a'] = total;
        }

        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            ans = (ans + g[i]) % MOD;
        }
        return ans;
    }

}
