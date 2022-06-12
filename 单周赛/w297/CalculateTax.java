/**
* 描述：
* 创建日期：2022年06月12 10:30:
* @author gong zhao 
**/
package 单周赛.w297;

import java.math.BigDecimal;

public class CalculateTax {
    public static double calculateTax(int[][] brackets, int income) {
        double res = 0;
        int leftIncom = income;
        int pre = 0;
        for (int i = 0; i < brackets.length; i++) {
            // 这个区间的金额是多少
            int upper = brackets[i][0] - pre;
            int percent = brackets[i][1];
            if (leftIncom <= upper) {
                // 这里计算完成就结束了
                res += leftIncom * percent * 0.01;
                break;
            } else {
                leftIncom -= upper;
                res += upper * percent * 0.01;
            }
            pre = brackets[i][0];
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] brackets = new int[][]{
            {1,0},{4,25},{5,50}
        };
        System.out.println(calculateTax(brackets, 2));
    }
}
