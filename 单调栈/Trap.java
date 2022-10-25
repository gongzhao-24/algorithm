/**
* 描述：[42] 接雨水
* 创建日期：2022年10月21 15:51:
* @author gong zhao 
**/
package 单调栈;

import java.util.Stack;

public class Trap {
    public int trap(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        int maxHeight = 0;
        int maxHeightIndex = -1;

        for (int i = 0; i < len; i++) {
            int cur = height[i];
            if (cur >= maxHeight) {
                maxHeightIndex = i;
                maxHeight = cur;
                leftMax[i] = i;
            } else {
                leftMax[i] = maxHeightIndex;
            }
        }

        maxHeight = 0;
        maxHeightIndex = -1;

        for (int i = len - 1; i >= 0; i--) {
            int cur = height[i];
            if (cur >= maxHeight) {
                maxHeightIndex = i;
                maxHeight = cur;
                rightMax[i] = i;
            } else {
                rightMax[i] = maxHeightIndex;
            }
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += (Math.min(height[leftMax[i]], height[rightMax[i]]) - height[i] > 0 ? Math.min(height[leftMax[i]], height[rightMax[i]]) - height[i]
                    : 0);
        }
        return res;
    }
}
