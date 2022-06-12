/**
* 描述：
* 创建日期：2022年06月08 19:26:
* @author gong zhao 
**/
package 每日一题;

import java.util.HashSet;
import java.util.Set;

public class IsBoomerang {
    public boolean isBoomerang(int[][] points) {
        Set<String> set = new HashSet<>();
        int x = 0;
        int y = 0;
        set.add(points[0][0] + "-" + points[0][1]);
        for (int i = 1; i < points.length; i++) {
            if (i == 1) {
                if (set.contains(points[i][0] + "-" + points[i][1])) {
                    return false;
                }
                set.add(points[i][0] + "-" + points[i][1]);
                x = points[i][0] - points[i - 1][0];
                y = points[i][1] - points[i - 1][1];
                int gcd = gcd(Math.abs(x), Math.abs(y));
                x = (gcd == 0 ? (x == 0 ? 0 : 1) : x / gcd);
                y = (gcd == 0 ? (y == 0 ? 0 : 1) : y / gcd);
                if (x == 0 && y == 0) {
                    return false;
                }
            } else {
                if (set.contains(points[i][0] + "-" + points[i][1])) {
                    return false;
                }
                set.add(points[i][0] + "-" + points[i][1]);
                int one = points[i][0];
                int two = points[i - 1][0];
                int currentX = points[i][0] - points[i - 1][0];
                int currentY = points[i][1] - points[i - 1][1];
                int gcd = gcd(Math.abs(currentX), Math.abs(currentY));
                currentX = (gcd == 0 ? (currentX == 0 ? 0 : 1) : currentX / gcd);
                currentY = (gcd == 0 ? (currentY == 0 ? 0 : 1) : currentY / gcd);
                if (currentX == 0 && currentY == 0) {
                    return false;
                }
                if (currentX * y == x * currentY) {
                    return false;
                }
            }
        }
        return true;
    }

    public int gcd(int x, int y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        return x % y == 0 ? y : gcd(y, x % y);
    }

    public static void main(String[] args) {
       
    }
}
