/**
* 描述：
* 创建日期：2022年07月23 22:42:
* @author gong zhao 
**/
package 双周赛.w83;

public class ZeroFilledSubarray {
    public static long zeroFilledSubarray(int[] nums) {
        int len = nums.length;
        long[] dp = new long[len + 1];
        for (int i = 1; i <= len; i++) {
            if (nums[i - 1] == 0) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        long ans = 0;
        for (long num : dp) {
            ans += num;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,10,2019};
        System.out.println(zeroFilledSubarray(nums));
    }
}
