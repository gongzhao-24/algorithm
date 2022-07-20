/**
* 描述：
* 创建日期：2022年07月16 14:39:
* @author gong zhao 
**/
package 单周赛.w291;

import java.util.HashSet;
import java.util.Set;

public class CountDistinct {
    public int countDistinct(int[] nums, int k, int p) {
        Set<String> set = new HashSet<>();
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            String str = "";
            int cout = 0;
            for (int j = i + 1; j <= nums.length; j++) {
                if (nums[j - 1] % p == 0) {
                    cout++;
                }
                if (cout > k) {
                    break;
                }
                str += "#" + nums[j - 1];
                if (!set.contains(str)) {
                    set.add(str);
                    ans++;
                }
            }
        }
        // 要求子数组中最多 k 个可被 p 整除的元素。
        return ans;
    }
}
