/**
* 描述：leetcode 313
* 创建日期：2022年07月01 18:43:
* @author gong zhao 
**/
package 动态规划;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NthSuperUglyNumber {
    public static int nthSuperUglyNumber(int n, int[] primes) {
        Set<Integer> set = new HashSet<>();
        // dp[i] 表示第i个超级丑数，因此dp[1] = 1
        int[] dp = new int[n + 1];
        int len = primes.length;
        // pointer[i] 表示 dp 中 dp[i]可以乘以这个数字
        int[] pointer = new int[len];
        Arrays.fill(pointer, 1);
        int[] num = new int[len];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 现在要选出 dp[i]
            int minNum = Integer.MAX_VALUE;
            int minIndex = 0;
            Arrays.fill(num, 0);

            int j = 0;
            while (j < len) {
                num[j] = dp[pointer[j]] * primes[j];
                if (!set.contains(num[j])) {
                    if (num[j] > 0 && num[j] < minNum) {
                        minNum = num[j];
                        minIndex = j;
                    }
                    j++;
                } else {
                    pointer[j]++;
                }
            }
            dp[i] = minNum;
            set.add(minNum);
            pointer[minIndex]++;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] primes = new int[] { 2,3,5,7 };
        System.out.println(nthSuperUglyNumber(5911, primes));
    }
}
