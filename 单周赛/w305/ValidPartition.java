/**
 * 描述：
 * 创建日期：2022年10月26 19:56:
 * @author gong zhao
 **/
package 单周赛.w305;

import java.util.Arrays;

public class ValidPartition {

  public boolean validPartition(int[] nums) {
    /**
     * 如果获得的这些子数组中每个都能满足下述条件 之一 ，则可以称其为数组的一种 有效 划分：
     *
     * 子数组 恰 由 2 个相等元素组成，例如，子数组 [2,2] 。
     * 子数组 恰 由 3 个相等元素组成，例如，子数组 [4,4,4] 。
     * 子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1 。例如，子数组 [3,4,5] ，但是子数组 [1,3,5] 不符合要求。
     */

    /**
     * 一般而言，如果有三个连续相等的元素，会考虑是将这三个组成一个一个子数组，
     * 还是将其中的两个组成一个子数组，然后第三个跟着下一个数字组成
     */

    // 位于数组的第一个，位于相同数组的第二个，位于相同数组的第三个，位于不同数组的第二个，位于不同数组的第三个。
    // 0， 1， 2， 3， 4
    int len = nums.length;
    boolean[][] index = new boolean[len][5];
    index[0][0] = true;
    for (int i = 1; i < len; i++) {
      index[i][0] = index[i - 1][1] || index[i - 1][2] || index[i - 1][4];
      index[i][1] = index[i - 1][0] && (nums[i] == nums[i - 1]);
      index[i][2] = index[i - 1][1] && (nums[i] == nums[i - 1]);
      index[i][3] = index[i - 1][0] && (nums[i] == nums[i - 1] + 1);
      index[i][4] = (i >= 2) && index[i - 1][3] && (nums[i] == nums[i - 1] + 1);
    }
    return index[len - 1][1] || index[len - 1][2] || index[len - 1][4];
  }
}
