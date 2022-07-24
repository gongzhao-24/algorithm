/**
* 描述：
* 创建日期：2022年06月12 10:42:
* @author gong zhao 
**/
package 单周赛.w297;


public class MinPathCost {
    public static int minPathCost(int[][] grid, int[][] moveCost) {
        int line = grid.length;
        int row = grid[0].length;
        int[][] cost = new int[line][row];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int m = 0; m < row; m++) {
                    int preCost = 0;
                    if (i != 0) {
                        preCost = moveCost[grid[i - 1][m]][j];
                    }
                    int realCost = (i == 0 ? 0 : preCost) + grid[i][j] + (i == 0 ? 0
                            : cost[i - 1][m]);
                    minCost = Math.min(minCost, realCost);
                }
                cost[i][j] = minCost;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int m = 0; m < row; m++) {
            res = Math.min(res, cost[line - 1][m]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                { 5, 3 }, { 4, 0 }, { 2, 1 }
        };
        int[][] moveCost = new int[][] {
                { 9, 8 }, { 1, 5 }, { 10, 12 }, { 18, 6 }, { 2, 4 }, { 14, 3 }
        };
        System.out.println(minPathCost(grid, moveCost));
    }
}
