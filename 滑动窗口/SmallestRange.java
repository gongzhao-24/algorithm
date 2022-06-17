/**
* 描述：leetcode 632
* 创建日期：2022年06月17 15:13:
* @author gong zhao 
**/
package 滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        //nums中列表的个数
        int size = nums.size();
        Map<Integer, List<Integer>> indices = new HashMap<Integer, List<Integer>>();
        int xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            for (int x : nums.get(i)) {
                //遍历 nums 中的第 i 个列表

                List<Integer> list = indices.getOrDefault(x, new ArrayList<Integer>());
                list.add(i);
                //查询 有多少个列表中出现了 x 这个数值，将列表的位序 放入到list中
                indices.put(x, list);
                //记录最小值
                xMin = Math.min(xMin, x);
                //记录最大值
                xMax = Math.max(xMax, x);
            }
        }

        int[] freq = new int[size];
        int inside = 0;
        int left = xMin, right = xMin - 1;
        int bestLeft = xMin, bestRight = xMax;

        while (right < xMax) {
            right++;
            if (indices.containsKey(right)) {
                for (int x : indices.get(right)) {
                    freq[x]++;
                    if (freq[x] == 1) {
                        inside++;
                    }
                }
                while (inside == size) {
                    if (right - left < bestRight - bestLeft) {
                        bestLeft = left;
                        bestRight = right;
                    }
                    if (indices.containsKey(left)) {
                        for (int x : indices.get(left)) {
                            freq[x]--;
                            if (freq[x] == 0) {
                                inside--;
                            }
                        }
                    }
                    left++;
                }
            }
        }

        return new int[] { bestLeft, bestRight };
    }

}
