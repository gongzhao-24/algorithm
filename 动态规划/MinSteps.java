/**
* 描述：leetcode 650. 只有两个键的键盘
* 创建日期：2022年07月20 17:29:
* @author gong zhao 
**/
package 动态规划;

public class MinSteps {
    public static int minSteps(int n) {
        // 最后得到了n，一定是前面某一次或者多次通过拷贝得来的
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }

        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(minSteps(18));
    }
}
