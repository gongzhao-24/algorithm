/**
* 描述：leetcode 1417. 重新格式化字符串
* 创建日期：2022年08月11 16:13:
* @author gong zhao 
**/
package 每日一题;

/**
 * 反正就是数字字母一个接一个
 */
public class Reformat {
    public static String reformat(String s) {
        String charStr = "";
        String numStr = "";
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                numStr += c;
            } else {
                charStr += c;
            }
        }
        if (Math.abs(charStr.length() - numStr.length()) > 1) {
            return "";
        }
        // 将字符串的放前面
        if (charStr.length() < numStr.length()) {
            String temp = numStr;
            numStr = charStr;
            charStr = temp;
        }
        int numIndex = 0;
        int charIndex = 0;
        String res = "";
        while (numIndex < numStr.length() || charIndex < charStr.length()) {
            res += charStr.charAt(charIndex++);
            if(numIndex < numStr.length()){
                res += numStr.charAt(numIndex++);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reformat("a0b1c2"));
    }
}
