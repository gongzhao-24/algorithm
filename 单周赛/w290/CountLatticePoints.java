/**
* 描述：
* 创建日期：2022年07月13 16:42:
* @author gong zhao 
**/
package 单周赛.w290;

import java.util.HashSet;
import java.util.Set;

public class CountLatticePoints {
    /**
     * 1 <= circles.length <= 200
     * circles[i].length == 3
     * 1 <= xi, yi <= 100
     * 1 <= ri <= min(xi, yi)
     * 
     * @param circles
     * @return
     */

    public static int countLatticePoints(int[][] circles) {
        // 圆心与半径确定了，那么落在这个园中间的点数也确定了，再就是把交集处的减去就行了
        Set<Integer> set = new HashSet<>();
        for (int[] circle : circles) {
            int x = circle[0];
            int y = circle[1];
            int r = circle[2];
            for (int i = x - r; i <= x + r; i++) {
                for (int j = y - r; j <= y + r; j++) {
                    if(Math.pow(Math.abs(i - x), 2) + Math.pow(Math.abs(j - y), 2) <= Math.pow(r, 2)){
                        set.add(x * 200 + y);
                    }
                    
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
