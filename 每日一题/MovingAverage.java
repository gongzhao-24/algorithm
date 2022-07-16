/**
* 描述 leetcode 剑指 Offer II 041. 滑动窗口的平均值
* 创建日期：2022年07月16 10:11:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverage {
    // size大小，第一个数字与最后一个数字
    int size;
    int contain;
    Deque<Integer> deque;
    int sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        deque = new ArrayDeque<>();
        this.size = size;
        this.contain = 0;
    }

    public double next(int val) {
        deque.addLast(val);
        if (contain == 0) {
            sum += val;
            contain++;
            return (double) sum / (double) size;
        }
        //
        if (contain == size) {
            sum -= deque.removeFirst();
            sum += val;
            return (double) sum / (double) size;
        }
        System.out.println();
        return 0;
    }
}
