/**
* 描述：1442. 形成两个异或相等数组的三元组数目
* 创建日期：2022年06月09 21:55:
* @author gong zhao 
**/
package 前缀和;

public class CountTriplets {
    public int countTriplets(int[] arr) {
        int[] sumArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sumArray[i] = arr[i];
            } else {
                sumArray[i] = sumArray[i - 1] ^ arr[i];
            }
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if ((sumArray[j - 1] ^ (i == 0 ? 0 : sumArray[i - 1])) == (sumArray[k] ^ sumArray[j - 1])) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
