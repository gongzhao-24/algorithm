/**
* 描述：leetcode 474
* 创建日期：2022年07月20 16:57:
* @author gong zhao 
**/
package 动态规划.背包问题;

//0-1背包问题
public class FindMaxForm {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 如果不满足的话，就只能继承前面的
                    int[] counts = count(strs[i - 1]);
                    int countZero = counts[0];
                    int countOne = counts[1];
                    if (j < countZero || k < countOne) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - countZero][k-countOne] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    public int[] count(String str) {
        int countOne = 0;
        int countZero = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                countZero++;
            } else {
                countOne++;
            }
        }
        return new int[] { countZero, countOne };
    }

    public static void main(String[] args) {
        System.out.println((0.007)*100);
        System.out.println((float)0.007*((float)100));
    }
}
