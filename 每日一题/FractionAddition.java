/**
* 描述：592. 分数加减运算
* 创建日期：2022年07月27 15:13:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayList;
import java.util.List;

public class FractionAddition {
    public static String fractionAddition(String expression) {
        // 存符号
        List<Character> list = new ArrayList();
        // 存数字
        List<String> num = new ArrayList();

        String str = "";
        for (int i = 0; i < expression.length(); i++) {
            if (i == 0) {
                if (expression.charAt(i) == '-') {
                    list.add('-');
                    continue;
                } else {
                    list.add('+');
                }
            }
            char c = expression.charAt(i);
            if (c == '-' || c == '+') {
                num.add(str);
                str = "";
                list.add(c);
            } else {
                str += c;
            }
        }
        num.add(str);
        int len = num.size();
        // 分子
        int numerator = 0;
        // 分母
        int denominator = 0;

        str = num.get(0);
        String[] array = str.split("\\/");

        numerator = calculate(0, Integer.parseInt(array[0]), list.get(0));
        denominator = Integer.parseInt(array[1]);
        for (int i = 1; i < len; i++) {
            // 先计算分母的最小公倍数，然后求出来 每个数对应的分子应该乘以多少。
            str = num.get(i);
            // 分子分母
            array = str.split("\\/");

            char c = list.get(i);

            // 求出分母的最小公倍数
            int lcm = lcm(denominator, Integer.parseInt(array[1]));

            numerator *= (lcm / denominator);
            int curNumerator = Integer.parseInt(array[0]);
            curNumerator *= (lcm / Integer.parseInt(array[1]));
            // 这里符号有问题，
            numerator = calculate(numerator, curNumerator, c);
            if (numerator != 0) {
                int gcd = gcd(lcm, Math.abs(numerator));
                denominator = lcm / gcd;
                numerator /= gcd;
            } else {
                denominator = 1;
            }
        }
        return numerator + "/" + denominator;
    }

    public static int lcm(int x, int y) {
        int gcd = gcd(x, y);
        return x * y / gcd;
    }

    // x为较大的值，y为较小的值
    public static int gcd(int x, int y) {
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (y == 0) {
            return 0;
        }
        if (x % y == 0) {
            return y;
        }
        return gcd(y, x % y);

    }

    public static int calculate(int x, int y, char c) {
        if (c == '-') {
            return x - y;
        } else {
            return x + y;
        }
    }

    public static void main(String[] args) {
        // "1/3-1/2"
        String expression = "-1/2+1/2";
        System.out.println(fractionAddition(expression));
    }

}
