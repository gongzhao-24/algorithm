/**
* 描述：leetcode 424
* 创建日期：2022年06月14 11:23:
* @author gong zhao 
**/
package 滑动窗口;

public class CharacterReplacement {
    /**
     * 这一题是，如果是找到符合条件的窗口时，窗口扩大，如果发现窗口不符合条件，则窗口平移
     * 
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int[] contains = new int[26];
        int historyMaxCount = 0;
        int left = 0;
        int right = 0;
        for (; right < s.length(); right++) {
            char c = s.charAt(right);
            contains[c - 'A']++;
            // 历史窗口中出现的最大次数
            historyMaxCount = Math.max(contains[c - 'A'], historyMaxCount);
            if (right - left + 1 > historyMaxCount + k) {
                contains[s.charAt(left) - 'A']--;
                left++;
            }
        }
        return right - left;
    }

    public static void main(String[] args) {
        CharacterReplacement characterReplacement = new CharacterReplacement();
        System.out.println(characterReplacement.characterReplacement("AABABBA", 1));
    }
}
