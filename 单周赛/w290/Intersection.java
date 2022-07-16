/**
* 描述：
* 创建日期：2022年07月13 16:36:
* @author gong zhao 
**/
package 单周赛.w290;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Intersection {
    public List<Integer> intersection(int[][] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums[0]) {
            list.add(num);
        }
        for (int i = 1; i < nums.length; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums[i]) {
                temp.add(num);
            }
            list.retainAll(temp);
        }
        Collections.sort(list);
        return list;
    }
}
