/**
* 描述：计算数字在二进制下的1的个数
* 创建日期：2022年07月24 17:57:
* @author gong zhao 
**/
package 数学;

public class CountOne {
    public static int countOne(int num) {
        return Integer.bitCount(num);
    }

    public static void main(String[] args) {
        System.out.println(countOne(1));
        System.out.println(countOne(2));
        System.out.println(countOne(3));
        System.out.println(countOne(4));
        System.out.println(countOne(5));
        System.out.println(countOne(6));
        System.out.println(countOne(7));
        System.out.println(countOne(8));
    }
}
