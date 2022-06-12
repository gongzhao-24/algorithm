/**
* 描述：leetcode 128. 最长连续序列
* 创建日期：2022年06月10 14:14:
* @author gong zhao 
**/
package 动态规划;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 
 */

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 1;
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }
            int currentLen = 1;
            int currentNum = num;
            while (set.contains(currentNum + 1)) {
                currentNum++;
                currentLen++;
            }
            maxLen = Math.max(maxLen, currentLen);
        }
        return maxLen;
    }

    public int longestConsecutiveForDp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 1;
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }
            int currentLen = 1;
            int currentNum = num;
            while (set.contains(currentNum + 1)) {
                currentNum++;
                currentLen++;
            }
            maxLen = Math.max(maxLen, currentLen);
        }
        return maxLen;
    }
}
