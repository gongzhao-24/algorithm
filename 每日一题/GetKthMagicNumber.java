/**
* 描述：面试题 17.09. 第 k 个数
* 创建日期：2022年09月28 11:07:
* @author gong zhao 
**/
package 每日一题;

import java.nio.channels.Pipe;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class GetKthMagicNumber {
    public int getKthMagicNumber(int k) {
        // 是 1，3，5，7，9，15，21。
        // 单独成组的数有 3 5 7 ，
        return sulotion3(k);
    }

    /**
     * 方法1：最小堆
     * 首先将 1 放入堆中，然后每次从堆里面取出一个数x，
     * 然后 x*3，x*5，x*7 也是符合要求的，也放入堆中，同时使用哈希表进行去充，第k次取出的数字即为所求
     */
    private int sulotion1(int k) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(1L);
        Set<Long> set = new HashSet<>();
        set.add(1L);
        long ans = 0;
        while (k > 0) {
            ans = priorityQueue.poll();
            if (!set.contains(ans * 3)) {
                priorityQueue.offer(ans * 3);
                set.add(ans * 3);
            }
            if (!set.contains(ans * 5)) {
                priorityQueue.offer(ans * 5);
                set.add(ans * 5);
            }
            if (!set.contains(ans * 7)) {
                priorityQueue.offer(ans * 7);
                set.add(ans * 7);
            }
            k--;
        }
        return (int) ans;
    }

    private int sulotion2(int k) {
        /**
         * ugly0 * 3 ugly1 * 3 ugly2 * 3
         * ugly0 * 5 ugly1 * 5 ugly2 * 5
         * ugly0 * 7 ugly1 * 7 ugly2 * 7
         * 每次从这三个数组中，选出一个最小的 比如说 ugly0 = 1，
         */
        if (k == 1) {
            return 1;
        }
        long start = 1;
        k--;
        Queue<Long> p1 = new LinkedList<>();
        Queue<Long> p2 = new LinkedList<>();
        Queue<Long> p3 = new LinkedList<>();

        p1.add(1L);
        p2.add(1L);
        p3.add(1L);

        while (k > 0) {
            long current1 = p1.peek() * 3;
            long current2 = p2.peek() * 5;
            long current3 = p3.peek() * 7;

            long min = Math.min(Math.min(current1, current2), current3);
            if (current1 == min) {
                p1.poll();
            }
            if (current2 == min) {
                p2.poll();
            }
            if (current3 == min) {
                p3.poll();
            }
            start = min;
            p1.add(min);
            p2.add(min);
            p3.add(min);
            k--;
        }
        return (int) start;
    }

    public int sulotion3(int k) {
        if (k == 1) {
            return 1;
        }
        int[] array = new int[k];
        array[0] = 1;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        for (int index = 1; index < k; index++) {
            int p1Num = array[p1] * 3;
            int p2Num = array[p2] * 5;
            int p3Num = array[p3] * 7;

            int min = Math.min(Math.min(p1Num, p2Num), p3Num);
            if (p1Num == min) {
                p1++;
            }
            if (p2Num == min) {
                p2++;
            }
            if (p3Num == min) {
                p3++;
            }
            array[index] = min;
        }
        return array[k - 1];
    }

    public static void main(String[] args) {
        GetKthMagicNumber getKthMagicNumber = new GetKthMagicNumber();
        // System.out.println(getKthMagicNumber.getKthMagicNumber(643));
        System.out.println(getKthMagicNumber.getKthMagicNumber(643));
    }
}
