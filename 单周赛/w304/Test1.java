/**
* 描述：
* 创建日期：2022年07月31 10:27:
* @author gong zhao 
**/
package 单周赛.w304;

import java.util.HashSet;
import java.util.Set;

public class Test1 {
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num != 0) {
                set.add(num);
            }
        }
        return set.size();
    }
}
