/**
* 描述：计算数字在二进制下的1的个数
* 创建日期：2022年07月24 17:57:
* @author gong zhao 
**/
package 数学;

public class CountOne {
    public static int countOne(int num) {
        return Integer.bitCount(num);
    }

    public static int countZero(int num) {
        if (num == 0) {
            return 1;
        }
        int moveNum = 1;
        int ans = 0;
        while (moveNum < num) {
            if ((moveNum & num) == 0) {
                ans++;
            }
            moveNum = (moveNum << 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countZero(0));
        System.out.println(countZero(1));
        System.out.println(countZero(2));
        System.out.println(countZero(3));
        System.out.println(countZero(4));
        System.out.println(countZero(5));
        System.out.println(countZero(6));
        System.out.println(countZero(7));
        System.out.println(countZero(8));
    }
}
