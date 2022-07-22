/**
* 描述：
* 创建日期：2022年07月21 11:19:
* @author gong zhao 
**/
package 单周赛.w296;

import java.util.Arrays;

public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int currentMin = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - currentMin > k) {
                currentMin = nums[i];
                ans++;
            }
        }
        ans++;
        return ans;
    }
}
