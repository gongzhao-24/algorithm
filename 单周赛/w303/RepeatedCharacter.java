/**
* 描述：
* 创建日期：2022年07月24 10:30:
* @author gong zhao 
**/
package 单周赛.w303;

public class RepeatedCharacter {
    public char repeatedCharacter(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (count[index] == 1) {
                return c;
            }else{
                count[index] ++;
            }
        }
        return 'a';
    }
}
