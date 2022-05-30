/**
* 描述：迪杰斯特拉算法
* 创建日期：2022年05月30 20:49:
* @author gong zhao 
**/
package 最短路径;
/*
迪杰斯特拉算法：
    特点：只能求单源最短路径，通俗的讲，就是该算法只能求出一个点到其他点的最短路径
    限制：图中不能有负边权，也就是图中不能有权值为负的边。
    

    原理：该算法的主要思想是贪心，将所有的节点分为两类：已经确定从起点到当前点的最短长度的节点，
         以及未确定从起点到当前节点的长度的节点（即，未确定节点 和 已确定节点）。
         每次从「未确定节点」中取一个与起点距离最短的点，将它归类为「已确定节点」，
         并用它「更新」从起点到其他所有「未确定节点」的距离。直到所有点都被归类为「已确定节点」。

        比如说：用节点 A「更新」节点 B 的意思是，用起点到节点 A 的最短路长度加上从节点 A 到节点 B 的边的长度，
        去比较起点到节点 B 的最短路长度，如果前者小于后者，就用前者更新后者。这种操作也被叫做「松弛」。
        这里暗含的信息是：每次选择「未确定节点」时，起点到它的最短路径的长度可以被确定。
        可以这样理解，因为我们已经用了每一个「已确定节点」更新过了当前节点，无需再次更新（因为一个点不能多次到达）。
        而当前节点已经是所有「未确定节点」中与起点距离最短的点，不可能被其它「未确定节点」更新。
        所以当前节点可以被归类为「已确定节点」。
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    /**
     * 例题1：leetcode 743:
     * 有 n 个网络节点，标记为 1 到 n。给你一个列表 times，表示信号经过 有向 边的传递时间。 
     * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 首先构建图
        List<int[]>[] grid = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            int from = time[0] - 1;
            int to = time[1] - 1;
            int distance = time[2];
            grid[from].add(new int[] { to, distance });
        }
        // 这个数组代表从k节点到各个节点的最短距离
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        // k自己到自己的距离是0
        k -= 1;
        distances[k] = 0;
        // 这个优先队列的目的就是始终保证，每次总是取出当前节点到其他节点路径最短的那个节点
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
        });
        priorityQueue.offer(new int[] { 0, k });
        while (!priorityQueue.isEmpty()) {
            int[] p = priorityQueue.poll();
            int current = p[1];
            int dis = p[0];
            if (distances[current] < dis) {
                // 如果上一个节点到当前节点的距离，已经超过了起点到当前节点的距离，那么就不用算了，通过某个节点中转的路径一定不是最短路径了。
                // 例如说：起点是A，中转点是B，终点是C，现在 AC = 2，BC = 3，那么显然如果通过B中转到C即 AB + BC 肯定大于AC，
                // 所以这个中转不用计算了。
                continue;
            }
            for (int[] nextNode : grid[current]) {
                int next = nextNode[0];
                int nextDis = nextNode[1] + distances[current];
                if (nextDis < distances[next]) {
                    // 只有当前中转的距离比最开始其他中转方案的距离近，才有意义
                    distances[next] = nextDis;
                    priorityQueue.offer(new int[] { nextDis, next });
                }
            }
        }
        int ans = Arrays.stream(distances).max().getAsInt();
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

}
