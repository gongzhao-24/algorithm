/**
* 描述：
* 创建日期：2022年06月25 16:32:
* @author gong zhao 
**/
package 动态规划.区间DP;

public class LongestPalindrome1 {
    public static String longestPalindrome1(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            if (i + 1 < len && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }
        // 长度2
        int maxLen = 1;
        int l = 0;
        int r = 0;
        for (int k = 2; k <= len; k++) {
            for (int i = 0; i + k - 1 < len; i++) {
                int left = i;
                int right = i + k - 1;
                if (dp[left][right]) {
                    if (k > maxLen) {
                        maxLen = k;
                        l = left;
                        r = right;
                    }
                } else {
                    if (s.charAt(left) == s.charAt(right)) {
                        dp[left][right] = dp[left + 1][right - 1];
                        if (dp[left][right] && k > maxLen) {
                            maxLen = k;
                            l = left;
                            r = right;
                        }
                    }
                }
            }
        }
        return s.substring(l, r + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome1("cbbd"));
    }

}
