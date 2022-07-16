/**
* 描述：
* 创建日期：2022年07月13 16:56:
* @author gong zhao 
**/
package 单周赛.w290;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 1 <= rectangles.length, points.length <= 5 * 10的4次方
 * rectangles[i].length == points[j].length == 2
 * 1 <= li, xj <= 10的9次方
 * 1 <= hi, yj <= 100
 * 所有 rectangles 互不相同 。
 * 所有 points 互不相同
 * 返回包含点的矩形的个数
 */

public class CountRectangles {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int len = rectangles.length;
        Arrays.sort(rectangles, (o1, o2) -> {
            return o2[1] - o1[1];
        });
        int n = points.length;
        Integer[] pIndex = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        // 对points纵坐标从大到小进行排序。
        Arrays.sort(pIndex, (o1, o2) -> {
            return points[o2][1] - points[o1][1];
        });
        int[] ans = new int[n];
        int start = 0;
        boolean flag;
        List<Integer> list = new ArrayList<>();
        for (int index : pIndex) {
            flag = false;
            while (start < len && points[index][1] <= rectangles[start][1]) {
                list.add(rectangles[start++][0]);
                flag = true;
            }
            if (flag) {
                Collections.sort(list, (o1, o2) -> {
                    return o2 - o1;
                });
            }
            int i = binarySearch(list, points[index][0]);
            ans[index] = i;
        }
        return ans;
    }

    // 找到第一个小于target数的坐标，如果没有，则返回0
    public int binarySearch(List<Integer> list, int target) {
        int lo = 0;
        int hi = list.size() - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            int num = list.get(mid);
            if (num == target) {
                lo = mid + 1;
            } else if (num < target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
            // 如果list中所有的数都比target 小，那么lo 最后等于 list.size
            // 如果list中所有的数都比target 大，那么lo 最后等于 0
        }
        return lo;
    }
}
