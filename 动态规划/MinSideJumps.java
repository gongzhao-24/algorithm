/**
* 描述：
* 创建日期：2022年06月01 16:28:
* @author gong zhao 
**/
package 动态规划;

import java.util.Arrays;

public class MinSideJumps {
    public int minSideJumps(int[] obstacles) {
        int len = obstacles.length;
        int[][] dp = new int[len][3];
        dp[0][1] = 0;
        dp[0][0] = 1;
        dp[0][2] = 1;
        for (int i = 1; i < len; i++) {
            int obstacleIndex = obstacles[i] - 1;
            if (obstacleIndex == -1) {
                // 说明这一列没有障碍
                // 只能同一列之间跳跃，
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1];
                dp[i][2] = dp[i - 1][2];

                dp[i][0] = Math.min(dp[i][0], Math.min(dp[i][1], dp[i][2]) + 1);
                dp[i][1] = Math.min(dp[i][1], Math.min(dp[i][0], dp[i][2]) + 1);
                dp[i][2] = Math.min(dp[i][2], Math.min(dp[i][0], dp[i][1]) + 1);
            } else {
                dp[i][obstacleIndex] = Integer.MAX_VALUE/2;
                if (obstacleIndex == 0) {
                    dp[i][1] = dp[i - 1][1];
                    dp[i][2] = dp[i - 1][2];
                    dp[i][1] = Math.min(dp[i][1], Math.min(dp[i][0], dp[i][2]) + 1);
                    dp[i][2] = Math.min(dp[i][2], Math.min(dp[i][0], dp[i][1]) + 1);
                } else if (obstacleIndex == 1) {
                    dp[i][0] = dp[i - 1][0];
                    dp[i][2] = dp[i - 1][2];
    
                    dp[i][0] = Math.min(dp[i][0], Math.min(dp[i][1], dp[i][2]) + 1);
                    dp[i][2] = Math.min(dp[i][2], Math.min(dp[i][0], dp[i][1]) + 1);
                } else {
                    dp[i][0] = dp[i - 1][0];
                    dp[i][1] = dp[i - 1][1];
    
                    dp[i][0] = Math.min(dp[i][0], Math.min(dp[i][1], dp[i][2]) + 1);
                    dp[i][1] = Math.min(dp[i][1], Math.min(dp[i][0], dp[i][2]) + 1);
                }
            }
        }
        return Arrays.stream(dp[len - 1]).min().getAsInt();
    }

    public static void main(String[] args) {
        int[] obstacles = new int[] { 0, 1, 2, 3, 0 };
        MinSideJumps minSideJumps = new MinSideJumps();
        int res = minSideJumps.minSideJumps(obstacles);
        System.out.println(res);
    }
}
