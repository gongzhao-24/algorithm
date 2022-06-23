/**
* 描述：
* 创建日期：2022年06月23 14:57:
* @author gong zhao 
**/
package 单周赛.w298;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个由英文字母组成的字符串 s ，请你找出并返回 s 中的 最好 英文字母。返回的字母必须为大写形式。如果不存在满足条件的字母，则返回一个空字符串。
 * 
 * 最好 英文字母的大写和小写形式必须 都 在 s 中出现。
 * 
 * 英文字母 b 比另一个英文字母 a 更好 的前提是：英文字母表中，b 在 a 之 后 出现。
 * 
 */
public class GreatestLetter {
    public String greatestLetter(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        char res = '#';
        for (int i = 0; i < 26; i++) {
            char left = (char)('a' + i);
            char right = (char)('A' + i);
            if(set.contains(left) && set.contains(right)){
                res = right;
            }
        }
        if(res == '#'){
            return "";
        }
        return String.valueOf(res);

    }
}
