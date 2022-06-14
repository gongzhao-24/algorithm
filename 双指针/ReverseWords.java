/**
* 描述：leetcode 151
* 创建日期：2022年06月13 16:54:
* @author gong zhao 
**/
package 双指针;

public class ReverseWords {
    public static String reverseWords(String s) {
        s = s.trim();
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i].length() == 0 || " ".equals(array[i])) {
                continue;
            }
            sb.append(array[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }
}
