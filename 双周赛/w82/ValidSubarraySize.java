/**
* 描述：
* 创建日期：2022年07月11 19:03:
* @author gong zhao 
**/
package 双周赛.w82;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ValidSubarraySize {
    int[] fa;
    int[] sz;

    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++){
            fa[i] = i;
        }
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
        return fa[x];
    }

    public static void main(String[] args) {
        ValidSubarraySize validSubarraySize = new ValidSubarraySize();
        int[] nums = new int[]{1,3,4,3,1};
        int threshold = 6;
        System.out.println(validSubarraySize.validSubarraySize(nums, threshold));
    }
}
