/**
* 描述：
* 创建日期：2022年05月28 22:47:
* @author gong zhao 
**/
package 双周赛.w79;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MaximumImportance {
    public long maximumImportance(int n, int[][] roads) {
        // 连接最多的点最多，然后慢慢降下来
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            map.put(from, map.getOrDefault(from, 0) + 1);
            map.put(to, map.getOrDefault(to, 0) + 1);
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return o2[1] - o1[1];
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            priorityQueue.offer(new int[] { entry.getKey(), entry.getValue() });
        }
        int value = n;
        Map<Integer, Integer> nodeValue = new HashMap<>();
        while (!priorityQueue.isEmpty()) {
            int[] node = priorityQueue.poll();
            nodeValue.put(node[0], value--);
        }

        long totalValue = 0;
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            totalValue += (nodeValue.get(from) + nodeValue.get(to));
        }
        return totalValue;
    }
}
