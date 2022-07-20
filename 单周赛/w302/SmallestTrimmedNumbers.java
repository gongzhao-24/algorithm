/**
* 描述：
* 创建日期：2022年07月17 11:05:
* @author gong zhao 
**/
package 单周赛.w302;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class SmallestTrimmedNumbers {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int len = queries.length;
        int[] ans = new int[len];
        int n = nums[0].length();
        for (int i = 0; i < len; i++) {
            int[] query = queries[i];
            int k = query[0];
            int trim = query[1];
            List<String> list = new ArrayList<>();

            for (String num : nums) {
                list.add(num.substring(num.length() - trim));
            }
            Integer[] ids = IntStream.range(0, nums.length).boxed().toArray(Integer[]::new);

            Arrays.sort(ids, (o1, o2) -> {
                String o1Str = list.get((int) o1);
                String o2Str = list.get((int) o2);
                for (int j = 0; j < trim; j++) {
                    char left = o1Str.charAt(j);
                    char right = o2Str.charAt(j);
                    if (left != right) {
                        return left - right;
                    }
                }
                return o1 - o2;

            });
            ans[i] = ids[k - 1];
        }
        return ans;

    }
}
