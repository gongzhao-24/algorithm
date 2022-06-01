/**
* 描述：leetcode 128. 最长连续序列
* 创建日期：2022年05月31 23:59:
* @author gong zhao 
**/
package 序列;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

https://leetcode.cn/problems/longest-consecutive-sequence/solution/by-lfool-jdy4/ 仔细研究
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int minNum = Integer.MAX_VALUE;
        int maxLen = 0;
        for (int num : nums) {
            set.add(num);
            minNum = Math.min(num, minNum);
        }
        for (int num : nums) {

        }
    }
}
