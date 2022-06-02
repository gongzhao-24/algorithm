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

    /**
     * 这一题应该可以贪心，想想怎么能贪心。
     */

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
                    return backtrace(index + 1, deque, word);
                }
            }
            return false;
        }
    }

    /**
     * 方法二：指向下一个字母的指针【通过】
     * 思路
     * 
     * 因为 S 很长，所以寻找一种只需遍历一次 S 的方法，避免暴力解法的多次遍历。
     * 
     * 将所有单词根据首字母不同放入不同的桶中。例如当 words = ['dog', 'cat', 'cop']，根据首字母不同可以分为 'c' :
     * ('cat', 'cop'), 'd' : ('dog',)。换句话说，每个桶中的单词就是该单词正在等待匹配的下一个字母。在遍历 S
     * 的同时，将匹配到单词根据下一个需要匹配的字母移动到不同的桶中。
     * 
     * 例如，有字符串 S = 'dcaog'：
     * 
     * 初始化 heads = 'c' : ('cat', 'cop'), 'd' : ('dog',)；
     * 遍历 S[0] = 'd' 后，heads = 'c' : ('cat', 'cop'), 'o' : ('og',)；
     * 遍历 S[1] = 'c' 后，heads = 'a' : ('at',), 'o' : ('og', 'op')；
     * 遍历 S[2] = 'a' 后，heads = 'o' : ('og', 'op'), 't': ('t',) ;
     * 遍历 S[3] = 'o' 后，heads = 'g' : ('g',), 'p': ('p',), 't': ('t',)；
     * 遍历 S[0] = 'g' 后，heads = 'p': ('p',), 't': ('t',)。
     * 算法
     * 
     * 使用长度为 26 的数组 heads 做桶，每个字母对应一个桶。访问 S
     * 中的每个字母时，将该字母对应桶中的所有单词，根据下一个等待匹配字母放入到不同的桶中。
     * 如果已经匹配到单词的最后一个字母，那么子序列单词数加 1。
     * 
     */

    public int numMatchingSubseq1(String S, String[] words) {
        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            heads[i] = new ArrayList<Node>();

        for (String word : words)
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));

        for (char c : S.toCharArray()) {
            ArrayList<Node> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<Node>();

            for (Node node : old_bucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        NumMatchingSubseq numMatchingSubseq = new NumMatchingSubseq();
        String s = "abcde";
        String[] words = new String[] { "a", "bb", "acd", "ace" };
        int res = numMatchingSubseq.numMatchingSubseq(s, words);
        System.out.println(res);
    }
}

class Node {
    String word;
    int index;

    public Node(String w, int i) {
        word = w;
        index = i;
    }
}
