/**
* 描述：leetcode 526. 优美的排列
* 创建日期：2022年07月20 19:02:
* @author gong zhao 
**/
package 动态规划;

import java.util.HashSet;
import java.util.Set;


/**
 * 状态压缩DP，事后研究
 */

public class CountArrangement {

    int ans = 0;

    public int countArrangement(int n) {
        Set<Integer> set = new HashSet<>();
        dfs(set, 1, n);
        return ans;
    }

    public void dfs(Set<Integer> set, int currentIndex, int n) {
        if (currentIndex == n + 1) {
            ans++;
        } else {
            for (int i = 1; i <= n; i++) {
                if (!set.contains(i) && (currentIndex % i == 0 || i % currentIndex == 0)) {
                    // 如果没有选
                    set.add(i);
                    dfs(set, currentIndex + 1, n);
                    set.remove(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        CountArrangement countArrangement = new CountArrangement();
        System.out.println(countArrangement.countArrangement(3));
    }
}
