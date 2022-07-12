/**
* 描述：leetcode 446
* 创建日期：2022年07月08 11:07:
* @author gong zhao 
**/
package 动态规划;

import java.util.HashMap;
import java.util.Map;

public class NumberOfArithmeticSlices2 {
    /**
     * 给一个整数数组nums，返回nums中所有的等差子序列的数目
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int ans = 0;
        int n = nums.length;
        Map<Long, Integer>[] f = new Map[n];
        for (int i = 0; i < n; ++i) {
            f[i] = new HashMap<Long, Integer>();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long d = 1L * nums[i] - nums[j];
                int cnt = f[j].getOrDefault(d, 0);
                ans += cnt;
                f[i].put(d, f[i].getOrDefault(d, 0) + cnt + 1);
            }
        }
        return ans;
    }
}
