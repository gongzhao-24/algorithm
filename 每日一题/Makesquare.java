/**
* 描述：
* 创建日期：2022年06月01 00:12:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Makesquare {
    Integer[] sticks;

    // 回溯算法。想想怎么减枝
    public boolean makesquare(int[] matchsticks) {
        sticks = new Integer[matchsticks.length];
        for (int i = 0; i < matchsticks.length; i++) {
            sticks[i] = matchsticks[i];
        }
        Arrays.sort(sticks, (o1, o2) -> (o2 - o1));
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : matchsticks) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            ans += num;
        }
        if (ans % 4 != 0) {
            return false;
        }
        int len = ans / 4;

        // 一个正方形有四条边，火柴必然会出现在某一条边里面
        int[] edges = new int[4];

        // 有点类似于背包问题了。
        return backtrace(0, edges, len, matchsticks.length);

    }

    // list 表示结果集合 ，set表示已经选择的index，
    public boolean backtrace(int index, int[] edgs, int len, int size) {
        //
        if (index == size) {
            /*
             * for (int edgLen : edgs) {
             * if (edgLen != target) {
             * return false;
             * }
             * }
             */
            return true;
        } else {
            for (int i = 0; i < 4; i++) {
                // 选择将index 这根火柴放置在 正方形的某条边里面
                if (edgs[i] + sticks[index] > len) {
                    continue;
                }
                edgs[i] += sticks[index];
                boolean res = backtrace(index + 1, edgs, len, size);
                if (res) {
                    return res;
                }
                edgs[i] -= sticks[index];
            }
            return false;
        }

    }

    //动态规划的解法，需要仔细研究一下
    public boolean makesquare1(int[] matchsticks) {
        int totalLen = Arrays.stream(matchsticks).sum();
        if (totalLen % 4 != 0) {
            return false;
        }
        int len = totalLen / 4, n = matchsticks.length;
        //dp[s] 表示正方形未放满的边的当前长度
        //用状态 ss 记录哪些火柴已经被放入（ss 的第 kk 位为 11 表示第 kk 根火柴已经被放入）
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int s = 1; s < (1 << n); s++) {
            for (int k = 0; k < n; k++) {
                if ((s & (1 << k)) == 0) {
                    continue;
                }
                int s1 = s & ~(1 << k);
                if (dp[s1] >= 0 && dp[s1] + matchsticks[k] <= len) {
                    dp[s] = (dp[s1] + matchsticks[k]) % len;
                    break;
                }
            }
        }
        return dp[(1 << n) - 1] == 0;
    }

    public static void main(String[] args) {
        Makesquare makesquare = new Makesquare();
        int[] matchsticks = new int[] { 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3 };
        boolean res = makesquare.makesquare(matchsticks);
        System.out.println();
    }
}
