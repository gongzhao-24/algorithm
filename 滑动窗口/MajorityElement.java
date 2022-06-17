/**
* 描述：
* 创建日期：2022年06月16 15:33:
* @author gong zhao 
**/
package 滑动窗口;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorityElement {
    public static List<Integer> majorityElement(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        int left = 0;
        int right = 0;
        int currentNum = nums[0];
        while (right < nums.length) {
            if (nums[right] != currentNum) {
                if ((right - left) * 3 > len) {
                    res.add(currentNum);
                }
                currentNum = nums[right];
                left = right;
            }
            if (right == nums.length - 1) {
                if ((right - left + 1) * 3 > len) {
                    res.add(currentNum);
                }
            }
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 3, 2, 3 };
        System.out.println(majorityElement(nums));
    }
}
