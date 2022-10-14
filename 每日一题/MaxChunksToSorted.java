/**
* 描述：
* 创建日期：2022年10月13 17:33:
* @author gong zhao 
**/
package 每日一题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxChunksToSorted {
    public int maxChunksToSorted(int[] arr) {
        /**
         * 大概意思就是，给数组分块，在这一块内部的数字，本身之间需要是连续的，
         */
        int[] newArray = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        // 现在arr 是已经排好序的，而newArray是原始的字符串
        int count = 0;
        /**
         * 好像是一个贪心的过程。
         * 假如从第一个数开始算起来，如果它应该的位置是y，但是现在实际的位置是x
         * 那么[x,y]应该被划分为一块，
         * 然后遍历第二个数，发现它应该的位置比当前的位置小，那不用考虑
         */
        int boundry = 0;
        for (int i = 0; i < newArray.length; i++) {
            // 实际在自增中应该处于的位序
            int realIndex = map.get(newArray[i]);
            if (realIndex <= boundry && i == boundry) {
                count++;
                boundry++;
            } else if (realIndex > boundry) {
                boundry = realIndex;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MaxChunksToSorted maxChunksToSorted = new MaxChunksToSorted();
        int[] arr = new int[] { 1,0,2,3,4};
        System.out.println(maxChunksToSorted.maxChunksToSorted(arr));
    }
}
