/**
* 描述：
* 创建日期：2022年07月17 10:32:
* @author gong zhao 
**/
package 单周赛.w302;

import java.util.HashSet;
import java.util.Set;

public class NumberOfPairs {
    public static int[] numberOfPairs(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int count = 0;;
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                set.remove(num);
                count++;
            }
        }
        return new int[]{count, nums.length - count * 2};
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
