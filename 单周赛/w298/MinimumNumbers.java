/**
* 描述：
* 创建日期：2022年06月23 15:02:
* @author gong zhao 
**/
package 单周赛.w298;

/**
 * 给你两个整数 num 和 k ，考虑具有以下属性的正整数多重集：
 * 
 * 每个整数个位数字都是 k 。
 * 所有整数之和是 num 。
 * 返回该多重集的最小大小，如果不存在这样的多重集，返回 -1 。
 * 
 * 注意：
 * 
 * 多重集与集合类似，但多重集可以包含多个同一整数，空多重集的和为 0 。
 * 个位数字 是数字最右边的数位。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-of-numbers-with-units-digit-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class MinimumNumbers {
    public static int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        if (k == 0 && num % 10 != 0) {
            return -1;
        }
        if (k == 0 && num % 10 == 0) {
            return 1;
        }

        /**
         * 假设多重集里面有x个元素，那么将这些元素相加，最后的个位数一定与 x * k 的个位数相同，此时需要比较一下 x * k 与num的大小，如果小于，
         * 则说明 有解，因为只需要在这x个元素的十位随便配置即可满足条件
         */
        int res = 0;
        for (int i = 1; i * k <= num; i++) {
            int temp = i * k;
            char tempChar = String.valueOf(temp).charAt(String.valueOf(temp).length() - 1);
            char numChar = String.valueOf(num).charAt(String.valueOf(num).length() - 1);
            if (temp <= num && tempChar == numChar) {
                res = i;
                break;
            }
        }
        return res == 0 ? -1 : res;
    }

    public static void main(String[] args) {
        System.out.println(minimumNumbers(10, 1));
    }
}
