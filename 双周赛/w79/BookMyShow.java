/**
* 描述：10011. 以组为单位订音乐会的门票
* 创建日期：2022年05月28 22:49:
* @author gong zhao 
**/
package 双周赛.w79;

//这一题没有完全做出来，最开始一个版本有两个用例无法通过，当前的版本有的问题，后面再修改了
public class BookMyShow {
    /**
     * 思路：一共有n排座位，每一排有m个座位。
     * 因此需要求出前 maxRow 排座位中，已经买了票的观众数不超过 m - k 且下标最小的那一排座位，同时我们还需要动态维护每一排的观众数
     */

    class Node {
        Node leftChild;
        Node rightChild;
        // 这个区间中，坐了最少人的一排坐了多少个人
        int minSeat;
        // 这个区间中，一共坐了多少个人
        long sumSeat;
        long add;
    }

    Node root;
    int n;
    int m;

    public BookMyShow(int n, int m) {
        root = new Node();
        this.n = n;
        this.m = m;
    }

    // 查找制定区间中，一行有m，现在需要坐k个人，因此之前最多做了 m - k 个人，保证 minSeat <= m - k

    // 查找区间中，最少坐的人数
    public long query(Node node, int treeLeft, int treeRight, int left, int right) {
        if (treeLeft == left && treeRight == right) {
            return node.minSeat;
        }
        pushDown(node);
        int mid = (treeLeft + treeRight) >> 1;
        if (right <= mid) {
            return query(node.leftChild, treeLeft, mid, left, right);
        } else if (left > mid) {
            return query(node.rightChild, mid + 1, treeRight, left, right);
        } else {
            return Math.min(query(node.leftChild, treeLeft, mid, left, mid),
                    query(node.rightChild, mid + 1, treeRight, mid + 1, right));
        }
    }

    // 查找指定区间中，一行有m，现在需要坐k个人，因此之前最多做了 m - k 个人，保证 minSeat <= m - k
    public long querySum(Node node, int treeLeft, int treeRight, int left, int right) {
        if (treeLeft == left && treeRight == right) {
            long sum = ((long) (right - left + 1)) * (long) m;
            return sum - node.sumSeat;
            // return (Long) (long)((right - left + 1) * m) - (long)(node.sumSeat);
        }
        pushDown(node);
        int mid = (treeLeft + treeRight) >> 1;
        if (right <= mid) {
            return querySum(node.leftChild, treeLeft, mid, left, right);
        } else {
            return querySum(node.leftChild, treeLeft, mid, left, mid)
                    + querySum(node.rightChild, mid + 1, treeRight, mid + 1, right);
        }
    }

    public void update(Node node, int treeLeft, int treeRight, int left, int right, int k) {
        if (treeLeft == left && treeRight == right && left == right) {
            node.minSeat += k;
            node.sumSeat += k;
            return;
        }
        pushDown(node);
        int mid = (treeLeft + treeRight) >> 1;
        if (right <= mid) {
            update(node.leftChild, treeLeft, mid, left, right, k);
        } else {
            update(node.rightChild, mid + 1, treeRight, mid + 1, right, k);
        }
        pushUp(node);
    }

    public void pushUp(Node node) {
        node.sumSeat = node.leftChild.sumSeat + node.rightChild.sumSeat;
        node.minSeat = Math.min(node.leftChild.minSeat, node.rightChild.minSeat);
    }

    public void pushDown(Node node) {
        if (node.leftChild == null) {
            node.leftChild = new Node();
        }
        if (node.rightChild == null) {
            node.rightChild = new Node();
        }
        return;
    }

    public int[] gather(int k, int maxRow) {
        int lo = 0;
        int hi = maxRow;
        long ans;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            ans = query(root, 0, n - 1, 0, mid);
            if (ans + k <= m) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        // 最后有两种结果，lo = 0 || lo = n
        if (lo == n) {
            return new int[] {};
        }
        long seat = query(root, 0, n - 1, lo, lo);

        int[] res = new int[] { (int) lo, (int) seat };
        update(root, 0, n - 1, (int) lo, (int) lo, k);
        return res;
    }

    public void updateSum(Node node, int treeLeft, int treeRight, int left, int right, long k) {
        if (treeLeft == left && treeRight == right && left == right) {
            node.minSeat += k;
            node.sumSeat += k;
            return;
        }
        pushDown(node);
        int mid = (treeLeft + treeRight) >> 1;
        if (right <= mid) {
            updateSum(node.leftChild, treeLeft, mid, left, right, k);
        } else {
            long leftChildLeave = ((long) (mid - left + 1) * m - node.leftChild.sumSeat);
            if (leftChildLeave >= k) {
                updateSum(node.leftChild, treeLeft, mid, left, mid, k);
            } else {
                updateSum(node.leftChild, treeLeft, mid, left, mid, leftChildLeave);
                updateSum(node.rightChild, mid + 1, treeRight, mid + 1, right, k - leftChildLeave);
            }
        }
        pushUp(node);
    }

    public boolean scatter(int k, int maxRow) {
        long queryRes = querySum(root, 0, n - 1, 0, maxRow);
        if (queryRes < k) {
            return false;
        }
        updateSum(root, 0, n - 1, 0, maxRow, k);
        return true;
    }

    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow(94, 270375234);
        boolean res1 = bookMyShow.scatter(207095844, 4);
        int[] res2 = bookMyShow.gather(77100725, 62);
        int[] res3 = bookMyShow.gather(884419363, 23);
        int[] res4 = bookMyShow.gather(600647239, 92);
        boolean res5 = bookMyShow.scatter(158051356, 83);
        int[] res6 = bookMyShow.gather(139947052, 89);
        System.out.println();
    }
}
