/**
* 描述：
* 创建日期：2022年05月28 22:33:
* @author gong zhao 
**/
package BiweeklyContest79;

import java.util.HashMap;
import java.util.Map;

public class DigitCount {
    public boolean digitCount(String num) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num.length(); i++) {
            map.put( num.charAt(i) - '0',map.getOrDefault( num.charAt(i) - '0', 0) + 1);
        }
        for (int i = 0; i < num.length(); i++) {
            if ((num.charAt(i) - '0') != map.getOrDefault(i, 0)){
                return false;
            }
        }
        return true;
    }
}
