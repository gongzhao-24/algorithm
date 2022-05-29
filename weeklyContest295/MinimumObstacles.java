/**
* 描述：
* 创建日期：2022年05月29 10:55:
* @author gong zhao 
**/
package weeklyContest295;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.apple.concurrent.Dispatch.Priority;

public class MinimumObstacles {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dpDown = new int[grid.length][grid[0].length];
        int[][] dpUp = new int[grid.length][grid[0].length];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    dpDown[i][j] = dpDown[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dpDown[i][j] = dpDown[i - 1][j] + grid[i][j];
                } else {
                    dpDown[i][j] = Math.min(dpDown[i][j - 1], dpDown[i - 1][j]) + grid[i][j];
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                if (i == m - 1) {
                    dpDown[i][j] = dpDown[i][j + 1] + grid[i][j];
                } else if (j == 0) {
                    dpDown[i][j] = dpDown[i + 1][j] + grid[i][j];
                } else {
                    dpDown[i][j] = Math.min(dpDown[i][j + 1], dpDown[i -+1][j]) + grid[i][j];
                }
            }
        }

        

    }

    public static void main(String[] args) {
        MinimumObstacles minimumObstacles = new MinimumObstacles();
        int[][] grid = new int[][] {
                { 0, 0, 1, 1, 1, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 1, 1, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1, 1, 1, 0, 1, 0 },
                { 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 }, { 1, 0, 1, 0, 0, 0, 1, 1, 1, 0 }
        };
        System.out.println(minimumObstacles.minimumObstacles(grid));
        System.out.println();
    }
}
