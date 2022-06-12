/**
* 描述：
* 创建日期：2022年06月11 23:25:
* @author gong zhao 
**/
package 双周赛.w80;

import java.util.Arrays;

public class SuccessfulPairs {
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] res = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            int spell = spells[i];
            int count = 0;
            int index = getLeftIndex(potions, spell, success);
            if(index != -1){
                count = potions.length  - index ;
            }

            res[i] = count;
        }
        return res;
    }

    public static int getLeftIndex(int[] potions, int spell, long success) {
        int lo = 0;
        int hi = potions.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            int potion = potions[mid];
            if ((long) potion * (long) spell >= success) {
                hi = mid - 1;
            }else{
                lo = mid + 1;
            }
        }
        if (lo != potions.length && (long)potions[lo] * (long)spell >= success) {
            return lo;
        }
        return -1;


    }

    public static void main(String[] args) {
        int[] spells = new int[] { 5, 1, 3 };
        int[] potions = new int[] { 1, 2, 3, 4, 5 };
        successfulPairs(spells, potions, 7);
    }
}
