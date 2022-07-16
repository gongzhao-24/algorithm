/**
* 描述：
* 创建日期：2022年07月10 10:46:
* @author gong zhao 
**/
package 单周赛.w301;

public class CanChange {
    public boolean canChange(String start, String target) {
        // 首先把 _ 去掉之后，两个字符串应该是一样的，否则肯定返回false
        if (!start.replaceAll("_", "").equals(target.replaceAll("_", ""))) {
            return false;
        }
        int i = 0;
        int j = 0;
        int len = start.length();
        while (i < len && j < len) {
            while (i < len && start.charAt(i) == '_') {
                i++;
            }
            while (j < len && target.charAt(j) == '_') {
                j++;
            }
            if (i == len && j == len) {
                break;
            }
            // 如果是R
            if (start.charAt(i) == 'R' && i > j) {
                return false;
            }
            // 如果是L
            if (start.charAt(i) == 'L' && i < j) {
                return false;
            }
            i++;
            j++;
        }
        return true;

    }

    public static void main(String[] args) {
        CanChange canChange = new CanChange();
        String start = "_L__R__R_";
        String target = "L______RR";
        System.out.println(canChange.canChange(start, target));
    }
}
