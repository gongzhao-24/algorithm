/**
* 描述：leetcode 167. 两数之和 II - 输入有序数组
* 创建日期：2022年06月13 15:57:
* @author gong zhao 
**/
package 双指针;

import java.util.ArrayList;
import java.util.List;

public class TwoSum {
    /**
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，
     * 请你从数组中找出满足相加之和等于目标数 target 的两个数。
     * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <=
     * numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     */

    public int[] twoSum(int[] numbers, int target) {
        List<int[]> list = new ArrayList();
        // 可以认为数据是单调增的,因此可以将双指针分别放置在首尾，然后逐渐收缩
        int lo = 0;
        int hi = numbers.length - 1;
        while (lo < hi) {
            if (numbers[lo] + numbers[hi] == target) {
                break;
            } else if (numbers[lo] + numbers[hi] < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return new int[] { lo + 1, hi + 1 };
    }
}
