/**
* 描述：背包问题 模板
* 创建日期：2022年06月22 16:56:
* @author gong zhao 
**/
package 动态规划.背包问题;

public class KnapsackProblem {

    /**
     * 0-1背包问题,二维数组解法
     * 题目：
     * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
     * 第 i 件物品的容量是 weights[i]，价值是 values[i]。
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出最大价值。
     */
    public int knapsackProblem1(int[] weights, int[] values, int V) {
        /**
         * 分析：
         * ①：因为每件物品只能使用一次，因此对于每件物品来说，它有两种可能性，选或者不选.
         * 因此我们可以使用一个二维数组dp来表示整个过程，dp[i][j] 表示选到第i个物品，
         * 占用了j的容量时的最大价值，那么显然，i的取值范围为[0, N]，j 的取值 范围为 [0, V].
         * 当前的状态依赖于之前的状态，可以理解为从初始状态 dp[0][0]开始决策。
         * 
         * ②：如果当前背包的容量不够（j < weights[i]）,没得选，因此前i个物品的最优解即前 i-1个物品的最优解
         * dp[i][j] = dp[i-1][j]
         * 
         * ③：如果当前背包的容量足够，那么就需要在两种可能性中决策，选择当前第i个物品，不选择当前第i个物品。
         * 如果选择：dp[i][j] = dp[i-1][j - weights[i]] + values[i];
         * (这个转移方程的意思是，因为选择了当前物品，因此容量需要占用 weights[i],所以只能由上一个物品的
         * dp[i-1][j - weights[i]] 转移而来，结果还需要加上当前物品的价值 values[i])
         * 如果不选：dp[i][j] = dp[i-1][j];
         * 
         */

        int length = weights.length;
        int[][] dp = new int[length + 1][V + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = Math.max(dp[i - 1][j],
                        j - weights[i - 1] < 0 ? 0 : dp[i - 1][j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[length][V];
    }

    /**
     * 0-1背包问题,一维数组解法
     * 题目：
     * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
     * 第 i 件物品的容量是 weights[i]，价值是 values[i]。
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出最大价值。
     */
    public int knapsackProblem2(int[] weights, int[] values, int V) {
        /**
         * 分析：
         * ①：二维数组的0-1背包的动态转移方程式为：
         * dp[i][j] = Math.max(dp[i - 1][j],j - weights[i - 1] < 0 ? 0 : dp[i - 1][j -
         * weights[i - 1]] + values[i - 1]);
         * 现在变成了一维，因此我们定义 dp[j] 表示 N 件物品在背包容量 j 下的最优解。
         * ②：为什么一维背包需要逆序，在二维情况下，状态dp[i][j]是由上一轮i - 1的状态得来的，
         * dp[i][j]与dp[i - 1][j]是独立的。而优化到一维后，如果我们还是正序，则有dp[较小体积]更新到dp[较大体积]，
         * 则有可能本应该用第i-1轮的状态却用的是第i轮的状态
         * ③：简单来说，一维情况正序更新状态dp[j]需要用到前面计算的状态已经被「污染」，逆序则不会有这样的问题。
         * 
         */

        int length = weights.length;
        int[] dp = new int[V + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = V; j >= 0; j--) {
                dp[j] = Math.max(dp[j], j - weights[i - 1] < 0 ? dp[j] : dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[V];
    }

    /**
     * 完全背包问题,二维数组解法(三重循环版本)
     * 题目：
     * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
     * i 件物品的容量是 weights[i]，价值是 values[i]。
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出最大价值。
     */
    public int completeKnapsackProblem1(int[] weights, int[] values, int V) {
        /**
         * 分析：
         * ①：完全背包问题和0-1背包问题最大的不同点在于，完全背包问题中的物品每件都有无限件可以使用
         * 那么很自然的就可以想到有多种选择，选0件，选1件，选2件。。。。。等等，直到空间不够用为止,
         * 那么就有方案1,使用三重循环来做
         * 
         */
        int length = weights.length;
        int[][] dp = new int[length + 1][V + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 0; k * weights[i - 1] <= j; k++) {
                    // 物品可以选0个，选一个，选两个，直到不能选了为止
                    if (k == 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i][j],
                                j - k * weights[i - 1] < 0 ? dp[i][j]
                                        : dp[i - 1][j - k * weights[i - 1]] + k * values[i - 1]);
                    }
                }
            }
        }
        return dp[length][V];
    }

    /**
     * 完全背包问题,二维数组解法(二重循环简化版)
     * 题目：
     * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
     * 第 i 件物品的容量是 weights[i]，价值是 values[i]。
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出最大价值。
     */
    public int completeKnapsackProblem2(int[] weights, int[] values, int V) {
        /**
         * 分析：
         * ①：完全背包问题和0-1背包问题最大的不同点在于，完全背包问题中的物品每件都有无限件可以使用
         * 那么很自然的就可以想到有多种选择，选0件，选1件，选2件。。。。。等等，直到空间不够用为止,
         * 那么就有方案1,使用三重循环来做
         * 
         */
        int length = weights.length;
        int[][] dp = new int[length + 1][V + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                // 在前面一个状态的基础上，多选一个，选最大的value
                dp[i][j] = Math.max(dp[i][j],
                        j - weights[i - 1] < 0 ? dp[i][j] : dp[i][j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[length][V];
    }

    /**
     * 完全背包问题,一维数组解法
     * 题目：
     * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
     * 第 i 件物品的容量是 weights[i]，价值是 values[i]。
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出最大价值。
     */
    public int completeKnapsackProblem3(int[] weights, int[] values, int V) {
        /**
         * 分析：
         * ①：同样在0-1背包一维转移方程的基础上分析，0-1背包一维方程中，容量j是倒序的，
         * 其根本目的在于避免 正序过程中，第 i 个物品更新 的 dp[较小体积] 污染了 第 i-1行的 dp[较小体积]的数据
         * ，而在完全背包中，在相同的物品，如果取第二个的时候，正好需要从第一个转移而来，所以需要正序
         * 
         */
        int length = weights.length;
        int[] dp = new int[V + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= V; j++) {
                dp[j] = Math.max(dp[j], j < weights[i - 1] ? dp[j] : dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[V];
    }

    /**
     * 多重背包问题1：二维数组
     * 有 N 种物品和一个容量是 V 的背包，
     * 第 i 种物品最多有 amount[i] 件，每件物品的容量是 weights[i]，价值是 values[i]。
     * 
     * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
     * 输出最大价值。
     */
    public int multipleKnapsackProblem1(int[] weights, int[] values, int[] amount, int V) {
        /**
         * ①：多重背包问题相比于完全背包，多了一个附加条件，即：每种物品的个数是有限的，因此可以在三重遍历的基础上做
         */
        int len = weights.length;
        int[][] dp = new int[len + 1][V + 1];
        // dp[0][0]表示初始情况
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 0; k * weights[i - 1] <= j && k <= amount[i - 1]; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * weights[i - 1]] + k * values[i - 1]);
                }
            }
        }
        return dp[len][V];
    }

    /**
     * 多重背包问题1：一维数组优化
     * 有 N 种物品和一个容量是 V 的背包，
     * 第 i 种物品最多有 amount[i] 件，每件物品的容量是 weights[i]，价值是 values[i]。
     * 
     * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
     * 输出最大价值。
     */
    public int multipleKnapsackProblem2(int[] weights, int[] values, int[] amount, int V) {
        /**
         * ①：多重背包问题相比于完全背包，多了一个附加条件，即：每种物品的个数是有限的，因此可以在三重遍历的基础上做
         * ②：同上面的一维数组优化一致，只保留 容量维度
         */
        int len = weights.length;
        int[] dp = new int[V + 1];
        // dp[0][0]表示初始情况
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 0; k * weights[i - 1] <= j && k <= amount[i - 1]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * weights[i - 1]] + k * values[i - 1]);
                }
            }
        }
        return dp[V];
    }

    /**
     * 多重背包问题1：(本题考查多重背包的二进制优化方法。)
     * 有 N 种物品和一个容量是 V 的背包，
     * 第 i 种物品最多有 amount[i] 件，每件物品的容量是 weights[i]，价值是 values[i]。
     * 
     * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
     * 输出最大价值。
     */
    public int multipleKnapsackProblem3(int[] weights, int[] values, int[] amount, int V) {
        /**
         * 
         */
        return 0;
    }

    /**
     * 多重背包问题1：(本题考查多重背包的单调队列优化方法。)
     * 有 N 种物品和一个容量是 V 的背包，
     * 第 i 种物品最多有 amount[i] 件，每件物品的容量是 weights[i]，价值是 values[i]。
     * 
     * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
     * 输出最大价值。
     */
    public int multipleKnapsackProblem4(int[] weights, int[] values, int[] amount, int V) {
        /**
         * 
         */
        return 0;
    }

    /**
     * 混合背包问题1：
     * 有 N 种物品和一个容量是 V 的背包。物品一共有三类：
     * 第一类物品只能用1次（01背包）；
     * 第二类物品可以用无限次（完全背包）；
     * 第三类物品最多只能用 amount[i] 次（多重背包）；
     * 每件物品的容量是 weights[i]，价值是 values[i]。
     * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。输出最大价值。
     * 备注：
     * amount[i]=−1 表示第 i 种物品只能用1次；
     * amount[i]=0 表示第 i 种物品可以用无限次；
     * amount[i]>0 表示第 i 种物品可以使用 amount[i] 次；
     */
    public int mixtureKnapsackProblem1(int[] weights, int[] values, int[] amount, int V) {
        /**
         * 
         */
        return 0;
    }

    public static void main(String[] args) {
        int[] weights = new int[] { 1, 2, 3, 4 };
        int[] values = new int[] { 2, 4, 4, 5 };
        int[] amount = new int[] { 3, 1, 3, 2 };
        int V = 5;

        KnapsackProblem knapsackProblem = new KnapsackProblem();
        System.out.println(knapsackProblem.knapsackProblem1(weights, values, V));
        System.out.println(knapsackProblem.knapsackProblem2(weights, values, V));
        System.out.println(knapsackProblem.completeKnapsackProblem1(weights, values, V));
        System.out.println(knapsackProblem.completeKnapsackProblem2(weights, values, V));
        System.out.println(knapsackProblem.completeKnapsackProblem3(weights, values, V));
        System.out.println(knapsackProblem.multipleKnapsackProblem1(weights, values, amount, V));
        System.out.println(knapsackProblem.multipleKnapsackProblem2(weights, values, amount, V));
    }
}
