/**
* 描述：leetcode 2127. 参加会议的最多员工数
* 创建日期：2022年07月31 18:27:
* @author gong zhao 
**/
package 拓扑排序;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumInvitations {
    public int maximumInvitations(int[] g) {
        int n = g.length;
        int[] deg = new int[n]; // g 上每个节点的入度
        for (int w : g)
            // 计算g上每一个节点的入度
            deg[w]++;

        int[] maxDepth = new int[n];
        Deque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            // 这里统计入度为0的节点，即为所有节点的起始节点
            if (deg[i] == 0) {
                q.push(i);
            }
        }

        // 拓扑排序，剪掉 g 上的所有树枝
        while (!q.isEmpty()) {
            int v = q.pop();
            maxDepth[v]++;

            int w = g[v]; // v 只有一条出边
            maxDepth[w] = Math.max(maxDepth[w], maxDepth[v]);
            // 减去这个入度，如果没有入度了，那么应该也放入队列中
            if (--deg[w] == 0)
                q.push(w);
        }
        // 经过上面的循环之后,将非环中的点去掉了

        int maxRingSize = 0, sumChainSize = 0;
        for (int i = 0; i < n; i++) {
            if (deg[i] <= 0)
                continue;
            // 遍历基环上的点（拓扑排序后入度大于 0）
            deg[i] = -1;
            int ringSize = 1;
            for (int v = g[i]; v != i; v = g[v]) {
                deg[v] = -1; // 将基环上的点的入度标记为 -1，避免重复访问
                ++ringSize;
            }
            if (ringSize == 2) // 基环大小为 2
                sumChainSize += maxDepth[i] + maxDepth[g[i]] + 2; // 累加两条最长链的长度
            else
                maxRingSize = Math.max(maxRingSize, ringSize); // 取所有基环的最大值
        }
        return Math.max(maxRingSize, sumChainSize);
    }

    public static void main(String[] args) {
        MaximumInvitations maximumInvitations = new MaximumInvitations();
        // int[] g = new int[]{3,3,4,2,3};
        int[] g = new int[] { 1, 2, 3, 2, 3, 3, 4 };
        System.out.println(maximumInvitations.maximumInvitations(g));
    }
}
