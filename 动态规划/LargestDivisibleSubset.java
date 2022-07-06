/**
* 描述：leetcode 368
* 创建日期：2022年07月06 19:51:
* @author gong zhao 
**/
package 动态规划;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxVal = nums[0];
        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLen) {
                maxVal = nums[i];
                maxLen = dp[i];
            }
        }
        List<Integer> res = new ArrayList<>();
        if (maxLen == 1) {
            res.add(maxVal);
            return res;
        }
        for (int i = len - 1; i >= 0; i--) {
            if (dp[i] == maxLen && maxVal % nums[i] == 0) {
                res.add(0, nums[i]);
                maxLen--;
                maxVal = nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 4, 8 };
        LargestDivisibleSubset largestDivisibleSubset = new LargestDivisibleSubset();
        List<Integer> res = largestDivisibleSubset.largestDivisibleSubset(nums);
        System.out.println();
    }
}
