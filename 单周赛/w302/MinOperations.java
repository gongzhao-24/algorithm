/**
* 描述：
* 创建日期：2022年07月17 11:23:
* @author gong zhao 
**/
package 单周赛.w302;

import java.util.Arrays;

public class MinOperations {
    public int minOperations(int[] nums, int[] numsDivide) {
        // 先找 numsDivide中的最小公约数，然后将左边的排序，找到这个数
        // 辗转相除法
        int maxGcd = 1;

        if (numsDivide.length == 1) {
            maxGcd = numsDivide[0];
        } else {
            for (int i = 1; i < numsDivide.length; i++) {
                if (i == 1) {
                    maxGcd = gcd(numsDivide[i - 1], numsDivide[i]);
                } else {
                    maxGcd = gcd(maxGcd, numsDivide[i]);
                }
            }
        }

        // 求出这个最大公约数了，然后去nums中二分找到一个最大的能被它整除的数
        Arrays.sort(nums);
        for (int i = 0; i < nums.length && nums[i] <= maxGcd; i++) {
            if (maxGcd % nums[i] == 0) {
                return i - 0;
            }
        }
        return -1;
    }

    public static int gcd(int x, int y) {
        if (y == 0) {
            return 0;
        }
        // x为较大的值，y为较小的值
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x % y == 0) {
            return y;
        } else {
            return gcd(y, x % y);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 4, 3, 6 };
        int[] numsDivide = new int[] { 8, 2, 6, 10 };
        MinOperations minOperations = new MinOperations();
        System.out.println(minOperations.minOperations(nums, numsDivide));
    }
}
