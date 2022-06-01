/**
* 描述：
* 创建日期：2022年05月31 09:49:
* @author gong zhao 
**/
package 最短路径;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Floyd 算法是判断多源最短路径的算法,
 * 
 * 原理：Floyd算法可以将问题分解:
 * 第一、先找出最短的距离
 * 第二、然后在考虑如何找出对应的行进路线。
 * 如何找出最短路径呢，这里还是用到动态规划的知识，对于任何一个城市而言，
 * i到j的最短距离不外乎存在经过i与j之间经过k和不经过k两种可能，所以可以令k=1，2，3，...，n(n是城市的数目)，
 * 在检查d(ij)与d(ik)+d(kj)的值；在此 d(ik)与d(kj)分别是目前为止所知道的i到k与k到j的最短距离，
 * 因此d(ik)+d(kj)就是i到j经过k的最短距离。所以，若有d(ij)>d(ik)+d(kj)，
 * 就表示从i出发经过k再到j的距离要比原来的i到j距离短，自然把i到j的d(ij)重写为d(ik)+d(kj)，
 * 每当一个k查完了，d(ij)就是目前的i到j的最短距离。重复这一过程，最后当查完所有的k时，
 * d(ij)里面存放的就是i到j之间的最短距离了。
 * 
 * 
 * 
 */

public class Floyd {

    /**
     * leetcode 1334. 阈值距离内邻居最少的城市
     * 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，
     * 其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，
     * 距离阈值是一个整数 distanceThreshold。
     * 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。
     * 如果有多个这样的城市，则返回编号最大的城市。
     * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // distances[i][j] 表示 节点i到节点j的最短距离（因为是双向的，所以反之亦然）
        int[][] distances = new int[n][n];
        for (int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            distances[i][i] = 0;
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int dis = edge[2];
            distances[from][to] = dis;
            distances[to][from] = dis;
        }
        for (int i = 0; i < n; i++) {
            // 第一层循环表示当前通过哪个节点进行中转
            for (int j = 0; j < n; j++) {
                // 第二层循环表示起始点是哪个
                for (int k = 0; k < n; k++) {
                    // 第三层循环表示终止点是哪个
                    if (distances[j][i] == Integer.MAX_VALUE || distances[i][k] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (distances[j][i] + distances[i][k] < distances[j][k]) {
                        distances[j][k] = distances[j][i] + distances[i][k];
                    }
                }
            }
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (distances[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            minCount = Math.min(minCount, count);
            if (map.get(count) == null) {
                map.put(count, new ArrayList<>());
            }
            map.get(count).add(i);
        }
        return map.get(minCount).stream().max(Integer::compareTo).get();
    }
}
