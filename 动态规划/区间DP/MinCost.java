/**
* 描述：
* 创建日期：2022年06月28 10:37:
* @author gong zhao 
**/
package 动态规划.区间DP;

import java.util.Arrays;

public class MinCost {
    public static int minCost(int n, int[] cuts) {
        /**
         * ①：一个棍子的长度为n，切点的位置为 cuts[],dp[i][j] 表示从 cuts[i]到cuts[j] 最小的切割成本
         * ②：定义好了之后就可以枚举中间的每一个切点，看成本最小的方式是哪种
         */

        int[] array = new int[cuts.length + 2];
        for (int i = 1; i <= cuts.length; i++) {
            array[i] = cuts[i - 1];
        }
        array[0] = 0;
        array[array.length - 1] = n;
        Arrays.sort(array);
        int[][] dp = new int[cuts.length + 2][cuts.length + 2];
        // i表示两切割点之间的距离
        for (int i = 1; i <= cuts.length+1; i++) {
            for (int j = 0; i + j < array.length; j++) {
                int left = j;
                int right = j + i;
                if(right - left <= 1){
                    dp[left][right] = 0;
                    continue;
                }


                dp[left][right] = Integer.MAX_VALUE;
                // 枚举中间的那一刀，然后再分左右
                for (int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.min(dp[left][right],
                            array[right] - array[left] + dp[left][k] + dp[k][right]);
                }

            }
        }
        return dp[0][cuts.length + 1];
    }

    public static void main(String[] args) {
        int[] cuts = new int[] {5,6,1,4,2};
        int n = 9;
        System.out.println(minCost(n, cuts));
    }
}
