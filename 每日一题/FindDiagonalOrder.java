/**
* 描述：leetcode 498
* 创建日期：2022年06月14 09:40:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayList;
import java.util.List;

public class FindDiagonalOrder {
    public int[] findDiagonalOrder(int[][] mat) {
        // 遍历的顺序是，y = 0，x从0到line-1
        // 当x == line - 1 时，
        // y从1遍历到row - 1

        int startX = 0;
        int startY = 0;
        int line = mat.length;
        int row = mat[0].length;
        List<Integer> list = new ArrayList();
        boolean flag = true;
        while (startX != line - 1 || startY != row - 1) {
            list.add(mat[startX][startY]);
            if (flag) {
                // 向上走
                if (startX == 0 || startY == row - 1) {
                    // 向上走到顶了，需要向下走了
                    flag = false;
                    if (startX == 0 && startY != row - 1) {
                        startY++;
                    } else {
                        startX++;
                    }
                } else {
                    startX--;
                    startY++;
                }
            } else {
                if (startX == line - 1 || startY == 0) {
                    // 向下走到底了，需要向上走了
                    flag = true;
                    if (startX != line - 1 && startY == 0) {
                        startX++;
                    } else {
                        startY++;
                    }
                } else {
                    startX++;
                    startY--;
                }
                // 向下走
            }
        }
        list.add(mat[startX][startY]);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        FindDiagonalOrder findDiagonalOrder = new FindDiagonalOrder();
        int[][] mat = new int[][] {
                { 1, 2 }, { 3, 4 }
        };
        System.out.println(findDiagonalOrder.findDiagonalOrder(mat));
    }
}
