/**
* 描述：
* 创建日期：2022年06月12 11:50:
* @author gong zhao 
**/
package 单周赛.w297;

import java.util.Arrays;

public class DistributeCookies {
    // 大概意思就是将数组分为k分，求最小总和
    int ans = Integer.MAX_VALUE;
    int[] cookies;
    int n;
    int k;

    public int distributeCookies(int[] cookies, int k) {
        Arrays.sort(cookies);
        this.cookies = cookies;
        this.n = cookies.length;
        this.k = k;
        dfs(new int[k], n - 1);
        return ans;
    }

    public void dfs(int[] buckets, int currentCookie) {
        // 当前要分的位置小于0.说明已经分完了
        if (currentCookie < 0) {
            int maxBucket = Integer.MIN_VALUE;
            for (int bucket : buckets) {
                maxBucket = Math.max(maxBucket, bucket);
            }
            ans = Math.min(maxBucket, ans);
        } else {
            for (int bucket : buckets) {
                if (bucket > ans) {
                    return;
                }
            }

            for (int i = 0; i < buckets.length; i++) {
                // 将这个位置的cookie 分给任意一个小朋友
                buckets[i] += cookies[currentCookie];
                dfs(buckets, currentCookie - 1);
                buckets[i] -= cookies[currentCookie];
            }
        }
    }
}
