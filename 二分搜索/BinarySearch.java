/**
* 描述：
* 创建日期：2022年06月09 19:40:
* @author gong zhao 
**/
package 二分搜索;

public class BinarySearch {
    /**
     * 二分搜索主要分为三种
     * 1、普通的二分搜索，即单调队列中没有相同元素
     * 2、左边界二分搜索,即单调队列中有重复元素，找到最左边的那个，如果没有，返回-1
     * 3、右边界二分搜索，即单调队列中有重复的元素，找到最右边的那个，如果没有，返回-1
     */

    // 普通的二分搜索，即单调队列中没有相同元素,找确定值
    public static int normalBinarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    // 左边界二分搜索,即单调队列中有重复元素，找到最左边的那个，如果没有，返回-1
    public static int leftBoundaryBinarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] == target) {
                hi = mid - 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
         // 如果队列中的所有数都比target 大，那么lo = 0，hi = -1；
        // 如果队列中的所有数都比target 小，那么 lo = nums.length hi = nums.length -1;
        // 如果队列中有数跟targetn一样大
        if (lo != nums.length && nums[lo] == target) {
            return lo;
        }
        return -1;
    }

    // 右边界二分搜索，即单调队列中有重复的元素，找到最右边的那个，如果没有，返回-1
    public static int rightBoundaryBinarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] == target) {
                lo = mid + 1;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        // 如果队列中的所有数都比target 大，那么lo = 0，hi = -1；
        // 如果队列中的所有数都比target 小，那么 lo = nums.length hi = nums.length -1;
        // 如果队列中有数跟targetn一样大
        if (hi != -1 && nums[hi] == target) {
            return hi;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] normalNums = new int[]{1, 2, 3, 4, 5};
        int[] nums = new int[]{1, 2, 3, 3, 3, 4, 5};
        System.out.println(normalBinarySearch(normalNums, 3));
        System.out.println(leftBoundaryBinarySearch(nums, 3));
        System.out.println(leftBoundaryBinarySearch(nums, 0));
        System.out.println(leftBoundaryBinarySearch(nums, 6));
        System.out.println(rightBoundaryBinarySearch(nums, 3));
        System.out.println(rightBoundaryBinarySearch(nums, 0));
        System.out.println(rightBoundaryBinarySearch(nums, 6));
    }
}
