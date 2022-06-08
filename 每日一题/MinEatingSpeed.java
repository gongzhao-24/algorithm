/**
* 描述：leetcode 875
* 创建日期：2022年06月07 17:06:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;

public class MinEatingSpeed {
    // 有点像贪心
    public int minEatingSpeed(int[] piles, int h) {
        // 如果 piles.length == h，那么选取piles 中的最大值就行了
        // 假如说，h bi piles 大1 plies:10,h:12
        // 可以求的，至少有多少的是需要一次性做完的，把这个求出来，然后剩下的除以一半有没有比这个小，如果没有，则取那个最大值
        int len = piles.length;
        Arrays.sort(piles);
        if (len == h) {
            return piles[len - 1];
        }
        // 二分
        int lo = piles[h - len - 1];
        int hi = piles[len - 1];
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (cal(piles, mid) <= h) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;

    }

    int cal(int[] piles, int v) {
        int sum = 0;
        for (int pile : piles) {
            sum += (pile + v - 1) / v;
        }
        return sum;
    }

    public static void main(String[] args) {
        MinEatingSpeed minEatingSpeed = new MinEatingSpeed();
        int[] piles = new int[]{3,6,7,11};
        System.out.println(minEatingSpeed.minEatingSpeed(piles, 8));
    }
}
