/**
* 描述：leetcode 85
* 创建日期：2022年06月30 15:00:
* @author gong zhao 
**/
package 动态规划;

import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        // 有m行，n列

        // left[i][j]表示第 i 行 第j列，及之前一共有多少个连续的1
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                // width 为 left[i][j] 的宽度

                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    // 从第i-1行继续往上走，找到一条最短的边，
                    width = Math.min(width, left[k][j]);
                    // 因为有可能下面的长边组成的面积比现在的要大
                    area = Math.max(area, (i - k + 1) * width);
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    // 单调栈实现
    public static int maximalRectangle1(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int line = matrix.length;
        int row = matrix[0].length;

        // left[i][j] 表示 第i行第j列的左边（包括自己）最长有多少个1，如果自己是0的话那就等于0
        int[][] left = new int[line][row];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 1 : left[i][j - 1] + 1);
                }
            }
        }

        int aera = 0;
        for (int j = 0; j < row; j++) {
            // up[i] 表示 比第i行的1少的最近的上面的行数是多少
            int[] up = new int[line];
            // down[i] 表示 比第i行的1少的最近的下面的行数是多少
            int[] down = new int[line];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < line; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    up[i] = -1;
                } else {
                    up[i] = stack.peek();
                }
                stack.push(i);
            }

            stack.clear();
            for (int i = line - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    down[i] = line;
                } else {
                    down[i] = stack.peek();
                }
                stack.push(i);
            }
            // 这里遍历up数组与down数组
            for (int i = 0; i < line; i++) {
                if (left[i][j] != 0) {
                    aera = Math.max(aera, left[i][j] * (down[i] - up[i] - 1));
                }
            }

        }
        return aera;
    }

    public static void main(String[] args) {
       /*  char[][] matrix = new char[][]{
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        }; */
        char[][] matrix = new char[][]{
            {'1'}
        };
        int res = maximalRectangle1(matrix);
        System.out.println(res);
    }
}
