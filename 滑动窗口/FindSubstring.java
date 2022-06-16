/**
* 描述：leetcode 30
* 创建日期：2022年06月15 16:20:
* @author gong zhao 
**/
package 滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSubstring {
    /**
     * ①：因为每个单词的长度固定，所以每次滑动的步长固定为 wordLen
     * ②：因为滑动的步长是 wordLen，所以有可能中间的某些部分漏掉了，因此需要划分多窗口
     * 从 0 到 wordLen - 1 个窗口进行滑动
     * ③：再滑动的过程中，先固定左端点，滑动右端点，记录窗口中出现的单词的频次，
     * 如果发现窗口中某些单词的频次已经超出了，而且长度还不为 words的总长度，
     * 需要left向前滑动一个步长
     * ④：如果恰好这个窗口满足条件，就将left的点记录下来，然后left向右移动一下
     */

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) {
            return res;
        }
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        int size = s.length();
        int wordLen = words[0].length();
        int wordSize = words.length * wordLen;
        Map<String, Integer>[] slidingWindows = new HashMap[wordLen];
        for (int i = 0; i < wordLen; i++) {
            // 先将各个窗口初始化好
            slidingWindows[i] = new HashMap<>();
        }
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i + wordLen;
            Map<String, Integer> curMap = slidingWindows[i];
            while (right <= s.length()) {
                String word = s.substring(right - wordLen, right);
                // 判断，如果 right - left + 1 = s.length,而且当前单词的频次恰好与words中一致，则符合要求
                if (wordMap.containsKey(word)) {
                    curMap.put(word, curMap.getOrDefault(word, 0) + 1);
                    if (curMap.get(word) == 5000) {
                        int a = 1;
                    }
                    int curMapCount = curMap.get(word);
                    int wordMapCount = wordMap.get(word);
                    if (curMapCount == wordMapCount) {
                        // 如果相等，判断一下
                        if (right - left == wordSize) {
                            res.add(left);
                            String leftWord = s.substring(left, left + wordLen);
                            curMap.put(leftWord, curMap.get(leftWord) - 1);
                            left += wordLen;
                        }
                    } else if (curMap.get(word) > wordMap.get(word)) {
                        while (left < right) {
                            left += wordLen;
                            String leftWord = s.substring(left - wordLen, left);
                            curMap.put(leftWord, curMap.get(leftWord) - 1);
                            if (leftWord.equals(word)) {
                                break;
                            }

                        }
                    }
                } else {
                    left = right;
                    // 这里直接清空
                    curMap.clear();
                }
                right += wordLen;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindSubstring findSubstring = new FindSubstring();
        String s = "barfoofoobarthefoobarman";
        String[] words = new String[] { "bar", "foo", "the" };
        System.out.println(findSubstring.findSubstring(s, words));
    }
}
