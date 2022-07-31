/**
* 描述：
* 创建日期：2022年07月31 10:27:
* @author gong zhao 
**/
package 单周赛.w304;

import java.util.Arrays;

public class Test2 {
    public int maximumGroups(int[] grades) {
        if (grades.length <= 2) {
            return 1;
        }
        // 先从小到大进行排序
        Arrays.sort(grades);
        // dp[i][j] 表示 最后一个数是i，
        int len = grades.length;
        int i = 1;
        while ((i + 1) * i <= len * 2) {
            i++;
        }
        return i - 1;
    }
}
