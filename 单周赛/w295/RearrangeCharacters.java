/**
* 描述：
* 创建日期：2022年05月29 10:30:
* @author gong zhao 
**/
package 单周赛.w295;

import java.util.HashMap;
import java.util.Map;

public class RearrangeCharacters {

    public int rearrangeCharacters(String s, String target) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int minCount = Integer.MAX_VALUE;
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : target.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            minCount = Math.min(map.getOrDefault(entry.getKey(), 0)/tMap.get(entry.getKey()), minCount);
        }
        return minCount;

    }
}
