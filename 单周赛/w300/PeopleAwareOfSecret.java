/**
* 描述：
* 创建日期：2022年07月03 11:05:
* @author gong zhao 
**/
package 单周赛.w300;

public class PeopleAwareOfSecret {
    int mod = (int) 1e9 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // 需要记录两个数组，一个用户哪天知道了秘密，所以一定会在之后哪一天忘记
        int[] start = new int[n + 1];
        start[1] = 1;
        // 最终只需要统计 从start[n] 到 start[n -forget之间的数量即可]
        // 一个秘密会在 delay 到 forget 之间被分享出去
        for (int i = 1; i <= n; i++) {
            for (int j = i + delay; j < i + forget && j <= n; j++) {
                start[j] = (start[j] + start[i]) % mod;
            }
        }
        int res = 0;
        for (int k = n; k >= n - forget; k--) {
            res = (res + start[k]) % mod;
        }

        return res;
    }

    public static void main(String[] args) {
    }
}
