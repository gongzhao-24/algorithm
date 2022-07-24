/**
* 描述：
* 创建日期：2022年07月23 21:35:
* @author gong zhao 
**/
package 拓扑排序;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class EventualSafeNodes {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        // 先把所有的出度为0的节点找出来
        int n = graph.length;
        HashSet<Integer> ans = new HashSet<Integer>();

        HashSet<Integer> res = new HashSet<Integer>();

        // 统计有多少节点进入当前节点
        HashSet<Integer>[] outdegree = new HashSet[n];

        // 统计有多少节点从当前节点出去
        HashSet<Integer>[] indegree = new HashSet[n];
        for (int i = 0; i < n; i++) {
            indegree[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            if (graph[i].length == 0) {
                ans.add(i);
            } else {
                int[] nums = graph[i];
                for (int num : nums) {
                    indegree[i].add(num);
                  //  outdegree[num].add(i);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int num : indegree[i]) {
                if (!ans.contains(num)) {
                    flag = false;
                    continue;
                }
            }
            if (flag) {
                res.add(i);
            }
        }
        res.addAll(ans);
        List<Integer> list = new ArrayList<>();
        for(int num : res){
            list.add(num);
        }
        Collections.sort(list);
        return list;
    }
}
