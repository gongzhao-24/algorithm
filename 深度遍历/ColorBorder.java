/**
* 描述：
* 创建日期：2022年06月14 16:55:
* @author gong zhao 
**/
package 深度遍历;

public class ColorBorder {
    boolean[][] visit;
    int lineLen;
    int rowLen;
    int[][] grid;
    int[][] fowwards = new int[][] {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        this.grid = grid;
        lineLen = grid.length;
        rowLen = grid[0].length;
        visit = new boolean[lineLen][rowLen];
        dfs(row, col, color);
        return grid;
    }

    public void dfs(int x, int y, int color) {
        visit[x][y] = true;
        int value = grid[x][y];
        for (int[] fowward : fowwards) {
            int xf = x + fowward[0];
            int yf = y + fowward[1];
            // 遍历过的需要去掉这个值
            if (xf >= 0 && xf < lineLen && yf >= 0 && yf < rowLen) {
                // 这个点在矩阵范围之内
                if (!visit[xf][yf] && grid[xf][yf] == value) {
                    dfs(xf, yf, color);
                } else if (!visit[xf][yf] && grid[xf][yf] != value) {
                    grid[x][y] = color;
                }
            } else {
                // 四周的点有一个不在，说明这个点是边界，应该染色
                grid[x][y] = color;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 }
        };
        ColorBorder colorBorder = new ColorBorder();
        System.out.println(colorBorder.colorBorder(grid, 1, 1, 2));
        System.out.println();
    }
}
