/**
* 描述：856. 括号的分数
* 创建日期：2022年10月09 18:46:
* @author gong zhao 
**/
package 每日一题;

public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        // 肯定是深度遍历
        return dfs(s);
    }

    public int dfs(String s) {
        // 首先保证s是一个完整的闭环括号，所以
        if (s.length() == 2) {
            return 1;
        }
        int left = 1;
        int currentIndex = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                left--;
            } else {
                left++;
            }
            if (left == 0) {
                currentIndex = i;
                break;
            }
        }
        if (currentIndex == s.length() - 1) {
            return 2 * dfs(s.substring(1, currentIndex));
        } else {
            return (currentIndex == 1 ? 1 : 2 * dfs(s.substring(1, currentIndex)))
                            + dfs(s.substring(currentIndex + 1));
        }
    }

    public static void main(String[] args) {
        ScoreOfParentheses scoreOfParentheses = new ScoreOfParentheses();
        String s = "(()(()))()";
        System.out.println(scoreOfParentheses.scoreOfParentheses(s));
    }
}
