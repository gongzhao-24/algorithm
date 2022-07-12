/**
* 描述：
* 创建日期：2022年07月09 23:37:
* @author gong zhao 
**/
package 双周赛.w82;

import java.util.Arrays;

/**
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度为 n 。
 * 
 * 数组 nums1 和 nums2 的 差值平方和 定义为所有满足 0 <= i < n 的 (nums1[i] - nums2[i])2 之和。
 * 
 * 同时给你两个正整数 k1 和 k2 。你可以将 nums1 中的任意元素 +1
 * 或者 -1 至多 k1 次。类似的，你可以将 nums2 中的任意元素 +1 或者 -1 至多 k2 次。
 * 
 * 请你返回修改数组 nums1 至多 k1 次且修改数组 nums2 至多 k2 次后的最小 差值平方和 。
 * 
 * 注意：你可以将数组中的元素变成 负 整数。
 * 
 */

public class MinSumSquareDiff {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int len = nums1.length;
        Integer[] a = new Integer[len + 1];
        int k = k1 + k2;
        long sum = 0L;
        long ans = 0L;
        for (int i = 0; i < len; i++) {
            a[i] = Math.abs(nums1[i] - nums2[i]);
            sum += a[i];
            ans += ((long) a[i] * (long) a[i]);
        }
        a[len] = 0;
        if (sum <= k) {
            return 0;
        }
        Arrays.sort(a, (o1, o2) -> {
            return o2 - o1;
        });

        for (int i = 1; i <= len; i++) {
            int count = i;
            // 把前面所有的数据下降到我当前的数据需要的值
            long cost = (long) (a[i - 1] - a[i]) * (long) count;
            ans -= ((long) a[i - 1] * (long) a[i - 1]);
            if (cost < k) {
                k -= cost;
                continue;
            }
            // 到这一步了，看前面有多少的可以减为a[i] 有多少的需要保留为a[i-1];
            // 因为 a[i - 1] 到 a[i] 之间的间隔可能比较大，现在先缩小，看能不能找到一个范围
            int current = a[i - 1];
            while (count < k) {
                current--;
                k -= count;
            }
            // 这里算出来现在一部分是 current，还有一部分是 可以是current - 1

            ans += ((long) k * (long) (current - 1) * (long) (current - 1)
                    + (long) (count - k) * (long) current * (long) current);
            return ans;
        }
        return ans;

    }

    public static void main(String[] args) {
        /**
         * [19,18,19,18,18,19,19]
         * [1,0,1,0,0,1,1]
         * 10
         * 33
         */

        int[] nums1 = new int[] { 19, 18, 19, 18, 18, 19, 19 };
        int[] nums2 = new int[] { 1, 0, 1, 0, 0, 1, 1 };
        int k1 = 10;
        int k2 = 33;
        MinSumSquareDiff minSumSquareDiff = new MinSumSquareDiff();
        System.out.println(minSumSquareDiff.minSumSquareDiff(nums1, nums2, k1, k2));
    }
}
