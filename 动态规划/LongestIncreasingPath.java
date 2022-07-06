/**
* 描述：leetcode 329
* 创建日期：2022年07月05 19:03:
* @author gong zhao 
**/
package 动态规划;

public class LongestIncreasingPath {
    int[][] forwards = new int[][] {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };
    int[][] matrix;
    int[][] dp;
    int line;
    int row;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        line = matrix.length;
        row = matrix[0].length;
        dp = new int[line][row];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                dfs(i, j);
            }
        }
        int max = 1;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    // 记忆化搜索，dp[i][j]表示以当前为起点的路径的最长值
    public int dfs(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        int max = 0;
        for (int[] forward : forwards) {
            int fx = x + forward[0];
            int fy = y + forward[1];
            if (fx >= 0 && fy >= 0 && fx < line && fy < row) {
                if (matrix[fx][fy] > matrix[x][y]) {
                    max = Math.max(dfs(fx, fy), max);
                }
            }

        }
        dp[x][y] += (max + 1);
        return dp[x][y];
    }
}
