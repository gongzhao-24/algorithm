/**
* 描述：leetcode 576. 出界的路径数
* 创建日期：2022年07月20 10:38:
* @author gong zhao 
**/
package 动态规划;

/**
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn]
 * 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 * 
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。
 * 因为答案可能非常大，返回对
 * 109 + 7 取余 后的结果。
 * 
 */

/**
 * 这题好好研究一下是怎么做的，感觉很典型
 * 
 */
public class FindPaths {
    int mod = (int) 1e9 + 7;
    int[][] forwards = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if(maxMove == 0){
            return 0;
        }
        int[][][] dp = new int[maxMove][m][n];
        int ans = 0;
        dp[0][startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            // 因为肯定是从起点开始，然后按照移动次数一步步扩散的，所以先遍历移动次数
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if (dp[i][j][k] > 0) {
                        for (int[] forward : forwards) {
                            int fx = j + forward[0];
                            int fy = k + forward[1];
                            if (fx >= 0 && fx < m && fy >= 0 && fy < n) {
                                if (i + 1 < maxMove) {
                                    dp[i + 1][fx][fy] = (dp[i + 1][fx][fy] + dp[i][j][k]) % mod;
                                }
                            } else {
                                ans = (ans + dp[i][j][k]) % mod;
                                System.out.println("i:" + i + ", j:" + j + "k:" + k + ",ans:" + ans);

                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FindPaths findPaths = new FindPaths();
        System.out.println(findPaths.findPaths(10, 10, 0, 5, 5));
    }
}
