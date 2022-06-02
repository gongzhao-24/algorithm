/**
* 描述：[792] 匹配子序列的单词数
* 创建日期：2022年06月01 16:54:
* @author gong zhao 
**/
package 字符串;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumMatchingSubseq {
    Map<Character, List<Integer>> map;

    public int numMatchingSubseq(String s, String[] words) {
        // 使用一个map记录所有字符以及其在字符串中对应的位序
        map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == null) {
                map.put(c, new ArrayList());
            }
            map.get(c).add(i);
        }
        int count = 0;
        for (String word : words) {
            if (backtrace(0, new ArrayDeque<>(), word)) {
                count++;
            }
        }
        return count;
        // 先暴力
        // 得研究一下kmp算法
    }

    public boolean backtrace(int index, Deque<Integer> deque, String word) {
        if (index == word.length()) {
            return true;
        } else {
            char c = word.charAt(index);
            List<Integer> list = map.get(c);
            if (list == null) {
                return false;
            }
            for (int i = 0; i < list.size(); i++) {
                int nextIndex = list.get(i);
                if (!deque.isEmpty() && nextIndex <= deque.getLast()) {
                    continue;
                } else {
                    deque.addLast(nextIndex);
                    boolean res = backtrace(index + 1, deque, word);
                    if (res) {
                        return res;
                    }
                    deque.removeLast();
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        NumMatchingSubseq numMatchingSubseq = new NumMatchingSubseq();
        String s= "abcde";
        String[] words = new String[]{"a","bb","acd","ace"};
        int res = numMatchingSubseq.numMatchingSubseq(s, words);
        System.out.println(res);
    }
}
