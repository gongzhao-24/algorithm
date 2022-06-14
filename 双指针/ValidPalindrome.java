/**
* 描述：leetcode 680
* 创建日期：2022年06月13 16:23:
* @author gong zhao 
**/
package 双指针;

public class ValidPalindrome {
    public boolean validPalindrome(String s) {
        if (s.length() <= 2) {
            return true;
        }
        int lo = 0;
        int hi = s.length() - 1;
        int changeCount = 0;
        while (lo < hi) {
            if (s.charAt(lo) == s.charAt(hi)) {
                lo++;
                hi--;
            } else {
                // 当不相等的时候
                if (changeCount > 1) {
                    break;
                }
                changeCount++;
                lo++;
            }
        }
        if (changeCount <= 1) {
            return true;
        }
        // 怎么判断是成功的
        changeCount = 0;
        lo = 0;
        hi = s.length() - 1;
        while (lo <= hi) {
            if (s.charAt(lo) == s.charAt(hi)) {
                lo++;
                hi--;
            } else {
                // 当不相等的时候
                if (changeCount > 1) {
                    break;
                }
                changeCount++;
                hi--;
            }
        }
        if (changeCount <= 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        System.out.println(validPalindrome.validPalindrome("abc"));
    }
}
