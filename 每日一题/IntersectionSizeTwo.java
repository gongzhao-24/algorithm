/**
* 描述：
* 创建日期：2022年07月22 10:21:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;

public class IntersectionSizeTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        // 首先对数组进行排序，右端点从小到大，左端点从大到小
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });
        int ans = 0;
        // 第二大的点
        int a = -1;
        // 第一大的点
        int b = -1;
        for (int[] interval : intervals) {
            if (interval[0] > b) {
                // 这个区间完全不在这个点集之内
                ans += 2;
                b = interval[1];
                a = b - 1;
            } else if (interval[0] > a) {
                // 当前区间 与 已有的点集的集合 只有最大的那个点b
                int temp = b;
                b = interval[1];
                a = temp;
                ans += 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] {
                { 2, 10 },
                { 3, 7 },
                { 3, 15 },
                { 4, 11 },
                { 6, 12 },
                { 6, 16 },
                { 7, 8 },
                { 7, 11 },
                { 7, 15 },
                { 11, 12 }
        };
        IntersectionSizeTwo intersectionSizeTwo = new IntersectionSizeTwo();
        System.out.println(intersectionSizeTwo.intersectionSizeTwo(intervals));

    }

}
