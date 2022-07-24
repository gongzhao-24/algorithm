/**
* 描述：
* 创建日期：2022年05月29 10:55:
* @author gong zhao 
**/
package 单周赛.w295;

import java.util.Arrays;
import java.util.PriorityQueue;


public class MinimumObstacles {
    int[][] forwards = new int[][] {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };

    public int minimumObstacles(int[][] grid) {
        int line = grid.length;
        int row = grid[0].length;
        int[][] dis = new int[line][row];
        for (int[] distances : dis) {
            Arrays.fill(distances, Integer.MAX_VALUE);
        }
        dis[0][0] = 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return dis[o1[0]][o1[1]] - dis[o2[0]][o2[1]];
        });
        priorityQueue.offer(new int[] { 0, 0 });
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            for (int[] forward : forwards) {
                int xf = poll[0] + forward[0];
                int yf = poll[1] + forward[1];
                if (xf >= 0 && xf < line && yf >= 0 && yf < row) {
                    int distance = grid[xf][yf] + dis[poll[0]][poll[1]];
                    if (dis[xf][yf] <= distance) {
                        continue;
                    } else {
                        dis[xf][yf] = distance;
                        priorityQueue.offer(new int[] { xf, yf });
                    }
                }

            }
        }
        return dis[line - 1][row - 1];
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
