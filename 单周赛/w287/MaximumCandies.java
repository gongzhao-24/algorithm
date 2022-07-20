/**
* 描述：
* 创建日期：2022年07月16 15:56:
* @author gong zhao 
**/
package 单周赛.w287;

import java.util.Arrays;

public class MaximumCandies {
    int[] candies;
    long k;

    public int maximumCandies(int[] candies, long k) {
        Arrays.sort(candies);
        this.candies = candies;
        this.k = k;
        int len = candies.length;
        int maxCandie = candies[len - 1];
        return binarySearch(1, maxCandie);
        // 有两种可能性，如果需要最小的，
        // 二分，然后判断分这个么多糖果是否合理
    }

    public int binarySearch(int left, int right) {
        int lo = left;
        int hi = right;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (check(mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo - 1;
    }

    public boolean check(int candie) {
        long count = k;
        int index = binarySearchLeft(candie);
        for (int i = index; i < candies.length; i++) {
            count -= candies[i] / candie;
            if (count <= 0) {
                return true;
            }
        }
        return false;
    }

    // 找到第一个大于等于target的数的坐标
    public int binarySearchLeft(int target) {
        int lo = 0;
        int hi = candies.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (candies[mid] == target) {
                hi = mid - 1;
            } else if (candies[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        /**
         * 如果没有数比target小 lo = 0；
         * 如果没有数比target 大 lo = len
         * 3 5 6 4
         */
        if (lo == candies.length) {
            return -1;
        }
        return lo;
    }
}
