/**
* 描述：
* 创建日期：2022年07月11 19:03:
* @author gong zhao 
**/
package 双周赛.w82;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * 给你一个整数数组 nums 和一个整数 threshold 。
 * 
 * 找到长度为 k 的 nums 子数组，满足数组中 每个 元素都 大于 threshold / k 。
 * 请你返回满足要求的 任意 子数组的 大小 。如果没有这样的子数组，返回 -1 。
 * 
 */

public class ValidSubarraySize {
    int[] fa, sz;

    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++)
            fa[i] = i;
        sz = new int[n + 1];
        Arrays.fill(sz, 1);

        Integer[] ids = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(ids, (i, j) -> nums[j] - nums[i]);
        for (int i : ids) {
            int j = find(i + 1);
            fa[i] = j; // 合并 i 和 i+1
            sz[j] += sz[i];
            if (nums[i] > threshold / (sz[j] - 1))
                return sz[j] - 1;
        }
        return -1;
    }

    // 非递归并查集
    int find(int x) {
        int f = x;
        while (fa[f] != f)
            f = find(fa[f]);
        int tmp = x;
        while (fa[tmp] != f) {
            int t = tmp;
            tmp = fa[tmp];
            fa[t] = f;
        }
        return f;
    }

    // 单调栈做法
    //
    public int validSubarraySize1(int[] nums, int threshold) {
        int len = nums.length;
        // left[i] 表示左侧第一个小于当前数据的index，如果没有则是-1
        int[] left = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);

        }
        stack.clear();
        int[] right = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < len; i++) {
            int width = right[i] - left[i] - 1;
            if (nums[i] > threshold / width) {
                return width;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ValidSubarraySize validSubarraySize = new ValidSubarraySize();
        int[] nums = new int[] { 1, 3, 4, 3, 1 };
        int threshold = 6;
        System.out.println(validSubarraySize.validSubarraySize1(nums, threshold));
    }
}
