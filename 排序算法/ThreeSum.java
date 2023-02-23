/**
* 描述：lc 15
* 创建日期：2023年02月23 16:41:
* @author gong zhao 
**/
package 排序算法;

import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        // 第一步先排序
        quickSort(nums, 0, nums.length - 1);
        // 其次是三指针
        int left = 0;
        int right = nums.length - 1;
        // -4, -1, -1, 0, 1, 2
        while (left < right - 1) {
            // 如果
        }

        return null;
    }

    public void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = nums[lo];
        int start = lo;
        int end = hi;
        while (start < end) {
            while (nums[end] >= pivot && end > start) {
                end--;
            }
            nums[start] = nums[end];
            while (nums[start] <= pivot && start < end) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        quickSort(nums, lo, start - 1);
        quickSort(nums, start + 1, hi);
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
        threeSum.threeSum(nums);
    }
}
