/**
* 描述：leetcode 1089
* 创建日期：2022年06月17 09:44:
* @author gong zhao 
**/
package 每日一题;

public class DuplicateZeros {
    public static void duplicateZeros(int[] arr) {
        int left = 0;
        int right = 0;
        while (right < arr.length) {
            if (arr[left] == 0) {
                right += 1;
            }
            if (right >= arr.length - 1) {
                break;
            }
            left++;
            right++;
        }
        // 这里想一想，如果right 超过了应该的长度，那么最后那个o只有一个o，如果没有
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = arr[left--];
            if (arr[i] == 0) {
                if (right == arr.length) {
                    right--;
                    // 什么都不用做
                } else {
                    arr[i - 1] = 0;
                    i--;
                }
                // 结尾的o只是一个o
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        duplicateZeros(arr);
    }
}
