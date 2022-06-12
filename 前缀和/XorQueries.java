/**
* 描述：1310. 子数组异或查询
* 创建日期：2022年06月09 21:49:
* @author gong zhao 
**/
package 前缀和;

public class XorQueries {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] sumArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sumArray[i] = arr[i];
            } else {
                sumArray[i] = sumArray[i - 1] ^ arr[i];
            }
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            res[i] = sumArray[right] ^ (left == 0 ? 0 : sumArray[left - 1]);
        }
        return res;
    }
}
