/**
* 描述：
* 创建日期：2022年10月26 20:31:
* @author gong zhao 
**/
package 单周赛.w305;

import java.util.Arrays;

/**
 * 给你一个由小写字母组成的字符串 s ，和一个整数 k 。如果满足下述条件，则可以将字符串 t 视作是 理想字符串 ：
 * 
 * t 是字符串 s 的一个子序列。
 * t 中每两个 相邻 字母在字母表中位次的绝对差值小于或等于 k 。
 * 返回 最长 理想字符串的长度。
 * 
 * 字符串的子序列同样是一个字符串，并且子序列还满足：可以经由其他字符串删除某些字符（也可以不删除）但不改变剩余字符的顺序得到。
 * 
 * 注意：字母表顺序不会循环。例如，'a' 和 'z' 在字母表中位次的绝对差值是 25 ，而不是 1 。
 * 
 * 示例 1：
 * 
 * 输入：s = "acfgbd", k = 2
 * 输出：4
 * 解释：最长理想字符串是 "acbd" 。该字符串长度为 4 ，所以返回 4 。
 * 注意 "acfgbd" 不是理想字符串，因为 'c' 和 'f' 的字母表位次差值为 3 。
 * 示例 2：
 * 
 * 输入：s = "abcd", k = 3
 * 输出：4
 * 解释：最长理想字符串是 "abcd" ，该字符串长度为 4 ，所以返回 4 。
 */

/**
 * 看到子序列和相邻就可以往 DP 上想（回顾一下经典题 300. 最长递增子序列，它也是子序列和相邻）。
 * 
 * 字符串题目套路：枚举字符。定义 f[i][c] 表示 s 的前 i 个字母中的以 c 结尾的理想字符串的最长长度。
 * 
 * 根据题意：
 * 
 * 选 s[i] 作为理想字符串中的字符，需要从 f[i−1] 中的 [s[i]−k,s[i]+k]
 * 范围内的字符转移过来，即
 * 
 * f[i][s[i]] = 1 + \max_{c=\max(s[i]-k,0)}^{\min(s[i]+k,25)} f[i-1][c]
 * f[i][s[i]]=1+
 * c=max(s[i]−k,0)
 * max
 * min(s[i]+k,25)
 * ​
 * f[i−1][c]
 * 
 * 其余情况，f[i][c] = f[i-1][c]f[i][c]=f[i−1][c]。
 * 
 * 答案为 \max(f[n-1])max(f[n−1])。
 * 
 * 代码实现时第一维可以压缩掉。
 * 
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

public class LongestIdealString {
    /*
     * public int longestIdealString(String s, int k) {
     * int[] f = new int[26];
     * for (int i = 0; i < s.length(); i++) {
     * // c 表示 s字符串中第i个字符
     * int c = s.charAt(i) - 'a';
     * for (int j = Math.max(c - k, 0); j <= Math.min(c + k, 25); j++)
     * f[c] = Math.max(f[c], f[j]);
     * f[c]++;
     * }
     * return Arrays.stream(f).max().getAsInt();
     * }
     */
    public int longestIdealString(String s, int k) {
        int len = s.length();
        int[][] dp = new int[len + 1][26];
        for (int i = 1; i <= len; i++) {
            int c = s.charAt(i - 1) - 'a';
            // 当c确定之后，c能连接上的字符的范围也已经确定了【c-k,c+k】
            for (int j = 0; j < 26; j++) {
                if (j < Math.max(0, c - k) || j > Math.min(25, c + k)) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][c] = Math.max(dp[i][c], dp[i - 1][j]);
                    if (j != c) {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            dp[i][c]++;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            res = Math.max(res, dp[len][i]);
        }
        return res;
    }

}
