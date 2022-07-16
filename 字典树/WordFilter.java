/**
* 描述：
* 创建日期：2022年07月14 15:44:
* @author gong zhao 
**/
package 字典树;

import java.util.ArrayList;
import java.util.List;

public class WordFilter {
    // 前后字典树，然后把重合的算出来，然后再把最大下标的算出来
    Trie prefixTrie;
    Trie suffixTrie;
    String[] words;

    public WordFilter(String[] words) {
        this.words = words;
        prefixTrie = new Trie();
        suffixTrie = new Trie();
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            prefixTrie.word(str, i);
            suffixTrie.word(new StringBuilder(str).reverse().toString(), i);
        }
    }

    public int f(String pref, String suff) {
        List<Integer> prefixList = prefixTrie.prefix(pref);
        if (prefixList.size() == 0) {
            return -1;
        }
        List<Integer> suffixList = suffixTrie.prefix(new StringBuilder(suff).reverse().toString());
        if (suffixList.size() == 0) {
            return -1;
        }
        int preIndex = prefixList.size() - 1;
        int sufIndex = suffixList.size() - 1;
        while (preIndex >= 0 && sufIndex >= 0) {
            int p = prefixList.get(preIndex);
            int s = suffixList.get(sufIndex);
            System.out.println(p + "--" + s);
            if (p == s) {
                System.out.println("ok");
                return prefixList.get(preIndex);
            } else if (p > s) {
                preIndex--;
            } else {
                sufIndex--;
            }
        }
        System.out.println();

        return -1;
    }

    static class Trie {
        // 字典树
        Trie[] array;
        boolean isEnd;
        List<Integer> indexList;

        public Trie() {
            array = new Trie[26];
            indexList = new ArrayList<>();
        }

        public void word(String word, int indexIndex) {
            Trie current = this;
            Trie next = null;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.array[index] == null) {
                    current.array[index] = new Trie();
                }
                next = current.array[index];
                next.indexList.add(indexIndex);
                current = next;
            }
            next.isEnd = true;
        }

        public List<Integer> prefix(String prefix) {
            Trie current = this;
            Trie next = null;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (current.array[index] == null) {
                    return new ArrayList<>();
                }
                next = current.array[index];
                current = next;
            }

            return next.indexList;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[] { "fs", "fsxtja", "fs", "fsik", "fsr", "fsgt", "fsjyfb", "fsjluyq", "fsqg",
                "fsxwz" };
        WordFilter wordFilter = new WordFilter(words);
        System.out.println(wordFilter.f("fs", "luyq"));
        System.out.println(wordFilter.f("c", "c"));
    }
}
