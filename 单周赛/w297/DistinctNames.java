/**
* 描述：
* 创建日期：2022年06月12 22:37:
* @author gong zhao 
**/
package 单周赛.w297;

import java.util.HashMap;

public class DistinctNames {
    public static long distinctNames(String[] ideas) {
        long res = 0;
        // 后缀分组与首字母映射(由于不会有相同单词因此同一后缀组最多为1个相同首字母->数位记录)
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : ideas) {
            String sub = s.substring(1);
            map.put(sub, map.getOrDefault(sub, 0) | 1 << (s.charAt(0) - 'a'));
        }
        int[][] cnt = new int[26][26]; // cnt[x][y] -> 组中包含首字母x但是不包含首字母y的"组数"
        for (String sub : map.keySet()) {
            // 这个num 代表，这个后缀有几个前缀，且分别是什么
            int num = map.get(sub);
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    // 假若该分组i存在并且j不存在,cnt[i][j]++
                    if ((num >> i & 1) == 1 && (num >> j & 1) == 0) {
                        //表示包含i，但是不包含j的单词个数
                        cnt[i][j]++;
                    }
                }
            }
        }
        for (String sub : map.keySet()) {
            int num = map.get(sub);
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if ((num >> i & 1) == 0 && (num >> j & 1) == 1) {
                        //假如 i = a，j = b ，那么单词 abc 就是包含a，但是不包含b
                        //此时这个单词不包含 i， 但是包含 j ，单词bcd 不包含a，但是包含b，交换就是bac，acd，就是合法的
                        res += cnt[i][j];
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] ideas = new String[] { "coffee", "donuts", "time", "toffee" };
        System.out.println(distinctNames(ideas));
    }
}
