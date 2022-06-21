/**
* 描述：leetcode 128. 最长连续序列
* 创建日期：2022年06月18 11:19:
* @author gong zhao 
**/
package 并查集;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        UnionFind unionFind = new UnionFind(nums.length);
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i], i);
            if (map.containsKey(nums[i] + 1)) {
                unionFind.union(i, map.get(nums[i] + 1));
            }
            if (map.containsKey(nums[i] - 1)) {
                unionFind.union(i, map.get(nums[i] - 1));
            }
        }

        int maxSize = 0;
        for (int size : unionFind.size) {
            maxSize = Math.max(maxSize, size);
        }
        return maxSize;

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

        // 连接

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
}
