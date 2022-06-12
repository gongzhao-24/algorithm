/**
* 描述：
* 创建日期：2022年06月11 23:18:
* @author gong zhao 
**/
package 双周赛.w80;

import java.util.HashSet;
import java.util.Set;

public class StrongPasswordCheckerII {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }
        Set<Character> set = new HashSet<>();
        set.add('!');
        set.add('@');
        set.add('#');
        set.add('$');
        set.add('%');
        set.add('^');
        set.add('&');
        set.add('*');
        set.add('(');
        set.add(')');
        set.add('-');
        set.add('+');
        boolean containUp = false;
        boolean containLow = false;
        boolean containNum = false;
        boolean containSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= 'a' && c <= 'z') {
                containLow = true;
            } else if (c >= 'A' && c <= 'Z') {
                containUp = true;
            } else if (c >= '0' && c <= '9') {
                containNum = true;
            } else if (set.contains(c)) {
                containSpecial = true;
            }
            if (i != 0 && c == password.charAt(i - 1)) {
                return false;
            }
        }
        return containLow && containUp && containNum && containSpecial;
    }
}
