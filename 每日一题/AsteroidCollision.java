/**
* 描述：leetcode 735
* 创建日期：2022年07月13 11:12:
* @author gong zhao 
**/
package 每日一题;

import java.util.Stack;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        // 5 10 -5 6 -1 2 3 -10
        // 5 10 6 2 -10
        Stack<Integer> stack = new Stack<>();
        for (int num : asteroids) {
            if (stack.isEmpty()) {
                stack.push(num);
            } else {
                // 如果当前的是负数
                while (num < 0 && !stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(num)) {
                    stack.pop();
                }
                if (num < 0 && !stack.isEmpty() && stack.peek() == Math.abs(num)) {
                    stack.pop();
                    continue;
                }
                if (num > 0 || stack.isEmpty() || stack.peek() < 0) {
                    stack.push(num);
                }

            }
        }
        if (stack.isEmpty()) {
            return new int[] {};
        }
        int[] ans = new int[stack.size()];
        int index = stack.size() - 1;
        while (!stack.isEmpty()) {
            ans[index--] = stack.pop();
        }
        return ans;
    }
}
