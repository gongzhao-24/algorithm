/**
* 描述：leetcode 542. 01 矩阵
* 创建日期：2022年07月21 10:30:
* @author gong zhao 
**/
package 动态规划;

import java.util.LinkedList;
import java.util.Queue;

public class UpdateMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int[][] forwards = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        Queue<int[]> queue = new LinkedList<>();

        int line = mat.length;
        int row = mat[0].length;

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[] { i, j });
                } else {
                    mat[i][j] = 50000;
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] nums = queue.poll();
                for (int[] forward : forwards) {
                    int fx = forward[0] + nums[0];
                    int fy = forward[1] + nums[1];
                    if (fx >= 0 && fx < line && fy >= 0 && fy < row) {
                        if (mat[fx][fy] != 0) {
                            if(mat[fx][fy] == 50000){
                                queue.add(new int[]{fx, fy});
                            }
                            mat[fx][fy] = Math.min(mat[fx][fy], mat[nums[0]][nums[1]] + 1);
                            
                        }
                    }
                }

            }
        }

        return mat;
    }

    public static void main(String[] args) {
        UpdateMatrix updateMatrix = new UpdateMatrix();
        int[][] mat = new int[][] {
                { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 }
        };
        updateMatrix.updateMatrix(mat);
    }

}
