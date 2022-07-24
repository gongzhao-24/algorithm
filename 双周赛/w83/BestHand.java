/**
* 描述：
* 创建日期：2022年07月23 22:30:
* @author gong zhao 
**/
package 双周赛.w83;

import java.util.HashMap;
import java.util.Map;

public class BestHand {
    public static String bestHand(int[] ranks, char[] suits) {
        // 表示同一数字有多少个
        Map<Integer, Integer> map = new HashMap<>();
        // 表示同一花色有多少个
        Map<Character, Integer> map2 = new HashMap<>();
        // 排数
        int maxCount = 0;
        // 花色数
        int maxCount2 = 0;

        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                map.put(ranks[i], 1);
                map2.put(suits[i], 1);
                maxCount = 1;
                maxCount2 = 1;
            } else {
                int cout = map.getOrDefault(ranks[i], 0);
                int cout2 = map2.getOrDefault(suits[i], 0);

                maxCount = Math.max(maxCount, cout + 1);
                maxCount2 = Math.max(maxCount2, cout2 + 1);

                map.put(ranks[i], cout + 1);
                map2.put(suits[i], cout2 + 1);

            }
        }
        if (maxCount2 == 5) {
            return "Flush";
        } else if (maxCount >= 3) {
            return "Three of a Kind";
        } else if (maxCount == 2) {
            return "Pair";
        } else {
            return "High Card";
        }
    }

    public static void main(String[] args) {
        int[] ranks = new int[]{4,4,2,4,4};
        char[] suits = new char[]{'d','a','a','b','c'};
        System.out.println(bestHand(ranks, suits));
    }
}
