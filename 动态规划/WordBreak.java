/**
* 描述：leetcode 140
* 创建日期：2022年06月30 22:39:
* @author gong zhao 
**/
package 动态规划;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    Set<String> set;
    int maxWordLen;
    int len;
    String s;

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.s = s;
        this.len = s.length();
        set = new HashSet<>();
        maxWordLen = 0;
        for (String word : wordDict) {
            maxWordLen = Math.max(maxWordLen, word.length());
            set.add(word);
        }
        List<List<String>> lists = new ArrayList<>();
        dfs(lists, 0, "", new ArrayDeque<String>());
        List<String> res = new ArrayList<>();
        for (List<String> list : lists) {
            String cur = "";
            for (String str : list) {
                cur = cur + str;
                cur = cur + " ";
            }
            res.add(cur.trim());
        }
        return res;

    }

    public void dfs(List<List<String>> res, int index, String current, Deque<String> deque) {
        if (index == len) {
            if (set.contains(current)) {
                deque.addLast(current);
                res.add(new ArrayList<>(deque));
                deque.removeLast();
            }
        } else {
            if (set.contains(current)) {
                deque.addLast(current);
                dfs(res, index, "", deque);
                deque.removeLast();
                dfs(res, index + 1, current + s.charAt(index), deque);
            } else {
                if (current.length() < maxWordLen) {
                    dfs(res, index + 1, current + s.charAt(index), deque);
                }
            }
        }
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        System.out.println(wordBreak.wordBreak(s, wordDict));
    }
}
