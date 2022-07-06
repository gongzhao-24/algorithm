/**
* 描述：
* 创建日期：2022年06月02 22:58:
* @author gong zhao 
**/
package 并查集;

/**
 * 并查集 主要解决图论中的连通性问题
 */

public class UnionFind {
    // 连通分量
    private int count;
    // 连通关系
    private int[] parent;

    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            // 初始化时，每个节点的父节点是本身
            parent[i] = i;
        }
    }

    // 将p与q连接起来
    public void union(int p, int q) {
        int parentP = findParent(p);
        int parentQ = findParent(q);
        if (parentP == parentQ) {
            return;
        }
        parent[parentP] = parentQ;
        count--;
    }

    /* 判断 p 和 q 是否连通 */
    public boolean connected(int p, int q) {
        int parentP = findParent(p);
        int parentQ = findParent(q);
        return parentP == parentQ;
    }

    /* 返回图中有多少个连通分量 */
    public int count() {
        return this.count;
    }

    /* 返回当前节点的根节点 */
    private int findParent(int x) {
        // 找到某一个节点的父节点是本身的时候，即找到了父节点
        if (x != parent[x]) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];   
    }
}