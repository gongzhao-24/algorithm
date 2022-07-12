/**
* 描述：
* 创建日期：2022年07月12 10:29:
* @author gong zhao 
**/
package 每日一题;

public class OddCells {
    public int oddCells(int m, int n, int[][] indices) {
        // 用两个数组，分别记录 line 和 row 的数字，然后
        int[] lineArray = new int[m];
        int[] rowArray = new int[n];

        for (int[] indice : indices) {
            int line = indice[0];
            int row = indice[1];
            lineArray[line]++;
            rowArray[row]++;
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((lineArray[i] + rowArray[j]) % 2 == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
