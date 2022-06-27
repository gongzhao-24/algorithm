/**
* 描述：leetcode 533
* 创建日期：2022年06月27 19:32:
* @author gong zhao 
**/
package 动态规划.序列DP;

import java.util.Arrays;

public class FindLUSlength {
    public int findLUSlength(String[] strs) {
        // 先按长度从大到小排个序
        Arrays.sort(strs, (o1, o2) -> {
            return o2.length() - o1.length();
        });

        /**
         * 思路清理一下：
         * ①：现将字符串排序，从长到短进行排序，假如说，A字符串和B消掉了，
         * 那么现在
         */
        int len = strs.length;
        boolean[] disappear = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (disappear[i]) {
                // 这个字符串已经被前面的串消掉了，那么就可以直接跳过了。
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                boolean isSubSquence = checkSubQuence(strs[i], strs[j]);
                // 如果j是i的子串，那么首先肯定是先判断
                if (isSubSquence) {
                    if (strs[i].length() == strs[j].length()) {
                        // 如果两个字符串长度相等，
                        disappear[i] = true;
                        disappear[j] = true;
                    } else {
                        disappear[j] = true;
                    }
                }

            }
        }
        for (int i = 0; i < len; i++) {
            if (!disappear[i]) {
                return strs[i].length();
            }
        }
        return -1;
    }

    public boolean checkSubQuence(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2] == str2.length();
    }

    public static void main(String[] args) {
        FindLUSlength findLUSlength = new FindLUSlength();
        String[] strs = new String[] {
            "aabbcc", "aabbcc","b" };
        System.out.println(findLUSlength.findLUSlength(strs));
    }
}
