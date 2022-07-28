/**
* 描述：
* 创建日期：2022年07月28 16:09:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class ArrayRankTransform {
    public static int[] arrayRankTransform(int[] arr) {
        Map<Integer, Integer> map = new HashMap();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : arr) {
            treeSet.add(num);
        }
        int index = 0;
        while (!treeSet.isEmpty()) {
            map.put(treeSet.pollFirst(), index++);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 100, 100, 100 };
        System.out.println(arrayRankTransform(arr));
    }
}
