/**
* 描述：
* 创建日期：2022年07月20 10:18:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int line = grid.length;
        int row = grid[0].length;
        int allCount = line * row;

        int[][] temp = new int[line][row];

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                int current = i * row + j;
                current = (current + k) % allCount;
                int currentI = current / row;
                int currentJ = current % row;
                temp[currentI][currentJ] = grid[i][j];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int [] nums : temp){
            List<Integer> list = new ArrayList<>();
            for(int num : nums){
                list.add(num);
            }
            ans.add(list);
        }
        return ans;
    }
}
