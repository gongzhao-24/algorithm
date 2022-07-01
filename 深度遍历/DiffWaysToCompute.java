/**
* 描述：leetcode 241. 为运算表达式设计优先级
* 创建日期：2022年07月01 09:47:
* @author gong zhao 
**/
package 深度遍历;

import java.util.ArrayList;
import java.util.List;

public class DiffWaysToCompute {
    public static List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        // 如果字符串是数字，那么就直接返回
        boolean flag = false;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                flag = true;
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                for (int j = 0; j < left.size(); j++) {
                    for (int k = 0; k < right.size(); k++) {
                        if (c == '+') {
                            res.add(left.get(j) + right.get(k));
                        } else if (c == '-') {
                            res.add(left.get(j) - right.get(k));
                        } else {
                            res.add(left.get(j) * right.get(k));
                        }
                    }
                }
            }
        }
        if (!flag) {
            res.add(Integer.valueOf(expression));
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> list = diffWaysToCompute("2*3-4*5");
        System.out.println();
    }

}
