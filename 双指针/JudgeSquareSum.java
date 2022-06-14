/**
* 描述：LeetCode 633
* 创建日期：2022年06月13 16:13:
* @author gong zhao 
**/
package 双指针;

public class JudgeSquareSum {
    public boolean judgeSquareSum(int c) {
        int hi = (int) Math.sqrt(c + 1);
        int lo = 0;
        while (lo < hi) {
            if (Math.pow(lo, 2) + Math.pow(hi, 2) == c) {
                return true;
            } else if (Math.pow(lo, 2) + Math.pow(hi, 2) < c) {
                lo++;
            } else {
                hi--;
            }
        }
        return false;
    }
}
