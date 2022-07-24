/**
* 描述：leetcode 959
* 创建日期：2022年06月21 17:21:
* @author gong zhao 
**/
package 并查集;


public class RegionsBySlashes {
    public int regionsBySlashes(String[] grid) {
        int n = grid[0].length();
        char[][] gridArray = new char[n][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                gridArray[i][j] = grid[i].charAt(j);
            }
        }
        UnionFind unionFind = new UnionFind(n * n * 4);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = gridArray[i][j];
                int up = i * n * 4 + j * 4;
                int left = i * n * 4 + j * 4 + 1;
                int down = i * n * 4 + j * 4 + 2;
                int right = i * n * 4 + j * 4 + 3;

                int leftLeft = (j == 0 ? -1 : i * n * 4 + (j - 1) * 4 + 3);
                int rightRight = (j == n - 1 ? -1 : i * n * 4 + (j + 1) * 4 + 1);
                int upUp = (i == 0 ? -1 : (i - 1) * n * 4 + j * 4 + 2);
                int downDown = (i == n - 1 ? -1 : (i + 1) * n * 4 + j * 4);

                if (upUp != -1) {
                    unionFind.union(up, upUp);
                }
                if (downDown != -1) {
                    unionFind.union(down, downDown);
                }
                if (leftLeft != -1) {
                    unionFind.union(left, leftLeft);
                }
                if (rightRight != -1) {
                    unionFind.union(right, rightRight);
                }
                if (c == ' ') {
                    // 首先讲内部四个点连接起来，然后将每个店
                    unionFind.union(up, left);
                    unionFind.union(up, down);
                    unionFind.union(up, right);
                } else if (c == '/') {
                    unionFind.union(up, left);
                    unionFind.union(down, right);
                } else {
                    unionFind.union(up, right);
                    unionFind.union(down, left);
                }

            }
        }
        return unionFind.count;
    }

    class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            count = n;
        }

        public int findParent(int x) {
            if (x != parent[x]) {
                parent[x] = findParent(parent[x]);
            }
            return parent[x];
        }

        public void union(int p, int q) {
            int parentP = findParent(p);
            int parentQ = findParent(q);
            if (parentP == parentQ) {
                return;
            }
            parent[parentP] = parentQ;
            count--;
        }

        public int count() {
            return this.count;
        }

    }

    public static void main(String[] args) {
        RegionsBySlashes regionsBySlashes = new RegionsBySlashes();
        String[] grid = new String[] { "/\\", "\\/" };
        System.out.println(regionsBySlashes.regionsBySlashes(grid));
    }
}
