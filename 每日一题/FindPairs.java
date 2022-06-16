/**
* 描述：leetcode 532
* 创建日期：2022年06月16 14:22:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindPairs {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Arrays.sort(nums);
        int res = 0;
        for (int num : nums) {
            if (map.get(num) == 0) {
                continue;
            }
            if (k == 0) {
                if (map.get(num) >= 2) {
                    res++;
                }
                map.put(num, 0);
            } else {
                int count = map.getOrDefault(num + k, 0);
                map.put(num, 0);
                res += count > 0 ? 1 : 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindPairs findPairs = new FindPairs();
        int[] nums = new int[] { 1, 3, 1, 5, 4 };
        System.out.println(findPairs.findPairs(nums, 0));
    }
}
