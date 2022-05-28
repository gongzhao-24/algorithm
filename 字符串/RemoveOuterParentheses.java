package 字符串;

/**
 * 描述：
 * 创建日期：2022年05月28 11:53:
 * 
 * @author gong zhao
 **/
public class RemoveOuterParentheses {
    public String removeOuterParentheses(String s) {
        String string = "";
        int leftCount = 0;
        int leftIndex = 0;
        int leftCountMax = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
                leftCountMax = Math.max(leftCount, leftCountMax);
            } else {
                leftCount--;
                if (leftCount == 0) {
                    if (leftCountMax == 1) {
                        // string = string + s.substring(leftIndex, i + 1);
                    } else {
                        string = string + s.substring(leftIndex + 1, i);
                    }
                    leftCount = 0;
                    leftIndex = i + 1;
                }
            }
        }
        return string;
    }
}
