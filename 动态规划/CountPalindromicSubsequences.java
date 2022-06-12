/**
* 描述：leetcode 730
* 创建日期：2022年06月10 09:44:
* @author gong zhao 
**/
package 动态规划;

public class CountPalindromicSubsequences {
    /**
     * 每一个回文串一定是首尾字母相等的字符串，
     * 那么我们设给定字符串为 s，长度为 n，状态 dp(x,i,j)
     * 表示在字符串区间 s[i:j] 中以字符 x 为开头和结尾的不同「回文序列」总数，
     * 其中 s[i:j] 表示字符串 s 从下标 i 到下标 j 的子串（包含下标 i 和下标 j）
     * 
     * 我们思考如何求解各个状态：
     * 
     * 当 s[i]=x 且 s[j]=x 时，那么对于 s[i+1:j-1] 中的任意一个「回文序列」,
     * 在头尾加上字符 x 都会生成一个新的以字符 x 为开头结尾的「回文序列」，
     * 并加上 xx 和 x 两个单独的「回文序列」。下式中，由于 x_k_x ,
     * 不同的「回文序列」一定互不相同，因此可以直接累加，无需考虑去重。
     * 
     * 当 s[i]=x且 s[j] !=x 时，那么 dp(x,i,j)=dp(x,i,j−1)
     * 
     * 当 s[i] !=x 且 s[j]=x 时，那么 dp(x,i,j)=dp(x,i+1,j)
     * 
     * 当 s[i] !=x 且 s[j] !=x 时，dp(x,i,j)=dp(x,i+1,j−1)
     * 
     * 上文的讨论是建立在字符串长度大于 1 的前提上的，我们还需要考虑动态规划的边界条件，即字符串长度为 1 或者不存在的情况。
     * 对于长度为 1 的字符串，它显然只有本身这一个「回文序列」。对于字符串不存在的情况，那么肯定不存在任何「回文序列」子串。
     * 因此我们就可以写出动态规划的边界条件：
     * dp(c,i,j) = 1, i = j && s[i] = c
     * dp(c,i,j) = 0, i = j && s[i] != c
     * dp(c,i,j) = 0, i > j
     * 
     * 
     * 另外一个问题是，如何进行去重的，答案就在最开始的将各种情况的首尾字符枚举中，
     * 举例，abaaba,
     * 这里有时间再研究一下
     * 
     */

    public int countPalindromicSubsequences(String s) {
        final int MOD = 1000000007;
        int n = s.length();
        int[][][] dp = new int[4][n][n];
        for (int i = 0; i < n; i++) {
            // 将所有的单个字符的 回文长度都置为1
            dp[s.charAt(i) - 'a'][i][i] = 1;
        }

        // 第一层表示字符串的长度
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                for (char c = 'a'; c <= 'd'; c++) {
                    int k = c - 'a';
                    if (s.charAt(i) == c && s.charAt(j) == c) {
                        dp[k][i][j] = (2 + (dp[0][i + 1][j - 1] + dp[1][i + 1][j - 1]) % MOD
                                + (dp[2][i + 1][j - 1] + dp[3][i + 1][j - 1]) % MOD) % MOD;
                    } else if (s.charAt(i) == c) {
                        dp[k][i][j] = dp[k][i][j - 1];
                    } else if (s.charAt(j) == c) {
                        dp[k][i][j] = dp[k][i + 1][j];
                    } else {
                        dp[k][i][j] = dp[k][i + 1][j - 1];
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 4; i++) {
            res = (res + dp[i][0][n - 1]) % MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        CountPalindromicSubsequences palindromicSubsequences = new CountPalindromicSubsequences();
        String s = "bccb";
        System.out.println(palindromicSubsequences.countPalindromicSubsequences(s));
    }
}
