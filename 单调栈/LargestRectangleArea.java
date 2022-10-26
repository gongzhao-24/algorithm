/**
* 描述：
* 创建日期：2022年10月25 17:01:
* @author gong zhao 
**/
package 单调栈;

import java.util.Stack;

public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        /**
         * 记录左边最远的<=自己的是多少
         */
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        int[] minLeft = new int[len];
        int[] minRight = new int[len];
        for (int i = 0; i < len; i++) {
            int num = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= num) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                minLeft[i] = -1;
            } else {
                minLeft[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            int num = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= num) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                minRight[i] = len;
            } else {
                minRight[i] = stack.peek();
            }
            stack.push(i);
        }
        for (int i = 0; i < len; i++) {
            res = Math.max(res, heights[i] * (minRight[i] - minLeft[i] - 1));
        }
        return res;

    }

    public static void main(String[] args) {
        int[] heights = new int[]{1, 1};
        LargestRectangleArea largestRectangleArea = new LargestRectangleArea();
        System.out.println(largestRectangleArea.largestRectangleArea(heights));
    }
}
