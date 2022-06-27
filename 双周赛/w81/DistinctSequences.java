/**
* 描述：
* 创建日期：2022年06月25 23:23:
* @author gong zhao 
**/
package 双周赛.w81;

public class DistinctSequences {
    int mod = (int) (1e9 + 7);

    public int distinctSequences(int n) {
        int[][][] dp = new int[n + 1][6][6];
        // i j k，表示 第i次掷，且倒数第二次为j，最后一次为k的次数有多少。
        if (n == 1) {
            // 因为选一次的话，有六种可能性
            return 6;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i != j && gcd(i + 1, j + 1) == 1) {
                    dp[2][i][j] = 1;
                }
            }
        }

        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (j == k || gcd(j + 1, k + 1) != 1) {
                        continue;
                    }
                    for (int m = 0; m < 6; m++) {
                        // m表示倒数第三个数
                        if (m != j && m != k && gcd(m + 1, j + 1) == 1) {
                            dp[i][j][k] = (dp[i][j][k] + dp[i - 1][m][j]) % mod;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                res = (res + dp[n][i][j]) % mod;
            }
        }
        return res;
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        return a % b == 0 ? b : gcd(b, a % b);
    }
}
