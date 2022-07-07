/**
* 描述：leetcode 403
* 创建日期：2022年07月07 10:11:
* @author gong zhao 
**/
package 动态规划;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CanCross {
    public boolean canCross(int[] stones) {
        if (stones[1] > 1) {
            return false;
        }
        int len = stones.length;
        Set<Integer>[] sets = new HashSet[len];
        for (int i = 0; i < len; i++) {
            sets[i] = new HashSet<>();
        }
        boolean[] dp = new boolean[len];
        dp[0] = true;
        dp[1] = true;
        sets[1].add(1);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(stones[i], i);
        }
        for (int i = 1; i < len; i++) {
            int current = stones[i];
            if (dp[i]) {
                Set<Integer> set = sets[i];
                for (int preJump : set) {
                    for (int jump = preJump - 1; jump <= preJump + 1; jump++) {
                        if (jump > 0) {
                            int next = current + jump;
                            if (map.containsKey(next)) {
                                // 如果跳下一步的石子存在
                                int nextIndex = map.get(next);
                                dp[nextIndex] = true;
                                sets[nextIndex].add(jump);
                            }
                        }
                    }
                }
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        CanCross canCross = new CanCross();
        int[] stones = new int[] { 0, 1, 2, 3, 4, 8, 9, 11 };
        System.out.println(canCross.canCross(stones));
    }
}
