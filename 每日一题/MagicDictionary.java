/**
* 描述：leetcode 676. 实现一个魔法字典
* 创建日期：2022年07月11 10:34:
* @author gong zhao 
**/
package 每日一题;

import java.util.HashSet;
import java.util.Set;

public class MagicDictionary {
    Set<String> set;

    public MagicDictionary() {
        set = new HashSet<>();
    }

    public void buildDict(String[] dictionary) {
        for (String str : dictionary) {
            set.add(str);
        }
    }

    public boolean search(String searchWord) {
        for (int i = 0; i < searchWord.length(); i++) {
            for (int j = 0; j <= 26; j++) {
                char c = (char) (j + 'a');
                if (c == searchWord.charAt(i)) {
                    continue;
                }
                String newWord = (i == 0 ? "" : searchWord.substring(0, i))
                        + c
                        + (i == searchWord.length() - 1 ? "" : searchWord.substring(i + 1));
                if (set.contains(newWord)) {
                    return true;
                }
            }

        }
        return false;
    }
}
