/**
* 描述：leetcode 689. 三个无重叠子数组的最大和
* 创建日期：2022年06月09 20:50:
* @author gong zhao 
**/
package 二分搜索;

public class MaxSumOfThreeSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // 三个滑动窗口 【0， k- 1】[k, 2k - 1], [2k, 3k-1]
        int leftIndex = 0;
        int leftSum = 0;
        int midIndex = k;
        int midSum = 0;
        int rightIndex = 2 * k;
        int rightSum = 0;

        int maxLeftIndex = 0;
        int maxMidIndex = 0;
        int maxRightIndex = 0;

        int maxSum = Integer.MIN_VALUE;
        while (rightIndex < nums.length) {
            if (leftIndex < k) {
                leftSum += nums[leftIndex];
                rightSum += nums[rightIndex];
                midSum += nums[midIndex];
                if (leftSum + rightSum + midSum > maxSum) {
                    maxSum = leftSum + rightSum + midSum;
                    maxLeftIndex = leftIndex;
                    maxMidIndex = midIndex;
                    maxRightIndex = rightIndex;
                }
            } else {
                leftSum = leftSum + nums[leftIndex] - nums[leftIndex - k];
                midSum = midSum + nums[midIndex] - nums[midIndex - k];
                rightSum = rightSum + nums[rightIndex] - nums[rightIndex - k];
                if (leftSum + rightSum + midSum > maxSum) {
                    maxSum = leftSum + rightSum + midSum;
                    maxLeftIndex = leftIndex;
                    maxMidIndex = midIndex;
                    maxRightIndex = rightIndex;
                }
            }
        }
        return new int[]{maxLeftIndex, maxMidIndex, maxRightIndex};
    }
}
