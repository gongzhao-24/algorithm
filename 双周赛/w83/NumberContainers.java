/**
* 描述：
* 创建日期：2022年07月23 22:49:
* @author gong zhao 
**/
package 双周赛.w83;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NumberContainers {
    /**
     * 肯定有两个map，一个存 index-nums，一个存 num-indexs
     */
    Map<Integer, TreeSet<Integer>> number2Index;
    Map<Integer, Integer> index2Num;

    public NumberContainers() {
        number2Index = new HashMap<>();
        index2Num = new HashMap<>();
    }

    public void change(int index, int number) {
        int old = index2Num.getOrDefault(index, 0);
        if (old != 0) {
            number2Index.get(old).remove(index);
        }
        index2Num.put(index, number);
        if (number2Index.get(number) == null) {
            number2Index.put(number, new TreeSet<>());
        }
        number2Index.get(number).add(index);

    }

    public int find(int number) {
        return number2Index.getOrDefault(number, new TreeSet<>()).isEmpty() ? -1 : number2Index.get(number).first();
    }
}
