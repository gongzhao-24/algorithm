/**
* 描述：1334. 阈值距离内邻居最少的城市
* 创建日期：2022年05月30 22:07:
* @author gong zhao 
**/
package 最短路径;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FindTheCity {
    List<int[]>[] grid;
    int[][] distances;

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        grid = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new ArrayList<>();
        }
        // 构建图
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int dis = edge[2];
            grid[from].add(new int[] { to, dis });
            grid[to].add(new int[] { from, dis });
        }
        distances = new int[n][n];
        for (int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            dijkstra(i);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int minCount = Integer.MAX_VALUE;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] kDistance = distances[i];
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                } else {
                    if (kDistance[j] <= distanceThreshold) {
                        count++;
                    }
                }
            }
          /*   if (count == 0) {
                continue;
            } */
            if (map.get(count) == null) {
                map.put(count, new ArrayList<>());
            }
            List<Integer> res = map.get(count);
            res.add(i);
            map.put(count, res);
            minCount = Math.min(minCount, count);
        }
        return map.get(minCount).stream().max(Integer::compare).get();
    }

    public void dijkstra(int k) {
        int[] kDistance = distances[k];
        kDistance[k] = 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
        });
        priorityQueue.offer(new int[] { 0, k });
        while (!priorityQueue.isEmpty()) {
            int[] p = priorityQueue.poll();
            int cur = p[1];
            int dis = p[0];
            if (dis > kDistance[cur]) {
                continue;
            }
            for (int[] next : grid[cur]) {
                int nextIndex = next[0];
                int nextDis = next[1] + kDistance[cur];
                if (nextDis < kDistance[nextIndex]) {
                    kDistance[nextIndex] = nextDis;
                    priorityQueue.offer(new int[] { nextDis, nextIndex });
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                // { 0, 1, 3 }, { 1, 2, 1 }, { 1, 3, 4 }, { 2, 3, 1 }
                { 0, 3, 7 }, { 2, 4, 1 }, { 0, 1, 5 }, { 2, 3, 10 }, { 1, 3, 6 }, { 1, 2, 1 }
        };

        FindTheCity findTheCity = new FindTheCity();
        int n = findTheCity.findTheCity(6, edges, 417);
        System.out.println();
    }
}
