import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 描述：
 * 创建日期：2022年11月10 16:08:
 * 
 * @author gong zhao
 **/
public class ShortestPathAllKeys {
    /**
     * 给定一个只包含空房间、墙、起点和终点的二维网格，我们可以使用广度优先搜索的方法求出起点到终点的最短路径。这是因为在最短路径上，我们最多只会经过每个房间一次。因此从起点开始，使用队列进行广度优先搜索，当第一个搜索到某个节点的时候，我们就可以得到从起点到该节点正确的最短路。
     * 
     * 如果加上了钥匙和锁，我们应该如何解决问题呢？类似地，在最短路径上也不可能存在如下的情况：我们经过了某个房间两次，并且这两次我们拥有钥匙的情况是完全一致的。
     * 
     * 因此，我们可以用一个三元组 (x,y,mask) 表示当前的状态，其中 x,y)
     * 表示当前所处的位置，mask 是一个二进制数，长度恰好等于网格中钥匙的数目，mask 的第 i
     * 个二进制位为 1，当且仅当我们已经获得了网格中的第 i 把钥匙。
     * 
     * 这样一来，我们就可以使用上述的状态进行广度优先搜索。初始时，我们把 (sx,sy,0)
     * 加入队列，其中 (sx,sy) 为起点。在搜索的过程中，我们可以向上下左右四个方向进行扩展：
     * 
     * 如果对应方向是空房间，那么 mask 的值不变；
     * 
     * 如果对应方向是第 i 把钥匙，那么将 mask 的第 i 位置为 1；
     * 
     * 如果对应方向是第 i 把锁，那么只有在 mask 的第 i 位为 1 时，才可以通过。
     * 
     * 当我们搜索到一个 mask 每一个二进制都为 1 的状态时，说明获取了所有钥匙，此时就可以返回最短路作为答案。
     * 
     */

    static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int sx = 0, sy = 0;
        Map<Character, Integer> keyToIndex = new HashMap<Character, Integer>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                //首先是将二维数组全部遍历一遍
                if (grid[i].charAt(j) == '@') {
                    //如果遇到墙了，就记录一下当前的坐标
                    sx = i;
                    sy = j;
                } else if (Character.isLowerCase(grid[i].charAt(j))) {
                    //如果遇到钥匙了
                    if (!keyToIndex.containsKey(grid[i].charAt(j))) {
                        int idx = keyToIndex.size();
                        keyToIndex.put(grid[i].charAt(j), idx);
                    }
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<int[]>();
        int[][][] dist = new int[m][n][1 << keyToIndex.size()];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        queue.offer(new int[] { sx, sy, 0 });
        dist[sx][sy][0] = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1], mask = arr[2];
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx].charAt(ny) != '#') {
                    if (grid[nx].charAt(ny) == '.' || grid[nx].charAt(ny) == '@') {
                        if (dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[] { nx, ny, mask });
                        }
                    } else if (Character.isLowerCase(grid[nx].charAt(ny))) {
                        int idx = keyToIndex.get(grid[nx].charAt(ny));
                        if (dist[nx][ny][mask | (1 << idx)] == -1) {
                            dist[nx][ny][mask | (1 << idx)] = dist[x][y][mask] + 1;
                            if ((mask | (1 << idx)) == (1 << keyToIndex.size()) - 1) {
                                return dist[nx][ny][mask | (1 << idx)];
                            }
                            queue.offer(new int[] { nx, ny, mask | (1 << idx) });
                        }
                    } else {
                        int idx = keyToIndex.get(
                                Character.toLowerCase(grid[nx].charAt(ny)));
                        if ((mask & (1 << idx)) != 0 && dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[] { nx, ny, mask });
                        }
                    }
                }
            }
        }
        return -1;
    }
}
