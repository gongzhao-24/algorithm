/**
* 描述：
* 创建日期：2022年07月03 10:30:
* @author gong zhao 
**/
package 单周赛.w300;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DecodeMessage {
    public static String decodeMessage(String key, String message) {
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        int index = -1;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (set.contains(key.charAt(i))) {
                continue;
            } else if (key.charAt(i) != ' ') {
                set.add(key.charAt(i));
                index++;
                map.put(key.charAt(i), (char) (index + 'a'));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
            } else {
                sb.append(map.get(c));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeMessage("the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv"));
    }
}
