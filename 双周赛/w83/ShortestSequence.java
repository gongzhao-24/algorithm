/**
* 描述：
* 创建日期：2022年07月23 23:01:
* @author gong zhao 
**/
package 双周赛.w83;

import java.util.HashSet;
import java.util.Set;

public class ShortestSequence {
    public int shortestSequence(int[] rolls, int k) {
        int min = 1;
        Set<Integer> set = new HashSet<>();
        for (int num : rolls) {
            set.add(num);
            if(set.size() == k){
                min ++;
                set.clear();
            }
        }
        return min;
    }
}
