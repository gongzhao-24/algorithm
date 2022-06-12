/**
* 描述：497. 非重叠矩形中的随机点
* 创建日期：2022年06月09 10:30:
* @author gong zhao 
**/
package 每日一题;

import java.util.Random;

/**
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，
 * 其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i
 * 个矩形的右上角点。
 * 设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。
 * 所有满足要求的点必须等概率被返回。在给定的矩形覆盖的空间内的任何整数点都有可能被返回。请注意 ，整数点是具有整数坐标的点。
 * 
 * 实现 Solution 类:
 * 
 * Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
 * int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 * 
 */

public class Solution {
    /**
     * 由于是随机选择点，而且矩形面积是不重叠的，所以可以先随机选一个矩形，再随机从矩形中随机选一个点。
     * 因为要保证每一个点被选择的几率相同，因此随机选择一个矩形的时候，应该按照它的面积里面覆盖的点个数来选择。
     * 比如总的面积是 aera ，可以从[1, aera] 中随机选一个点，然后使用前缀和与二分，查找到这个面积是属于那个矩形
     * 找到这个矩形，然后再再矩形中随机一个点
     */
    int[] sumArray;
    int[][] rects;
    int sumAera;
    Random random = new Random();

    public Solution(int[][] rects) {
        this.rects = rects;
        this.sumArray = new int[rects.length];
        sumArray[0] = calculateAera(rects[0]);
        for (int i = 1; i < rects.length; i++) {
            sumArray[i] = sumArray[i - 1] + calculateAera(rects[i]);
        }
        this.sumAera = sumArray[rects.length - 1];
    }

    public int[] pick() {
        int aera = 1 + random.nextInt(sumAera);
        // 这里用二分，找出来这个面积应该属于哪个矩形
        int index = findRectangleIndex(aera);
        int[] rect = rects[index];
        return new int[] { rect[0] + random.nextInt(rect[2] - rect[0] + 1),
                rect[1] + random.nextInt(rect[3] - rect[1] + 1) };
    }

    // 这个二分可能有点问题，[1, 3, 5, 10, 15, 16] area = 7
    public int findRectangleIndex(int aera) {
        int lo = 0;
        int hi = rects.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (sumArray[mid] < aera) {
                lo = mid + 1;
            } else if (sumArray[mid] == aera) {
                return mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public int calculateAera(int[] rect) {
        int ai = rect[0];
        int bi = rect[1];
        int xi = rect[2];
        int yi = rect[3];
        //这里的 + 1 表示求的不是面积，而是这个面积里面覆盖的点的个数
        int length = xi - ai + 1;
        int width = yi - bi  +1;
        return length * width;
    }

    public static void main(String[] args) {
        int[][] rects = new int[][] {
                { 82918473, -57180867, 82918476, -57180863 }, { 83793579, 18088559, 83793580, 18088560 },
                { 66574245, 26243152, 66574246, 26243153 }, { 72983930, 11921716, 72983934, 11921720 }
        };
        Solution solution = new Solution(rects);
        while(true){
            int[] res1 = solution.pick();
        }
    }
}
