/**
* 描述：leetcode 413
* 创建日期：2022年07月07 22:38:
* @author gong zhao 
**/
package 动态规划;

public class NumberOfArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        int count = 0;
        int preGap = nums[1] - nums[0];
        int preLen = 2;
        int currentCount = 0;
        for (int i = 2; i < nums.length; i++) {
            int gap = nums[i] - nums[i - 1];
            if (gap == preGap) {
                preLen++;
            } else {
                preGap = gap;
                preLen = 2;
                currentCount = 0;
            }
            if (preLen == 3) {
                currentCount = 1;
            }
            if (preLen > 3) {
                currentCount++;
            }
            count += currentCount;

        }
        return count;
        /**
         * 首先确认一个事实，相邻数的差值是固定的。
         */

        /**
         * dp[i] 表示以该数结尾的等差数组的个数，最后把所有的遍历一下就行了，
         */
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        NumberOfArithmeticSlices numberOfArithmeticSlices = new NumberOfArithmeticSlices();
        System.out.println(numberOfArithmeticSlices.numberOfArithmeticSlices(nums));

    }
}
