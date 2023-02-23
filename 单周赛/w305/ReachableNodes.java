/**
 * 描述：
 * 创建日期：2022年10月26 19:40:
 * @author gong zhao
 **/
package 单周赛.w305;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReachableNodes {

  Set<Integer> restrictedSet;
  Set<Integer>[] graph;
  Set<Integer> res;

  public int reachableNodes(int n, int[][] edges, int[] restricted) {
    /**
     * graph 中的set 表示某一个节点
     */
    restrictedSet = new HashSet<>();
    for (int num : restricted) {
      restrictedSet.add(num);
    }

    graph = new HashSet[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      graph[edge[0]].add(edge[1]);
      graph[edge[1]].add(edge[0]);
    }
    res = new HashSet<>();
    res.add(0);
    /** 首先是从0开始的节点 */
    dfs(0);
    return res.size();
  }

  public void dfs(int node) {
    for (int child : graph[node]) {
      if (!restrictedSet.contains(child) && !res.contains(child)) {
        res.add(child);
        dfs(child);
      }
    }
  }

  public static void main(String[] args) {}
}
