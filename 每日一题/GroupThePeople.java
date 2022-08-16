/**
* 描述：leetcode 1282. 用户分组
* 创建日期：2022年08月12 11:14:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupThePeople {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap();
        List<Integer> sizeList = new ArrayList();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            if (!sizeList.contains(size)) {
                sizeList.add(size);
            }
            List<Integer> list = map.getOrDefault(size, null);
            if (list == null) {
                map.put(size, new ArrayList());
                list = map.get(size);
            }
            list.add(i);
        }
        List<List<Integer>> ans = new ArrayList();
        Collections.sort(sizeList);
        for (int size : sizeList) {
            List<Integer> list = map.get(size);
            // 这里的list是所有的size相同的
            if (list.size() == size) {
                ans.add(list);
            } else {
                int count = 0;
                List<Integer> res = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    res.add(list.get(i));
                    count++;
                    if (count == size) {
                        ans.add(new ArrayList<>(res));
                        res.clear();
                        count = 0;
                    }
                }

            }

        }

        // 上面把所有组数量大小相同的排出来了
        return ans;
    }
}
