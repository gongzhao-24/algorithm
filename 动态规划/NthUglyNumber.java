/**
* 描述：leetcode 264
* 创建日期：2022年07月01 15:48:
* @author gong zhao 
**/
package 动态规划;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class NthUglyNumber {
    public static int nthUglyNumber(int n) {
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        set.add(1);
        priorityQueue.offer(1);
        int count = 0;
        while (true) {
            int num = priorityQueue.poll();
            count++;
            if (count == n) {
                return num;
            }
            int twoNum = num * 2;
            if (!set.contains(twoNum)) {
                set.add(twoNum);
                priorityQueue.offer(twoNum);
            }
            int threeNum = num * 3;
            if (!set.contains(threeNum)) {
                set.add(threeNum);
                priorityQueue.offer(threeNum);
            }
            int fiveNum = num * 5;
            if (!set.contains(fiveNum)) {
                set.add(fiveNum);
                priorityQueue.offer(fiveNum);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }
}
