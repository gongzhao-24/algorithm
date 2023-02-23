/**
* 描述：快速排序
* 创建日期：2023年02月22 11:04:
* @author gong zhao 
**/
package 排序算法;

public class QuickSort {
    /**
     * 快速排序的基本思想是，通过一次排序，将数据分为两部分，
     * 假如有一个基准数据pivot，那么通过一次排序，比pivot小放在它前面，比pivot大的放到它后面。
     * 然后递归上述过程。
     * 快速排序的时间复杂度
     */

    /**
     * 
     * 3 7 1 8 2 4 0 5
     * 0 7 1 8 2 4 3 5
     * 0 3 1 8 2 4 7 5
     * 
     * @param nums
     * @param lo
     * @param hi
     */

    public static void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = nums[lo];
        int start = lo;
        int end = hi;
        while (start < end) {
            // 先从后往前
            while (nums[end] >= pivot && end > start) {
                end--;
            }
            nums[start] = nums[end];
            // 现在end 里面的值是 pivot
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
        int[] nums = new int[] { 8 };
        quickSort(nums, 0, nums.length - 1);
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println(nums);
    }
}
