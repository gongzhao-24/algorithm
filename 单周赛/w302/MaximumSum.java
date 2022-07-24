/**
* 描述：
* 创建日期：2022年07月17 10:48:
* @author gong zhao 
**/
package 单周赛.w302;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MaximumSum {
    public static int maximumSum(int[] nums) {
        if (nums.length == 1) {
            return -1;
        }
        int len = nums.length;
        int[] sumArray = new int[len];
        for (int i = 0; i < len; i++) {
            sumArray[i] = cal(nums[i]);
        }
        Integer[] ids = IntStream.range(0, len).boxed().toArray(Integer[]::new);
        Arrays.sort(ids, (o1, o2) -> {
            if(sumArray[o1] == sumArray[o2]){
                return nums[o2] - nums[o1];
            }else{
                return sumArray[o2] - sumArray[o1];
            }
        });
        int maxSum = -1;
        int i = 0;
        int j = 1;
        while (i < len && j < len) {
            if (sumArray[ids[i]] == sumArray[ids[j]]) {
                maxSum = Math.max(nums[ids[i]] + nums[ids[j]], maxSum);
               
            }
            i = j;
            j = j + 1;
        }

        return maxSum;
    }

    public static int cal(int num) {
        int ans = 0;
        while (num > 0) {
            ans += (num % 10);
            num = num / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 279, 169, 463, 252, 94, 455, 423, 315, 288, 64, 494, 337, 409, 283, 283, 477, 248, 8,
                89, 166, 188, 186, 128 };
        System.out.println(maximumSum(nums));
    }
}
