/**
* 描述：
* 创建日期：2022年07月16 16:51:
* @author gong zhao 
**/
package 单周赛.w286;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindDifference {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < nums1.length || index2 < nums2.length) {
            if (index1 < nums1.length && index2 < nums2.length) {
                if (nums1[index1] == nums2[index2]) {
                    index1++;
                    index2++;
                    while (index1 < nums1.length && nums1[index1] == nums1[index1 - 1]) {
                        index1++;
                    }
                    while (index2 < nums2.length && nums2[index2] == nums2[index2 - 1]) {
                        index2++;
                    }

                } else if (nums1[index1] < nums2[index2]) {
                    list1.add(nums1[index1++]);
                    while (index1 < nums1.length && nums1[index1] == nums1[index1 - 1]) {
                        index1++;
                    }
                } else {
                    list2.add(nums2[index2++]);
                    while (index2 < nums2.length && nums2[index2] == nums2[index2 - 1]) {
                        index2++;
                    }
                }

            } else if (index1 < nums1.length) {
                list1.add(nums1[index1++]);
                while (index1 < nums1.length && nums1[index1] == nums1[index1 - 1]) {
                    index1++;
                }
            } else {
                list2.add(nums2[index2++]);
                while (index2 < nums2.length && nums2[index2] == nums2[index2 - 1]) {
                    index2++;
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(list1);
        ans.add(list2);
        return ans;
    }
}
