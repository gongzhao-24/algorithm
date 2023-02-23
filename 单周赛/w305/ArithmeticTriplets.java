/**
 * 描述：
 * 创建日期：2022年10月26 19:35:
 * @author gong zhao
 **/
package 单周赛.w305;

import java.util.HashSet;
import java.util.Set;

public class ArithmeticTriplets {

  public int arithmeticTriplets(int[] nums, int diff) {
    Set<Integer> set = new HashSet();
    for (int num : nums) {
      set.add(num);
    }
    int res = 0;
    for (int num : nums) {
      if (
        set.contains(num) &&
        set.contains(num + diff) &&
        set.contains(num + diff + diff)
      ) {
        res++;
      }
    }
    return res;
  }
}
