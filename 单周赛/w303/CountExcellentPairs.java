/**
* 描述：
* 创建日期：2022年07月24 17:11:
* @author gong zhao 
**/
package 单周赛.w303;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountExcellentPairs {
    public long countExcellentPairs(int[] nums, int k) {
        
        Set<Integer> set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : set) {
            int count = 0;
            int move = 0;
            int res = 1;
            while (res <= num) {
                if ((num & res) != 0) {
                    count++;
                }
                move++;
                res = (1 << move);
            }
            map.put(count, map.getOrDefault(count, 0) + 1);
        }
        long ans = 0;
        for (Map.Entry<Integer, Integer> entry1 : map.entrySet()) {
            for (Map.Entry<Integer, Integer> entry2 : map.entrySet()) {
                if(entry1.getKey() + entry2.getKey() >= k){
                    ans += (entry1.getValue() * entry2.getValue());
                }
            }
        }
        return ans;
    }
}
