/**
* 描述：
* 创建日期：2022年07月21 11:25:
* @author gong zhao 
**/
package 单周赛.w296;

import java.util.stream.IntStream;

public class ArrayChange {
    public int[] arrayChange(int[] nums, int[][] operations) {
        int len = nums.length;
        Integer[] idx = IntStream.range(0, len).boxed().toArray(Integer[]::new);





        for (int[] operation : operations) {
            //将数组中的 oldNum 替换成 newNum
            int oldNum = operation[0];
            int newNum = operation[1];
        }
    }

  
}
