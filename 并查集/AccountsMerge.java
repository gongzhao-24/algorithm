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

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 邮箱地址，与对应的 所述账号的位序
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(i);
                if (!map.containsKey(email)) {
                    map.put(email, new ArrayList<>());
                }
                map.get(email).add(i);

            }
        }
        //第二步，是将这些拥有公共邮箱的账号联合起来，

    }
}
