/**
* 描述：leetcode 1049
* 创建日期：2022年06月24 14:22:
* @author gong zhao 
**/
package 动态规划.背包问题;

public class LLastStoneWeightII {
    public static int lastStoneWeightII(int[] stones) {
        //等效于将石头分为两堆，重量最大为 最大重量的一半，找出最大的可能重量
        int sumWeight = 0;
        for(int stone : stones){
            sumWeight += stone;
        }
        int weight = sumWeight/2;
        int[] dp = new int[weight + 1];
        for(int i = 1; i <= stones.length; i ++){
            for(int j = weight; j > 0; j --){
                dp[j] = Math.max(dp[j],  j - stones[i - 1] < 0 ? dp[j] : dp[j - stones[i - 1]] + stones[i - 1]);
            }
        }
        return sumWeight - dp[weight] - dp[weight];
    }

    public static void main(String[] args) {
        int[] stones = new int[]{31,26,33,21,40};
        System.out.println(lastStoneWeightII(stones));
    }
}
