/**
* 描述：归并排序
* 创建日期：2023年02月22 16:22:
* @author gong zhao 
**/
package 排序算法;

import java.util.Arrays;

public class MergeSort {
    /**
     * 归并排序思路：
     * 先将一个数组拆为两部分，然后先将两部分中的顺序排好了之后，再将两个已经排好序的数组归并排序到一个数组中
     * 
     * @param nums
     * @param lo
     * @param hi
     */
    public static void mergeSort(int[] nums, int lo, int hi, int[] temp) {
        if (lo == hi) {
            return;
        }
        int mid = lo + ((hi - lo) >> 1);
        mergeSort(nums, lo, mid, temp);
        mergeSort(nums, mid + 1, hi, temp);
        // 现在左右两边都排好了
        int left = lo;
        int right = mid + 1;
        int index = lo;

        while (left <= mid && right <= hi) {
            temp[index++] = (nums[left] <= nums[right]) ? nums[left++] : nums[right++];
        }
        while (left <= mid) {
            temp[index++] = nums[left++];
        }
        while (right <= hi) {
            temp[index++] = nums[right++];
        }

        for (int i = lo; i <= hi; i++) {
            nums[i] = temp[i];
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[] { 8, 7, 1, 8, 2, 4, 0, 5 };
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
        System.out.println();
    }
}
