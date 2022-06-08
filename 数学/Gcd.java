/**
* 描述：求最大公约数
* 创建日期：2022年06月08 22:11:
* @author gong zhao 
**/
package 数学;

public class Gcd {
    // 辗转相除法
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
        System.out.println(gcd(377, 318));
    }
}
