/**
* 描述：
* 创建日期：2022年07月05 19:53:
* @author gong zhao 
**/
package 动态规划;

import java.util.Arrays;

public class MaxEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        int len = envelopes.length;
        int maxLen = 1;
        int[] dp = new int[len + 1];
        dp[1] = envelopes[0][1];
        for (int i = 1; i < len; i++) {
            // 往前寻找到第一个比这个数小的shi'zi
            int cur = envelopes[i][1];
            int left = binarySearch(1, maxLen, cur, dp);
            // 找不到有比这个数字更小的数了
            if (left == -1) {
                dp[1] = cur;
            } else {
                dp[left + 1] = cur;
                maxLen = Math.max(maxLen, left + 1);
            }
        }
        return maxLen;
    }

    // 寻找左侧第一个小于 target的数
    public int binarySearch(int lo, int hi, int target, int[] dp) {
        int left = lo;
        int right = hi;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (dp[mid] > target) {
                right = mid - 1;
            } else if (dp[mid] == target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 如果所有的数字都比target小，left = right + 1

        // 如果所有的数字都比target 大，right = left - 1
        if (right == lo - 1) {
            return -1;
        }
        return left - 1;
    }

    public static void main(String[] args) {
        MaxEnvelopes maxEnvelopes = new MaxEnvelopes();
        int[][] envelopes = new int[][] {
                { 4, 5 }, { 4, 6 }, { 6, 7 }, { 2, 3 }, { 1, 1 }
        };
        System.out.println(maxEnvelopes.maxEnvelopes(envelopes));
    }
}
