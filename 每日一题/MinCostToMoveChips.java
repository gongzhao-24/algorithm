/**
* 描述：
* 创建日期：2022年07月08 09:36:
* @author gong zhao 
**/
package 每日一题;

import java.util.HashSet;
import java.util.Set;

public class MinCostToMoveChips {
    public int minCostToMoveChips(int[] position) {
        // 先遍历一下，找出来所有位置是多少，然后依次遍历，找出最小的成本
        Set<Integer> set = new HashSet<>();
        for (int num : position) {
            set.add(num);
        }
        int minCost = Integer.MAX_VALUE;
        for (int i : set) {
            int cost = 0;
            for (int j = 0; j < position.length; j++) {
                if ((Math.abs(position[j] - i)) % 2 == 1) {
                    cost++;
                }
            }
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    public static void main(String[] args) {
        MinCostToMoveChips minCostToMoveChips = new MinCostToMoveChips();
        int[] position = new int[] { 1, 1000000000 };
        System.out.println(minCostToMoveChips.minCostToMoveChips(position));
    }
}
