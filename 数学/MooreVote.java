/**
* 描述：摩尔投票法
* 创建日期：2022年06月16 18:47:
* @author gong zhao 
**/
package 数学;

import java.util.ArrayList;
import java.util.List;

public class MooreVote {
    /**
     * 摩尔投票算法是基于这个事实：
     * 每次从序列里选择两个不相同的数字删除掉（或称为“抵消”），
     * 最后剩下一个数字或几个相同的数字，就是出现次数大于总数一半的那个
     * 
     * 
     * 
     */

    // leetcode 剑指 Offer 39. 数组中出现次数超过一半的数字
    // 简单的摩尔投票法。
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count > 0) {
                if (nums[i] == major) {
                    count++;
                } else {
                    count--;
                }
            } else {
                major = nums[i];
                count++;
            }
        }
        return major;
    }

    /**
     * 进阶摩尔投票法：
     * leetcode 229. 多数元素 II
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 1) {
            res.add(nums[0]);
            return res;
        }
        int firstNum = 0;
        int firstCount = 0;
        int secondNum = 0;
        int secondCount = 0;
        for (int num : nums) {
            if (firstCount > 0 && num == firstNum) {
                firstCount++;
            } else if (secondCount > 0 && num == secondNum) {
                secondCount++;
            } else if (firstCount == 0) {
                firstNum = num;
                firstCount++;
                // 如果都不等的话
            } else if (secondCount == 0) {
                secondNum = num;
                secondCount++;
            } else {
                firstCount--;
                secondCount--;
            }
        }

        firstCount = firstCount > 0 ? 0 : -1;
        secondCount = secondCount > 0 ? 0 : -1;

        for (int num : nums) {
            if (firstCount >= 0 && num == firstNum) {
                firstCount++;
            } else if (secondCount >= 0 && num == secondNum) {
                secondCount++;
            }
        }
        if (firstCount > 0 && firstCount * 3 > nums.length) {
            res.add(firstNum);
        }
        if (secondCount > 0 && secondCount * 3 > nums.length) {
            res.add(secondNum);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 4, 2, 1, 1 };
        System.out.println(majorityElement2(nums));
    }
}
