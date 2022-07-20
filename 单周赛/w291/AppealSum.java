/**
* 描述：
* 创建日期：2022年07月16 14:57:
* @author gong zhao 
**/
package 单周赛.w291;

import java.util.Arrays;

public class AppealSum {
    public long appealSum(String s) {
        // 需要求所有子字符串的引力和。复杂度只能是 nlogn

        /**
         * abbca 引力和 28
         * ①：计算以s[i]为结尾的所有子串的引力和
         * ②：通过计算 s[i-1]为结婚的所有子串的引力和转移而来。
         */
        long ans = 1l;
        int[] pre = new int[26];
        Arrays.fill(pre, -1);
        long current = 1l;
        pre[s.charAt(0) - 'a'] = 0;
        for (int i = 1; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (pre[c] == -1) {
                // 前面没有出现过
                /**
                 * abc abc:3,bc:2,c:1
                 * abcd abcd:4,bcd:3,cd:2 + 1
                 */
                current += (i + 1);
                ans += current;
                pre[c] = i;
            } else {
                // abcd
                // abcdb
                int preIndex = pre[c];
                current += (i - preIndex);
                ans += current;
                pre[c] = i;
            }
        }
        return ans;
    }
}
