/**
* 描述：leetcode 338
* 创建日期：2022年07月05 19:17:
* @author gong zhao 
**/
package 动态规划;

public class CountBits {
    public static int[] countBits(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 0;
        int high = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                nums[i] = 1;
                high = i;
            } else {
                nums[i] = nums[high] + nums[i - high];
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(countBits(16));
    }
}
