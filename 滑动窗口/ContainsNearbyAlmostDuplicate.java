/**
* 描述：leetcode 220
* 创建日期：2022年06月16 20:38:
* @author gong zhao 
**/
package 滑动窗口;

import java.util.TreeSet;

public class ContainsNearbyAlmostDuplicate {
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length == 0) {
            return false;
        }
        if (k == 0 && t != 0) {
            return false;
        }

        // 保持一个 长度为 k的滑动窗口，然后在数据的左侧判断，是否有合适的数据存在于这个窗口中
        int len = nums.length;
        TreeSet<Long> treeSet = new TreeSet<>();
        treeSet.add((long) nums[0]);
        int left = 0;
        int right = 1;
        while (right < len) {
            // 一定保证当前[left, right] 这个数据范围是合适的
            long num = (long) nums[right];
            Long ceiling = treeSet.ceiling(num);
            Long floor = treeSet.floor(num);
            if (ceiling != null && ceiling - num <= t) {
                return true;
            }
            if (floor != null && num - floor <= t) {
                return true;
            }
            treeSet.add(num);
            if (right - left == k) {
                treeSet.remove((long) nums[left]);
                left++;
            }
            right++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2 };
        int k = 0;
        int t = 1;
        System.out.println(containsNearbyAlmostDuplicate(nums, k, t));
    }
}
