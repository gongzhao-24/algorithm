/**
* 描述：
* 创建日期：2022年07月29 10:40:
* @author gong zhao 
**/
package 数学;



/**
 * 正方形判定定理适用于判断一个四边形是否是正方形的判定定理，顺序如下：
 * 先判断一个四边形是菱形，再判断一个四边形是矩形。具体如下：
 * 1、如果两条斜边的中点相同,则说明以该两条斜边组成的四边形为平行四边形
 * 2、在满足条件1的基础上，如果两条斜边的长度相同，则说明以该两条斜边组成的四边形为矩形
 * 3、在满足条件2的基础上，如果两条斜边相互垂直，则说明以该两条斜边组成的四边形为正方形。
 *  
 */
public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // 首先对于每个点，一定有两条边是最短的，其次，这两条边的斜率一定是相互垂直的
        // 分别求出每两个点之间的距离
        long lenP1P2 = (long)Math.pow(p2[0] - p1[0], 2) + (long)Math.pow(p2[1] - p1[1], 2);
        long lenP2P3 = (long)Math.pow(p3[0] - p2[0], 2) + (long)Math.pow(p3[1] - p2[1], 2);
        long lenP3P4 = (long)Math.pow(p4[0] - p3[0], 2) + (long)Math.pow(p4[1] - p3[1], 2);
        long lenP1P4 = (long)Math.pow(p4[0] - p1[0], 2) + (long)Math.pow(p4[1] - p1[1], 2);
        long lenP1P3 = (long)Math.pow(p3[0] - p1[0], 2) + (long)Math.pow(p3[1] - p1[1], 2);
        long lenP2P4 = (long)Math.pow(p4[0] - p2[0], 2) + (long)Math.pow(p4[1] - p2[1], 2);

        // 这六条边里面，一定有四条是最小的，
        long minLen = lenP1P2;
        int count = 1;
        if (lenP2P3 < minLen) {
            count = 1;
            minLen = lenP2P3;
        } else if (lenP2P3 == minLen) {
            count++;
        }
        if (lenP3P4 < minLen) {
            count = 1;
            minLen = lenP3P4;
        } else if (lenP3P4 == minLen) {
            count++;
        }
        if (lenP1P4 < minLen) {
            count = 1;
            minLen = lenP1P4;
        } else if (lenP1P4 == minLen) {
            count++;
        }
        if (lenP1P3 < minLen) {
            count = 1;
            minLen = lenP1P3;
        } else if (lenP1P3 == minLen) {
            count++;
        }
        if (lenP2P4 < minLen) {
            count = 1;
            minLen = lenP2P4;
        } else if (lenP2P4 == minLen) {
            count++;
        }
        if (count != 4) {
            return false;
        }

        // 针对每个点，判断是不是有两条边等于最短的边
        // p1,
        if (lenP1P2 == minLen && lenP1P3 == minLen) {
            if (!checkVertical(p1, p2, p3)) {
                return false;
            }
        } else if (lenP1P2 == minLen && lenP1P4 == minLen) {
            if (!checkVertical(p1, p2, p4)) {
                return false;
            }
        } else if (lenP1P3 == minLen && lenP1P4 == minLen) {
            if (!checkVertical(p1, p3, p4)) {
                return false;
            }
        } else {
            return false;
        }

        // p2,
        if (lenP1P2 == minLen && lenP2P3 == minLen) {
            if (!checkVertical(p2, p1, p3)) {
                return false;
            }
        } else if (lenP1P2 == minLen && lenP2P4 == minLen) {
            if (!checkVertical(p2, p1, p4)) {
                return false;
            }
        } else if (lenP2P3 == minLen && lenP2P4 == minLen) {
            if (!checkVertical(p2, p3, p4)) {
                return false;
            }
        } else {
            return false;
        }

        // p3,
        if (lenP1P3 == minLen && lenP2P3 == minLen) {
            if (!checkVertical(p3, p2, p1)) {
                return false;
            }
        } else if (lenP1P3 == minLen && lenP3P4 == minLen) {
            if (!checkVertical(p3, p1, p4)) {
                return false;
            }
        } else if (lenP2P3 == minLen && lenP3P4 == minLen) {
            if (!checkVertical(p3, p2, p4)) {
                return false;
            }
        } else {
            return false;
        }

        // p1,
        if (lenP1P4 == minLen && lenP2P4 == minLen) {
            if (!checkVertical(p4, p2, p1)) {
                return false;
            }
        } else if (lenP1P4 == minLen && lenP3P4 == minLen) {
            if (!checkVertical(p4, p1, p3)) {
                return false;
            }
        } else if (lenP2P4 == minLen && lenP3P4 == minLen) {
            if (!checkVertical(p4, p3, p2)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    // 判断 xy，与xz 是否构成一个直角
    public boolean checkVertical(int[] x, int[] y, int[] z) {
        int[] xyPointer = new int[] { y[0] - x[0], y[1] - x[1] };
        int[] xzPointer = new int[] { z[0] - x[0], z[1] - x[1] };
        return xzPointer[0] * xyPointer[0] + xzPointer[1] * xyPointer[1] == 0;
    }
}
