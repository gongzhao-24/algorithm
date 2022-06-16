/**
* 描述：leetcode 719
* 创建日期：2022年06月15 11:06:
* @author gong zhao 
**/
package 二分搜索;

import java.util.Arrays;

public class SmallestDistancePair {
    // 不是超时，而是内存溢出了，怎么二分呢
    /**
     * 先将数组 nums 从小到大进行排序。因为第 k 小的数对距离必然在区间 [0,max(nums)−min(nums)] 内，
     * 令 left=0，right=max(nums)−min(nums)，我们在区间 [left,right] 上进行二分。
     * 对于当前搜索的距离 mid，计算所有距离小于等于 mid 的数对数目 cnt，
     * 如果 cnt≥k，那么 right=mid−1，否则 left=mid+1。
     * 当 left>right 时，终止搜索，那么第 k 小的数对距离为 left。
     * 给定距离 mid，计算所有距离小于等于 mid 的数对数目 cnt 可以使用二分查找：
     * 枚举所有数对的右端点 j，二分查找大于等于 nums[j]−mid 的最小值的下标 i，
     * 那么右端点为 j 且距离小于等于 mid 的数对数目为 j−i，计算这些数目之和。
     */

    int[] nums;

    public int smallestDistancePair(int[] nums, int k) {
        // 首先将数组从小到大排序
        Arrays.sort(nums);
        this.nums = nums;
        // 最小的距离是0，最大的距离是数组末尾 减去 数组 起始
        // 这这个区间中，找到一个最大的数字，使得其对应的cnt >= k.
        int loMid = 0;
        int hiMid = nums[nums.length - 1] - nums[0];
        while (loMid <= hiMid) {
            int mid = loMid + ((hiMid - loMid) >> 1);
            int cnt = 0;
            for (int j = 1; j < nums.length; j++) {
                /**
                 * 现在的最大距离是 mid，而当前的数据是 nums[j]，说明允许
                 */
                int i = binarySearch(j, nums[j] - mid > 0 ? nums[j] - mid : 0);
                cnt += (j - i);
            }
            if (cnt == k) {
                hiMid = mid - 1;
            } else if (cnt < k) {
                loMid = mid + 1;
            } else {
                hiMid = mid - 1;
            }
        }
        return loMid;
    }

    public int binarySearch(int j, int target) {
        // 找到最小的 >= target 的数,
        int start = 0;
        int end = j;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        // start 的范围是，【-1 ， 】
        return start;
    }

    public static void main(String[] args) {
        SmallestDistancePair smallestDistancePair = new SmallestDistancePair();
        int[] nums = new int[] {62,100,4};
        int k = 2;
        System.out.println(smallestDistancePair.smallestDistancePair(nums, k));
    }
}