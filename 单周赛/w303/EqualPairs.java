/**
* 描述：
* 创建日期：2022年07月24 10:32:
* @author gong zhao 
**/
package 单周赛.w303;

public class EqualPairs {
    public int equalPairs(int[][] grid) {
        int len = grid.length;
        int ans = 0;
        for (int[] nums : grid) {
            // 这是行
            for (int i = 0; i < len; i++) {
                boolean flag = true;
                for (int j = 0; j < len; j++) {
                    if (nums[j] != grid[j][i]) {
                        flag = false;
                        continue;
                    }
                }
                if (flag) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
