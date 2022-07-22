/**
* 描述：
* 创建日期：2022年07月21 11:10:
* @author gong zhao 
**/
package 单周赛.w296;

public class MinMaxGame {
    public int minMaxGame(int[] nums) {
        return dfs(nums)[0];

    }

    public int[] dfs(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }
        int len = nums.length;
        int[] newArray = new int[len / 2];
        boolean flag = true;
        for (int i = 0; i < len - 1;) {
            if (flag) {
                newArray[i / 2] = Math.min(nums[i], nums[i + 1]);
                flag = false;
            } else {
                newArray[i / 2] = Math.max(nums[i], nums[i + 1]);
                flag = true;
            }
            i += 2;
        }
        return dfs(newArray);
    }

    public static void main(String[] args) {
        MinMaxGame maxGame = new MinMaxGame();
        int[] nums = new int[]{70,38,21,22};
        System.out.println(maxGame.minMaxGame(nums));
    }
}
