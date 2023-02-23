/**
* 描述：基数排序
* 创建日期：2023年02月23 15:19:
* @author gong zhao 
**/
package 排序算法;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 基数排序，基本原理就是从数据的每一位开始排序
 */
public class RadixSort {
    /**
     * ①：首先找出来数组中最大数是多少
     * ②：按位进行统计，统计每一位中，0-9的数字个数有多少
     * ③：从后往前，对每一位分别是什么数字的数进行排序
     */
    public static void radixSort(int[] nums) {
        // 数组中最大值
        int maxValue = Arrays.stream(nums).max().getAsInt();
        int exp = 1;
        // 位数组，放置数组中某个位中某个数个数有多少
        int[] temp = new int[nums.length];
        while (maxValue >= exp) {
            int[] cnt = new int[10];
            for (int i = 0; i < nums.length; i++) {
                int digital = (nums[i] / exp) % 10;
                cnt[digital]++;
            }
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = nums.length - 1; i >= 0; i--) {
                int digital = (nums[i] / exp) % 10;
                int index = --cnt[digital];
                temp[index] = nums[i];
            }
            exp *= 10;
            System.arraycopy(temp, 0, nums, 0, nums.length);

        }

    }

    public static void main(String[] args) {
        int[] nums = new int[] { 12, 01, 1, 32, 0, 100, 23, 24, 9, 52 };
        radixSort(nums);
        System.out.println();
    }
}
