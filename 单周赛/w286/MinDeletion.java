/**
* 描述：
* 创建日期：2022年07月16 17:05:
* @author gong zhao 
**/
package 单周赛.w286;

/**
 * nums.length 为偶数
 * 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
 * 注意，空数组同样认为是美丽数组。
 * 
 * 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
 * 
 * 返回使 nums 变为美丽数组所需删除的 最少 元素数目。
 */

public class MinDeletion {
    public int minDeletion(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 需要保证[0,1][2,3][4,5] 都不等
        int count = 0;
        int i = 0;
        int j = 1;
        for (; j < nums.length;) {
            if (nums[i] != nums[j]) {
                count++;
                i = j + 1;
                j = i + 1;
            } else {
                j += 1;
            }
        }
        if (i < nums.length) {
            count++;
        }

        return count;
    }
}
