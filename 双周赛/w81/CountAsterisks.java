/**
* 描述：
* 创建日期：2022年06月25 22:30:
* @author gong zhao 
**/
package 双周赛.w81;

public class CountAsterisks {
    public int countAsterisks(String s) {
        int count = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (left == 0 && c == '|') {
                left = 1;
            } else if (left == 1 && c == '|') {
                left = 0;
            }
            if (left == 0 && c == '*') {
                count++;
            }
        }
        return count;
    }
}
