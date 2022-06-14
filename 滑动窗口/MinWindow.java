/**
* 描述：leetcode 76
* 创建日期：2022年06月13 22:39:
* @author gong zhao 
**/
package 滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class MinWindow {
    public String minWindow(String s, String t) {
        // 先滑动右窗口，使得窗口中的字符串包含了t中的所有字符串，
        // 然后再收缩左边界，使得窗口最小，
        // 当收缩到左边界已经不足以全部包含t中的所有字符的时候，再扩大右窗口
        // 一直继续上面的循环
        int[] sContains = new int[128];
        int[] tContains = new int[128];
        for (char c : t.toCharArray()) {
            tContains[c]++;
        }
        int right = 1;
        int left = 0;
        int contain = 0;
        int minLen = s.length() + 1;
        String ans = "";
        // 字符串的范围为[left, right)，即包左不包右
        while (right <= s.length()) {
            // right++;
            char c = s.charAt(right - 1);
            sContains[c]++;
            if (sContains[c] <= tContains[c]) {
                contain++;
            }
            while (contain == t.length()) {
                int len = right - left;
                if (len < minLen) {
                    ans = s.substring(left, right);
                    minLen = len;
                }
                char leftChar = s.charAt(left);
                sContains[leftChar]--;
                if (sContains[leftChar] < tContains[leftChar]) {
                    contain--;
                }
                left++;
            }
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        System.out.println(minWindow.minWindow("aa", "aa"));
    }
}
