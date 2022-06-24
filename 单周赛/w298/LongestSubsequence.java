/**
* 描述：leetcode 2311. 小于等于 K 的最长二进制子序列
* 创建日期：2022年06月23 15:25:
* @author gong zhao 
**/
package 单周赛.w298;

import javax.security.auth.kerberos.ServicePermission;
import javax.sound.sampled.SourceDataLine;

public class LongestSubsequence {
    // 最长子序列，而且得出来的值小于或等于k
    // 是不是先求出k的二进制字符串，
    public static int longestSubsequence(String s, int k) {
        // 先统计有多少个1
        int numberOneCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                numberOneCount++;
            }
        }
        int numberOneUsed = 0;
        int maxLen = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 0) {
                continue;
            } else {
                if (s.length() - i > 32) {
                    break;
                }
                if (Integer.parseInt(s.substring(i), 2) <= k) {
                    if (s.charAt(i) == '1') {
                        numberOneUsed++;
                    }
                    int currentLen = s.length() - (numberOneCount - numberOneUsed);
                    maxLen = Math.max(maxLen, currentLen);
                } else {
                    break;
                }
            }

        }
        return maxLen;

    }

    public static void main(String[] args) {
        String s = "0";
        System.out.println(longestSubsequence(s, 5));
    }
}
