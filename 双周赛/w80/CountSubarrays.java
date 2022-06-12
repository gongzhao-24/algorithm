/**
* 描述：
* 创建日期：2022年06月11 23:07:
* @author gong zhao 
**/
package 双周赛.w80;

public class CountSubarrays {
    public long countSubarrays(int[] nums, long k) {
        long sum = 0;
        int res = 0;
        for (int left = 0, right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum * (right - left + 1) >= k) {
                sum -= (nums[left++]);
            }
            res += (right - left + 1);
        }
        return res;
    }
}
