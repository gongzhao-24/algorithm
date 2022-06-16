/**
* 描述：
* 创建日期：2022年06月16 15:00:
* @author gong zhao 
**/
package 滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindRepeatedDnaSequences {
    public List<String> findRepeatedDnaSequences(String s) {
        /**
         * 思考，在DNA分子中出现不止一次的长度为10的序列
         */
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        int left = 0;
        int right = 10;
        while (right <= s.length()) {
            String str = s.substring(left, right);
            map.put(str, map.getOrDefault(str, 0) + 1);
            if (map.get(str) == 2) {
                res.add(str);
            }
            left++;
            right++;
        }
        return res;
    }
}
