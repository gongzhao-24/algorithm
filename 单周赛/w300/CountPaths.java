/**
* 描述：
* 创建日期：2022年07月03 11:17:
* @author gong zhao 
**/
package 单周赛.w300;

import java.util.Arrays;

public class CountPaths {
    int[][] forwords = new int[][] {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };
    int mod = (int) 1e9 + 7;
    int[][] grid;
    boolean[][] visit;
    int[][] dp;
    int line;
    int row;

    public int countPaths(int[][] grid) {
        line = grid.length;
        row = grid[0].length;
        visit = new boolean[line][row];
        dp = new int[line][row];
        this.grid = grid;
        // dp[i][j] 表示从[i, j]出发的方案数量，
        for (int[] array : dp) {
            Arrays.fill(array, 1);
        }
        int res = 0;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                paths(i, j);
            }
        }
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                res = (res + dp[i][j]) % mod;
            }
        }
        return res;
    }

    public int paths(int x, int y) {
        if (visit[x][y]) {
            return dp[x][y];
        } else {
            for (int[] forword : forwords) {
                int fx = x + forword[0];
                int fy = y + forword[1];
                if (fx >= 0 && fx < line && fy >= 0 && fy < row) {
                    if (grid[x][y] < grid[fx][fy]) {
                        dp[x][y] = (dp[x][y] + paths(fx, fy)) % mod;
                    }
                }

            }
            visit[x][y] = true;
            return dp[x][y];
        }
    }

    public static void main(String[] args) {
        CountPaths countPaths = new CountPaths();
        int[][] grid = new int[][] {
                { 1, 2 }
        };
        System.out.println(countPaths.countPaths(grid));
    }
}
