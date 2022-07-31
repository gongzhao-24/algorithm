/**
* 描述：
* 创建日期：2022年07月31 10:27:
* @author gong zhao 
**/
package 单周赛.w304;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test4 {
    static boolean[] visited;

    public static int longestCycle(int[] edges) {
        int n = edges.length;
        int[] indegree = new int[n];
        for (int num : edges) {
            if (num >= 0) {
                indegree[num]++;
            }
        }

        Deque<Integer> deque = new ArrayDeque();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                deque.offer(i);
            }
        }

        while (!deque.isEmpty()) {
            int num = deque.poll();
            int next = edges[num];
            if (next != -1) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    deque.offer(next);
                }
            }

        }
        // 这一顿操作之后，就只剩下组成换的节点的入度为1了，然后统计就行了
        int maxRingSize = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] <= 0) {
                continue;
            }
            int ringSize = 0;
            for (int start = i; indegree[start] != -1; start = edges[start]) {
                ringSize++;
                indegree[start] = -1;
            }
            maxRingSize = Math.max(maxRingSize, ringSize);
        }
        return maxRingSize;
    }

    public static void main(String[] args) {
        int[] edges = new int[] { 3,3,4,2,3 };
        System.out.println(longestCycle(edges));
    }

}
