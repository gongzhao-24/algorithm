/**
* 描述：数位dp
* 创建日期：2022年06月29 17:23:
* @author gong zhao 
**/
package 动态规划.数位统计DP;

import javax.sound.sampled.SourceDataLine;

public class DigitalDP {
    /**
     * todo ： 下面的解法还有问题，有时间来研究
     */

    // 返回从[1, nums] 之间的不降数，不降数是指从高位到低位，数字不下降
    public static int notDownNumber(int num) {
        if (num == 0) {
            return 1;
        }

        String str = String.valueOf(num);
        int len = str.length();
        /**
         * dp[i][j] 表示一共有i位，且最高位数字为j的不降数的个数
         * 因为最高位已经固定为j，所以假设第i-1位是k，根据不降数定义k >= j，所以
         * dp[i][j] = dp[i-1][j] + dp[i-1][j + 1] + dp[i-1][j + 2] + .... + dp[i-1][9];
         */
        int[][] dp = new int[len + 1][10];

        // 预处理
        for (int i = 0; i <= 9; i++) {
            // 如果只有一位数，那么不降数显然是1
            dp[1][i] = 1;
        }
        for (int i = 2; i <= len; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    // 状态转移方程,
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        // 将num拆出来一个个字符，然后存到数组中
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = str.charAt(i) - '0';
        }
        int res = 0;
        // 表示上一位的数字
        int last = 0;
        // 从高位到低位枚举
        for (int i = len - 1; i >= 1; i--) {
            // 当前位数
            int cur = array[i];
            for (int j = last; j < cur; j++) {
                res += dp[i][j];
            }
            if (cur < last) {
                break;
            }
            last = cur;
            if (i == 1) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(notDownNumber(19));
    }
}
