/**
* 描述：
* 创建日期：2022年06月25 22:34:
* @author gong zhao 
**/
package 双周赛.w81;

import java.util.ArrayList;
import java.util.List;

public class CountPairs {
    public long countPairs(int n, int[][] edges) {
        /**
         * 能相互到达，即有一条边能联通即可,并查集,算联通分量，有几个父节点是自己的，就有几个联通分量，然后
         */
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        // 全部联合之后，找到所有的父节点，并找到其下面有多少个节点
        int[] parent = unionFind.parent;
        int[] size = unionFind.size;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                list.add(i);
            }
        }
        if (list.size() == 1) {
            return 0;
        }
        /**
         * Cnm = n!/(m! * (n - m)!)
         * Anm = n!/(n-m)!
         */
        long res = (long) (n * (n - 1)) / 2;

        for (int i = 0; i < list.size(); i++) {
            res -= (long) ((long)size[list.get(i)] * (long)(size[list.get(i)] - 1)) / 2;
        }
        return res;
    }

    // 并查集
    class UnionFind {
        // 连通分量
        int count;
        int[] parent;
        // 表示这个节点有多少个子节点（包括自己）
        int[] size;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p, int q) {
            int parentP = findParent(p);
            int parentQ = findParent(q);
            if (parentP == parentQ) {
                return;
            }
            if (size[parentP] < size[parentQ]) {
                parent[parentP] = parentQ;
                size[parentQ] += size[parentP];
            } else {
                parent[parentQ] = parentP;
                size[parentP] += size[parentQ];
            }
            count--;
        }

        // 找到父节点
        public int findParent(int x) {
            if (x != parent[x]) {
                parent[x] = findParent(parent[x]);
            }
            return parent[x];
        }

        // 返回连通分量数量
        public int count() {
            return count;
        }

        public int[] size() {
            return size;
        }

    }

    public static void main(String[] args) {
        CountPairs countPairs = new CountPairs();
        int[][] edges = new int[][] {
                { 0, 2 }, { 0, 5 }, { 2, 4 }, { 1, 6 }, { 5, 4 } };
        System.out.println(countPairs.countPairs(7, edges));
    }
}
