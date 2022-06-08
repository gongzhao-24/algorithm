/**
* 描述：
* 创建日期：2022年06月07 21:08:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BestSeqAtIndex {
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int[][] players = new int[height.length][2];
        for (int i = 0; i < height.length; i++) {
            players[i] = new int[] { height[i], weight[i] };
        }
        int[] dp = new int[height.length];
        Arrays.fill(dp, 1);
        Arrays.sort(players, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        for (int i = 1; i < height.length; i++) {
            if(players[i][0] > players[i])
        }

    }
}
