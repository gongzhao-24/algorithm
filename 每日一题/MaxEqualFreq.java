/**
* 描述：
* 创建日期：2022年08月18 10:08:
* @author gong zhao 
**/
package 每日一题;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxEqualFreq {

    public static int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        Map<Integer, Set<Integer>> mapSet = new HashMap<>();
        // map 中存放的是 integer - count
        // mapSet 中存放的是 count -list
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            /**
             * 到这个数了可行的条件有下面几个：
             * ①：因为只要删除了一个数，剩下的每个数字出现的次数相同，
             * 那么当前的这个数出现的次数，要么是被删的，要么是不被删的，
             */
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
            Set<Integer> set = mapSet.getOrDefault(count, null);
            if (set == null) {
                mapSet.put(count, new HashSet<>());
                set = mapSet.get(count);
                set.add(num);
                if (count != 1) {
                    // 如果count 不等于 0，那么就要去找一找
                    Set<Integer> down = mapSet.getOrDefault(count - 1, null);
                    if (down.size() == 1) {
                        mapSet.remove(count - 1);
                    } else {
                        down.remove(num);
                    }
                }

            } else {
                set.add(num);
                if (count != 1) {
                    // 如果count 不等于 0，那么就要去找一找
                    Set<Integer> down = mapSet.getOrDefault(count - 1, null);
                    if (down.size() == 1) {
                        mapSet.remove(count - 1);
                    } else {
                        down.remove(num);
                    }

                }

            }
            // 现在应该有 count
            if (mapSet.size() == 2) {
                // count ,或者是count - 1，或者是count + 1 而且
                if (mapSet.get(count - 1) != null
                        && mapSet.get(count).size() == 1) {
                    res = i + 1;
                } // 只能减少一个，不能加一个
                if (mapSet.get(count + 1) != null && mapSet.get(count + 1).size() == 1) {
                    res = i + 1;
                }
                if (mapSet.get(1) != null && mapSet.get(1).size() == 1) {
                    res = i + 1;
                }
            } else if (mapSet.size() == 1) {
                if (mapSet.get(1) != null) {
                    res = i + 1;
                } else if (mapSet.get(count).size() == 1) {
                    res = i + 1;
                }

            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 1, 1, 2, 2, 2, 3, 3, 3 };
        System.out.println(maxEqualFreq(nums));
    }
}
