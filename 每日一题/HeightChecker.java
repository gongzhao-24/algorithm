/**
* 描述：
* 创建日期：2022年06月13 13:55:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;

public class HeightChecker {
    public int heightChecker(int[] heights) {
        int[] expect = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expect);
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expect[i]) {
                res++;
            }
        }
        return res;
    }
}
