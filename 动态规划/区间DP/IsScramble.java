/**
* 描述：
* 创建日期：2022年06月29 10:41:
* @author gong zhao 
**/
package 动态规划.区间DP;

public class IsScramble {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            // s1与s2一致
            return true;
        }
        if (!check(s1, s2)) {
            return false;
        }
        int len = s1.length();
        for (int i = 0; i < len - 1; i++) {
            // 划分为[0, i][i + 1, s1.length - 1]
            String a = s1.substring(0, i + 1);
            String b = s1.substring(i + 1);

            String c = s2.substring(0, i + 1);
            String d = s2.substring(i + 1);

            if (isScramble(a, c) && isScramble(b, d)) {
                return true;
            }

            String e = s2.substring(len - i - 1);
            String f = s2.substring(0, len - i - 1);
            if (isScramble(a, e) && isScramble(b, f)) {
                return true;
            }
        }
        return false;
    }

    public boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] s1Array = new int[26];
        int[] s2Array = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1Array[s1.charAt(i) - 'a']++;
            s2Array[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (s1Array[i] != s2Array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * dp的做法
     * 
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScrambleForDp(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        /**
         * dp[i][j][k] 表示 s1中i起始，与s2中j起始，长度为k的字符串是否为扰动字符串
         */
        // 处理长度为1的字符串，只要字符相同，那就是互为扰动字符串
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j][1] = true;
                }
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                for (int j = 0; j + len <= n; j++) {
                    if (dp[i][j][len]) {
                        continue;
                    }
                    // 这里判断一下是不是相等的，如果相等，直接赋值true就行了
                    String str1 = s1.substring(i, i + len);
                    String str2 = s2.substring(j, j + len);
                    if (str1.equals(str2)) {
                        dp[i][j][len] = true;
                    } else {
                        for (int k = 1; k < len; k++) {
                            if (dp[i][j][len]) {
                                continue;
                            }
                            boolean a = dp[i][j][k] && dp[i + k][j + k][len - k];
                            boolean b = dp[i][j + len - k][k] && dp[i + k][j][len - k];
                            dp[i][j][len] = a || b;
                        }
                    }

                }
            }
        }
        return dp[0][0][n];

    }
}
