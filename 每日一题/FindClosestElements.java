/**
* 描述：
* 创建日期：2022年08月25 10:31:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Arrays.sort(arr);
        int targetIndex = 0;
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int gap = Math.abs(x - arr[i]);
            if (gap < minGap) {
                minGap = gap;
                targetIndex = i;
            }
        }
        // 最靠近的数的index 是 targetIndex
        List<Integer> ans = new ArrayList<>();
        int left = targetIndex - 1;
        int right = targetIndex + 1;
        ans.add(arr[targetIndex]);
        k--;
        while (k > 0) {
            System.out.println("left:" + left + ", right :" + right);
            // 比较left 与 right 谁更靠近
            if (left >= 0 && right < arr.length) {
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    ans.add(arr[left--]);
                } else {
                    ans.add(arr[right++]);
                }
            } else if (left >= 0) {
                ans.add(arr[left--]);
            } else {
                ans.add(arr[right++]);
            }
            k--;
        }
        Collections.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 0, 0, 0, 1, 3, 5, 6, 7, 8, 8 };
        int k = 2;
        int x = 2;
        FindClosestElements findClosestElements = new FindClosestElements();
        System.out.println(findClosestElements.findClosestElements(arr, k, x));
    }
}
