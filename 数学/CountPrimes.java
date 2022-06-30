/**
* 描述：计算质数数量
* 创建日期：2022年06月30 10:51:
* @author gong zhao 
**/
package 数学;

import java.util.Arrays;

public class CountPrimes {
    public static int countPrimes(int n) {
        /**
         * 1不是质数
         * 如果某一个数不是质数，肯定可以表示成 a * b,或者 a * a
         * 那么在遍历的过程中，如果发现某一个数是质数，就依次标记 a * a,a * (a+1)....一直到n，
         */
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
                // 因为i是质数，所以 i * i,i * (i + 1)一定是合数
                // 为什么从 i* i开始，因为 i * (i - 1) 这个数已经被 i = i-1的时候遍历过了
                long j = (long) i * (long) i;
                for (; j < n; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(499979));
    }
}
