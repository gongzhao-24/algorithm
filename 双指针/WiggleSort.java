/**
* 描述：
* 创建日期：2022年06月28 09:56:
* @author gong zhao 
**/
package 双指针;

import java.util.Arrays;

public class WiggleSort {
    public static void wiggleSort(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        // 1 2 3 4
        // 1 2 3 4 5 6
        int right = len - 1;
        int left = right / 2;
        while (left >= 0) {
            if (left % 2 == 1) {
                // 奇数，需要换
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
            left--;
            right--;
        }
        return;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 3, 2, 2, 3, 1 };
        wiggleSort(nums);
    }
}
