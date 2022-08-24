
/**
* 描述：
* 创建日期：2022年08月23 16:17:
* @author gong zhao 
**/
package 动态规划;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingOffers {
    Map<List<Integer>, Integer> memo = new HashMap<List<Integer>, Integer>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // ①：过滤掉无效的大礼包（总价比单独买还贵的、礼包中为空的、礼包中有某些物品个数超出了所需的）
        int n = price.size();
        List<List<Integer>> filterSpecial = new ArrayList();
        for (List<Integer> list : special) {
            // 遍历每一个大礼包
            int count = 0;
            int specialPrice = 0;
            int normalPrice = 0;
            for (int i = 0; i < n; i++) {
                if (list.get(i) > needs.get(i)) {
                    break;
                }
                count += list.get(i);
                normalPrice += list.get(i) * price.get(i);
            }
            if (count != 0 && normalPrice > list.get(n)) {
                filterSpecial.add(list);
            }

        }
        return dfs(price, filterSpecial, needs, n);
    }

    public int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int n) {
        if (memo.get(needs) == null) {
            // 如果单独购买的话，价格是多少
            int minPrice = 0;
            for (int i = 0; i < n; i++) {
                minPrice += price.get(i) * needs.get(i);
            }

            for (List<Integer> list : special) {
                // 分别使用每个
                List<Integer> next = new ArrayList();
                for (int i = 0; i < n; i++) {
                    if (needs.get(i) < list.get(i)) {
                        break;
                    }
                    // next 的size 一定是和 price 是一直的
                    next.add(needs.get(i) - list.get(i));
                }
                if(next.size() == n){
                    minPrice = Math.min(minPrice, dfs(price, special, next, n));

                }
            }
            memo.put(needs, minPrice);
        }
        return memo.get(needs);
    }

    public static void main(String[] args) {
        ShoppingOffers sOffers = new ShoppingOffers();
        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);

        List<List<Integer>> special = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(0);
        list1.add(5);
        special.add(list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(10);
        special.add(list2);
        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);
        System.out.println(sOffers.shoppingOffers(price, special, needs));
    }

}
