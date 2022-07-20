/**
* 描述：
* 创建日期：2022年07月17 22:41:
* @author gong zhao 
**/
package 每日一题;

import java.util.Currency;
import java.util.HashSet;

public class ArrayNesting {
    int[] nums;

    public int arrayNesting(int[] nums) {
        int len = nums.length;
        boolean[] visit = new boolean[len];
        int maxLen = 1;
        int cnt;
        for (int i = 0; i < len; i++) {
            cnt = 0;
            while (!visit[i]) {
                visit[i] = true;
                cnt++;
                i = nums[i];
                maxLen = Math.max(maxLen, cnt);
            }

        }
        return maxLen;
    }

    public static void main(String[] args) {
        ArrayNesting arrayNesting = new ArrayNesting();
        int[] nums = new int[] { 5, 4, 0, 3, 1, 6, 2 };
        System.out.println(arrayNesting.arrayNesting(nums));
    }
}
