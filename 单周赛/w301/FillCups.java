/**
* 描述：
* 创建日期：2022年07月10 10:30:
* @author gong zhao 
**/
package 单周赛.w301;

import java.util.PriorityQueue;

public class FillCups {
    public int fillCups(int[] amount) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        for (int mun : amount) {
            priorityQueue.offer(mun);
        }
        int max = priorityQueue.poll();
        int count = 0;
        while (max > 0) {

            int next = priorityQueue.isEmpty() ? 0 : priorityQueue.poll();
            if (next > 0) {
                count++;
                priorityQueue.offer(max - 1);
                priorityQueue.offer(next - 1);
            } else {
                count++;
                priorityQueue.offer(max - 1);
            }
            max = priorityQueue.poll();
        }
        return count;
    }

    public static void main(String[] args) {
        FillCups fillCups = new FillCups();
        int[] amount = new int[] { 5, 0, 0 };
        System.out.println(fillCups.fillCups(amount));
    }
}
