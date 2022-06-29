/**
* 描述：leetcode 1000
* 创建日期：2022年06月28 15:44:
* @author gong zhao 
**/
package 动态规划.区间DP;

/**
 * 今天晚上就好好研究这一题，区间DP经典题
 */

public class MergeStones {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            /**
             * 由于每一堆石子都可以有k堆石子合并而来，因此有
             * k + a*(k - 1) = n;
             * 
             * 如果a = 0，那自然是成立的，如果a ！= 0，怎么理解呢
             * 可以想象，将左边的k合并成一堆石子，然后从右边的 a*(k - 1) 堆石子中
             * 抽出来一个 k - 1，那么就变成了 k + （a-1）*（k-1） = n,由于 a 可以为任意数
             * 因此上述等式就是满足的。
             * 
             */

            return -1;
        }
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }
        // dp[i][j][k] 表示从i到j堆石头，合成k堆的成本是多少
        int[][][] dp = new int[n + 1][n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (i == j) {
                    // 同一堆石头，本来就是一堆，因此成本为0
                    dp[i][j][1] = 0;
                } else {
                    for (int m = 2; m <= k; m++) {
                        dp[i][j][m] = 100000;
                    }
                }
            }
        }
        // 第一维度枚举区间长度
        for (int len = 2; len <= n; len++) {
            // 第二维度枚举左端点
            for (int i = 1; i + len - 1 <= n; i++) {
                int left = i;
                int right = i + len - 1;
                // 第三维度枚举堆数
                for (int m = 2; m <= k; m++) {
                    // 第思维度枚举中间端点
                    for (int p = left; p < right && (right - p) >= m - 1; p++) {
                        int cost = dp[left][p][1] + dp[p + 1][right][m - 1];
                        dp[left][right][m] = Math.min(dp[left][right][m], cost);
                    }
                }
                dp[left][right][1] = dp[left][right][k] + sum[right] - sum[left - 1];

            }
        }
        return dp[1][n][1];

    }

}
