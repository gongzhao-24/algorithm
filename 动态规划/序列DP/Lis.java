/**
* 描述：最长上升子序列 leetcode 300
* 创建日期：2022年06月27 16:51:
* @author gong zhao 
**/
package 动态规划.序列DP;

public class Lis {
    public int lengthOfLIS(int[] nums) {
        /**
         * 贪心：
         */
        int len = nums.length;
        int[] d = new int[len + 1];
        int maxLen = 1;
        d[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > d[maxLen]) {
                maxLen++;
                d[maxLen] = num;
            } else {
                int index = find(1, maxLen, d, num);
                if (index == 0) {
                    d[1] = num;
                } else {
                    d[index + 1] = num;
                }
            }
        }
        return maxLen;
    }

    public int find(int left, int right, int[] d, int target) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (d[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Lis lis = new Lis();
        int[] nums = new int[] { 4, 10, 4, 3, 8, 9 };
        System.out.println(lis.lengthOfLIS(nums));

    }
}
