/**
* 描述：
* 创建日期：2022年07月04 17:17:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(arr);
        // 最小绝对差，那么这两个数绝对是靠拢的
        int maxLen = Integer.MAX_VALUE;
        for (int i = 0, j = 1; j < arr.length; i++, j++) {
            if (arr[j] - arr[i] < maxLen) {
                maxLen = arr[j] - arr[i];
                res.clear();
            } else if (arr[j] - arr[i] == maxLen) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                list.add(arr[j]);
                res.add(list);
            }
        }
        return res;
    }
}
