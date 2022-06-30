/**
* 描述：
* 创建日期：2022年06月30 10:41:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;

public class NumPrimeArrangements {
    int mod = 1000000007;

    public int numPrimeArrangements(int n) {
        // 质数是大于1的，先计算出来一共有多少位质数，
        int primeCount = countPrimes(n + 1);
        // 结果应该是质数的排列组合数 * 非质数的排列组合数

        int notPrimeCount = n - primeCount;

        long resPrime = res(primeCount);
        long notPrime = res(notPrimeCount);

        return (int)( (resPrime * notPrime) % mod);
    }

    public long res(int n) {
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res = (res * (long) i) % mod;
        }
        return res;
    }

    /**
     * 计算[1, n)中的质数有多少个
     * 
     * @param n
     * @return
     */
    public int countPrimes(int n) {
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
        NumPrimeArrangements numPrimeArrangements = new NumPrimeArrangements();
        System.out.println(numPrimeArrangements.numPrimeArrangements(100));
    }
}
