/**
* 描述：leetcode 1456
* 创建日期：2022年06月17 16:35:
* @author gong zhao 
**/
package 滑动窗口;

import java.util.HashSet;
import java.util.Set;

public class MaxVowels {
    public static int maxVowels(String s, int k) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        int len = s.length();
        int maxCount = 0;
        int curCount = 0;
        int left = 0;
        int right = 0;
        while (right < len) {
            if (set.contains(s.charAt(right))) {
                curCount++;
            }
            if (right - left + 1 == k) {
                maxCount = Math.max(maxCount, curCount);
                if (set.contains(s.charAt(left))) {
                    curCount--;
                }
                left++;
            }
            right++;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(maxVowels("abciiidef", 3));
    }
}
