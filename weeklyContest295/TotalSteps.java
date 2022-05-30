/**
* 描述：6080. 使数组按非递减顺序排列
* 创建日期：2022年05月29 10:49:
* @author gong zhao 
**/
package weeklyContest295;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class TotalSteps {
    // 每次移除 nums[i-1] > nums[i] 的 nums[i]，直到nums 变为非递减数组
    public static int totalSteps(int[] nums) {
        //对于每一个nums[i] 它一定是被左侧第一个更大的元素消除的，那就可以用单调栈求左侧第一个更大的元素，
        //如果nums[i] 被 nums[j] (j < i) 消除，那么位于 j 与 i 之间的元素一定首先被消除，使得他们两个元素相邻
        //然后才能使得 nums[i] 被 nums[j] 消除
        // 设 f[i] 为 nums[i] 被消除所需的轮数，那么 f[i]=max(f[j+1]…f[i−1])+1。

        int ans = 0;
        Deque<int[]> st = new ArrayDeque<int[]>();
        for (int num : nums) {
            int maxT = 0;
            while (!st.isEmpty() && st.peek()[0] <= num) {
                maxT = Math.max(maxT, st.peek()[1]);
                st.pop();
            }
            if (!st.isEmpty()) {
                maxT++;
            }
            ans = Math.max(ans, maxT);
            st.push(new int[] { num, maxT });
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,3,4,4,7,3,6,11,8,5,1};
        System.out.println(totalSteps(nums));
    }
}
