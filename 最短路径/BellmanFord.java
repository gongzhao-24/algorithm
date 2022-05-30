/**
* 描述：Bellman-Ford算法
* 创建日期：2022年05月30 20:50:
* @author gong zhao 
**/
package 最短路径;

import java.util.Arrays;

/*
Bellman-Ford算法：
    特点：也属于单源最短路径算法，能检测负权环（指的是存在至少一条负权边的环）是否存在
    松弛：用节点 A「更新」节点 B 的意思是，用起点到节点 A 的最短路长度加上从节点 A 到节点 B 的边的长度，
        去比较起点到节点 B 的最短路长度，如果前者小于后者，就用前者更新后者。这种操作也被叫做「松弛」。

    原理：对所有的边进行n-1轮松弛操作，因为在一个含有n个顶点的图中，任意两点之间的最短路径最多包含n-1边。
         换句话说，第1轮在对所有的边进行松弛后，得到的是源点最多经过一条边到达其他顶点的最短距离；
         第2轮在对所有的边进行松弛后，得到的是源点最多经过两条边到达其他顶点的最短距离；
         第3轮在对所有的边进行松弛后，得到的是源点最多经过一条边到达其他顶点的最短距离......

    核心代码如下：
         for (var i = 0; i < n - 1; i++) {
            for (var j = 0; j < m; j++) {//对m条边进行循环
                var edge = edges[j];
                // 松弛操作
                if (distance[edge.to] > distance[edge.from] + edge.weight ){ 
                    distance[edge.to] = distance[edge.from] + edge.weight;
                }
            }
        }
    
        其中， n为顶点的个数，m为边的个数，edges数组储存了所有边，distance数组是源点到所有点的距离估计值，
        循环结束后就是最小值。内层循环就是对源点与边的两个顶点进行松弛操作。
        那么我们需要进行多少次内层循环进行松弛呢？最坏的情况就是n个顶点连成一条直线，那我们只需要n-1次循环就可以完成。
        那么我们怎么去判断有没有负权回路呢？再对边进行一次内部循环，如果还有可以松弛的点，那么说明肯定有负权回路。
*/

public class BellmanFord {
    /**
     * 例题1：leetcode 743:
     * 有 n 个网络节点，标记为 1 到 n。给你一个列表 times，表示信号经过 有向 边的传递时间。 
     * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 这个数组代表从k节点到各个节点的最短距离
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        // k自己到自己的距离是0
        k -= 1;
        distances[k] = 0;
        // 有n个节点，就说明至少需要进行n-1轮次的松弛
        for (int i = 0; i < n - 1; i++) {
            for (int[] time : times) {
                int from = time[0] - 1;
                int to = time[1] - 1;
                int dis = time[2];
                if (distances[from] == Integer.MAX_VALUE) {
                    continue;
                } else {
                    if (distances[to] > distances[from] + dis) {
                        distances[to] = distances[from] + dis;
                    }
                }

            }
        }
        int res = Arrays.stream(distances).max().getAsInt();
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 代表例题2：leetcode 787
     * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，
     * 表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
     * 你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。
     * 如果不存在这样的路线，则输出 -1。
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /**
         * [限制最多经过不超过k个点] 等价于「限制最多不超过 k + 1条边]
         * 限制最多不超过 k + 1 条边，就可以正好适用于bellman-ford算法中，限制松弛次数，松弛一次是经过一条边
         * 则松弛 k + 1 次则是不超过 k + 1 条边。
         */
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;
        int[] pre = new int[n];
        for (int i = 0; i <= k; i++) {
            // 比如说，起点是A，那么第一轮只能松弛从A出发的所有的边，
            // 如果第一条边是 AB，第二条边是BC，那么如果基于当前的松弛结果进行松弛就有可能第一轮松弛到了 A->C
            // 这种就有问题： 因此只要规定了松弛的次数，每次松弛的起点只能基于上一轮的结果
            pre = distances.clone();
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int dis = flight[2];
                if (pre[from] != Integer.MAX_VALUE && dis + pre[from] < distances[to]) {
                    distances[to] = pre[from] + dis;
                }

            }
        }
        return distances[dst] == Integer.MAX_VALUE ? -1 : distances[dst];
    }

    public static void main(String[] args) {
        int[][] flights = new int[][] {
                { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 }
        };

        BellmanFord bellmanFord = new BellmanFord();
        int res = bellmanFord.findCheapestPrice(4, flights, 0, 3, 1);
        System.out.println(res);
    }
}
