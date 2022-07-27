/**
* 描述：求最小公倍数
* 创建日期：2022年07月27 15:01:
* @author gong zhao 
**/
package 数学;


/**
 * 两个数的乘积等于其最大公约数与最小公倍数的乘积。
 * 证明如下：
 * 设两个数为x和y，其最大公约数为a，则最小公倍数为b，则
 * b = (x/a)*(y/a)*a 
 * 即：a*b = x*y
 */

public class Lcm {
    public static int lcm(int x, int y) {
        int gcd = gcd(x, y);
        return x * y / gcd;
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
        int x = 6;
        int y = 8;
        System.out.println(gcd(x, y));
        System.out.println(lcm(x, y));
    }
}
