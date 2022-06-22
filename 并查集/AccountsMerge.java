/**
* 描述：leetcode 721. 账户合并
* 创建日期：2022年06月18 11:53:
* @author gong zhao 
**/
package 并查集;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 邮箱地址，与对应的 所述账号的位序
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (!map.containsKey(email)) {
                    map.put(email, new ArrayList<>());
                }
                map.get(email).add(i);
            }
        }

        UnionFind unionFind = new UnionFind(accounts.size());
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + ", value :" + entry.getValue());
            int firstIndex = entry.getValue().get(0);
            for (int i = 1; i < entry.getValue().size(); i++) {
                unionFind.union(firstIndex, entry.getValue().get(i));
            }
        }
        for (int num : unionFind.parents) {
            System.out.println("parent :" + num);

        }
        // 联合完毕了
        Map<Integer, PriorityQueue<String>> resMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            int parent = unionFind.findParent(i);
            if (resMap.get(parent) == null) {
                resMap.put(parent, new PriorityQueue<>((o1, o2) -> {
                    String left = o1 + o2;
                    String right = o2 + o1;
                    for (int k = 0; k < left.length(); k++) {
                        char leftChar = left.charAt(k);
                        char rightChar = right.charAt(k);
                        if (leftChar != rightChar) {
                            return leftChar - rightChar;
                        }
                    }
                    return 0;
                }));
            }
            for (int j = 1; j < accounts.get(i).size(); j++) {
                if (!resMap.get(parent).contains(accounts.get(i).get(j))) {
                    resMap.get(parent).offer(accounts.get(i).get(j));
                }
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, PriorityQueue<String>> entry : resMap.entrySet()) {
            List<String> account = accounts.get(entry.getKey());
            List<String> newAccount = new ArrayList<>();
            newAccount.add(account.get(0));
            PriorityQueue<String> priorityQueue = entry.getValue();
            while (!priorityQueue.isEmpty()) {
                newAccount.add(priorityQueue.poll());
            }
            res.add(newAccount);
        }
        return res;
    }

    // 并查集
    class UnionFind {
        int[] parents;
        int count;

        /**
         * 并查集需要实现下面几个方法
         * ①：findParent
         * ②：union
         * ③：count
         */

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            count = n;
        }

        public int findParent(int x) {
            if (x != parents[x]) {
                parents[x] = findParent(parents[x]);
            }
            return parents[x];
        }

        public void union(int p, int q) {
            System.out.println("p" + p + ", q" + q);
            int parentP = findParent(p);
            int parentQ = findParent(q);
            System.out.println("parentP" + parentP + ", parentQ" + parentQ);
            if (parentP != parentQ) {
                // todo 这里很容易错误，需要谨慎
                parents[parentP] = parentQ;
                count--;
            }
        }

        public int count() {
            return this.count;
        }
    }
}
